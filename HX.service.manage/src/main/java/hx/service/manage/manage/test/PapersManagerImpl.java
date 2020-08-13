package hx.service.manage.manage.test;

import hx.base.core.dao.dict.*;
import hx.base.core.dao.entity.MarketingManpower;
import hx.base.core.dao.entity.test.papers.*;
import hx.base.core.dao.repo.jpa.MarketingManpowerRepo;
import hx.base.core.dao.repo.jpa.test.papers.*;
import hx.base.core.dao.repo.request.common.Pagination;
import hx.base.core.dao.repo.request.test.PapersPageRequest;
import hx.base.core.dao.repo.request.test.PapersSubjectPageRequest;
import hx.base.core.manage.model.CommonResponse;
import hx.base.core.manage.poi.ExcelReadRowHandler;
import hx.base.core.manage.poi.ExcelTemplateHelper;
import hx.base.core.manage.poi.PoiExcelInfo;
import hx.base.core.manage.poi.WorkbookWithDataHandler;
import hx.base.core.manage.tools.FileTools;
import hx.base.core.manage.tools.JsonTools;
import hx.base.core.manage.tools.MyTimeTools;
import hx.service.manage.manage.AbstractManager;
import hx.service.manage.manage.model.CommonRequest;
import hx.service.manage.manage.model.test.papers.*;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.compress.utils.Lists;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @ClassName PaperManagerImpl
 * @Description 试卷管理ManagerImpl
 * @Author HuoJiaJin
 * @Date 2020/6/20 21:47
 * @Version 1.0
 **/
@Service
public class PapersManagerImpl extends AbstractManager implements PapersManager,
        ExcelReadRowHandler<PapersImportSubjectModel>, WorkbookWithDataHandler<PapersIdRequest> {

    @Autowired
    private PapersRepo papersRepo;
    @Autowired
    private PapersSubjectRepo subjectRepo;
    @Autowired
    private PapersOptionRepo optionRepo;
    @Autowired
    private MarketingManpowerRepo manpowerRepo;
    @Autowired
    private PapersPushRepo pushRepo;
    @Autowired
    private PapersPushAnswerRepo answerRepo;

    @Override
    public String query(PapersQueryRequest request) {

        CommonResponse response = new CommonResponse();
        PapersPageRequest pageRequest = new PapersPageRequest();
        BeanUtils.copyProperties(request, pageRequest);
        pageRequest.setName(request.getName());
        if (null != request.getType() && request.getType() != 0) {
            try {
                pageRequest.setType(PapersType.fromCode(request.getType()));
            } catch (InterruptedException e) {
                logger.error("", e);
                return response.setError(ErrorType.CONVERT);
            }
        }
        Pagination page = papersRepo.page(pageRequest);
        page.convertResult(this::updateStatus);
        response.setData(page);
        return response.toJson();
    }

    private PapersModel updateStatus(Papers papers){
        PapersModel model = new PapersModel();
        model.setId(papers.getId());
        model.setName(papers.getName());
        model.setType(papers.getType().getCode());
        model.setAnswerTime(papers.getAnswerTime());
        model.setEndTime(MyTimeTools.timeToStr(papers.getEndTime()));
        if (LocalDateTime.now().isAfter(papers.getEndTime())){
            papersRepo.updateStatus(papers.getId(), PapersStatus.YJZ, papers.getEndTime());
            model.setStatus(PapersStatus.YJZ.getCode());
        }else {
            model.setStatus(papers.getStatus().getCode());
        }
        return model;
    }

    @Override
    public String add(PapersAddRequest request) {
        CommonResponse response = new CommonResponse();
        Papers byName = papersRepo.findByName(request.getName());
        if (byName != null){
            return response.setError(ErrorType.VALID, "该试卷已存在");
        }
        if (request.getAnswerTime() < 5){
            return response.setError(ErrorType.VALID, "答题时间不应小于5分钟");
        }
        Papers papers = new Papers();
        papers.setName(request.getName());
        papers.setAnswerTime(request.getAnswerTime());
        papers.setEndTime(MyTimeTools.strToTime(request.getEndTime()));
        try {
            papers.setType(PapersType.fromCode(request.getType()));
        } catch (InterruptedException e) {
            logger.error("", e);
            return response.setError(ErrorType.CONVERT);
        }
        papers.setUpdateTime(LocalDateTime.now());
        papers.setInsertTime(LocalDateTime.now());
        papersRepo.persist(papers);
        addSysLog("添加试卷" + request.getName(), request.getToken(), papers.getId());
        response.setMessage("添加试卷成功");
        return response.toJson();
    }

    @Override
    public String delete(PapersIdRequest request) {
        CommonResponse response = new CommonResponse();
        Optional<Papers> op = papersRepo.findById(request.getId());
        if (op.isEmpty()) {
            return response.setError(ErrorType.NOPAPERS);
        }
        if(op.get().getStatus() == PapersStatus.YTS || op.get().getStatus() == PapersStatus.YJZ){
            response.setError(ErrorType.NOPAPERS);
            response.setMessage("试卷已推送或已截止，不可删除");
            return response.toJson();
        }
        papersRepo.updateDelete(request.getId(), LocalDateTime.now());
        addSysLog("删除试卷" + op.get().getName(), request.getToken(), request.getId());
        response.setMessage("删除试卷成功");
        return response.toJson();
    }

    @Override
    public String downloadTemplate(CommonRequest request) {
        CommonResponse response = new CommonResponse();
        PaperTemplateResponse templateResponse = new PaperTemplateResponse();
        InputStream fis = FileTools.getResourcesFileInputStream("template/papersTemplate.xlsx");
        String str = null;
        try {
            str = inputStreamToBase64Str(fis);
        } catch (IOException e) {
            logger.error("", e);
            return response.setError(ErrorType.CONVERT);
        }
        templateResponse.setTemplate(str);
        response.setData(templateResponse);
        return response.toJson();
    }

    @Override
    public String importPapers(PapersImportReqeust request){
        CommonResponse response = new CommonResponse();
        Optional<Papers> op = papersRepo.findById(request.getPaperId());
        if (op.isEmpty()){
            return response.setError(ErrorType.NOPAPERS);
        }
        PoiExcelInfo excelInfo = getExcelInfo(request);
        List<PapersImportSubjectModel> importModels;
        try {
            importModels = ExcelTemplateHelper.readData(excelInfo, this);
        } catch (IOException e) {
            logger.error("", e);
            return response.setError(ErrorType.CONVERT);
        }
        //校验分值
        int sumScore = importModels.stream().mapToInt(p -> p.getScore()).sum();
        if (sumScore != 100){
            logger.error("读取试卷失败，错误信息：{}", "分值相加不等于100");
            return response.setError(ErrorType.CONVERT,
                    toLogString("读取试卷失败：{}", "分值相加不等于100"));
        }
        List<PapersSubject> subjectList = Lists.newArrayList();
        List<PapersOption> optionList = Lists.newArrayList();
        int row = 1;
        for (PapersImportSubjectModel importModel : importModels) {
            if (!importModel.isSuccess()) {
                logger.error("读取试卷失败，行数{}，错误信息：{}", row, importModel.getErrorMsg());
                return response.setError(ErrorType.CONVERT,
                        toLogString("第{}行读取失败：{}", row, importModel.getErrorMsg()));
            }
            PapersSubject subject = new PapersSubject();
            subject.setPapersId(request.getPaperId());
            subject.setList(importModel.getList());
            subject.setSubject(importModel.getSubject());
            subject.setScore(importModel.getScore());
            PapersSubjectType subjectType;
            try {
                subjectType = PapersSubjectType.fromCode(importModel.getType());
                subject.setType(subjectType);
            } catch (Exception e) {
                logger.error("读取试卷失败，行数{}，错误信息：{}", row, "题目类型不存在");
                return response.setError(ErrorType.CONVERT,
                        toLogString("第{}行读取失败：{}", row, "题目类型不存在"));
            }
            List<PapersImportOptionModel> optionModels = importModel.getOptionModels();
            if (isEmpty(optionModels) || optionModels.size() < 2) {
                logger.error("读取试卷失败，行数{}，错误信息：{}", row, "选项数量不应小于两个");
                return response.setError(ErrorType.CONVERT,
                        toLogString("第{}行读取失败：{}", row, "选项数量不应小于两个"));
            }
            //正确答案序号校验
            String[] correctArr = importModel.getCorrectNum().split("\\|");
            //校验题目类型
            if (subjectType == PapersSubjectType.SINGLE){
                if (correctArr.length != 1){
                    logger.error("读取试卷失败，行数{}，错误信息：{}", row, "该题目为单选题，请修改答案序号");
                    return response.setError(ErrorType.CONVERT,
                            toLogString("第{}行读取失败：{}", row, "该题目为单选题，请修改答案序号"));
                }
            }else if (subjectType == PapersSubjectType.MULTIPLE){
                if (correctArr.length <= 1){
                    logger.error("读取试卷失败，行数{}，错误信息：{}", row, "该题目为多选题，请修改答案序号");
                    return response.setError(ErrorType.CONVERT,
                            toLogString("第{}行读取失败：{}", row, "该题目为多选题，请修改答案序号"));
                }
            }
            for (String correct : correctArr) {
                int num = Integer.valueOf(correct);
                if (num > optionModels.size() || num <= 0){
                    logger.error("读取试卷失败，行数{}，错误信息：{}", row, "答案序号超出范围");
                    return response.setError(ErrorType.CONVERT,
                            toLogString("第{}行读取失败：{}", row, "答案序号超出范围"));
                }
            }
            subject.setCorrectNum(importModel.getCorrectNum());

            for (PapersImportOptionModel optionModel : optionModels) {
                PapersOption option = new PapersOption();
                option.setSubjectId(subject.getId());
                option.setList(optionModel.getList());
                option.setContent(optionModel.getContent());
                optionList.add(option);
            }
            subjectList.add(subject);
        }
        persist(request.getPaperId(), subjectList, optionList);
        addSysLog("导入试卷" + op.get().getName() + "成功", request.getToken(), request.getPaperId());
        response.setMessage("导入试卷成功");
        return response.toJson();
    }

    @Transactional(rollbackFor=Exception.class)
    private void persist(String papersId, List<PapersSubject> subjectList, List<PapersOption> optionList){
        subjectRepo.persistAll(subjectList);
        optionRepo.persistAll(optionList);
        papersRepo.updateYDR(papersId, subjectList.size());
    }

    private PoiExcelInfo getExcelInfo(PapersImportReqeust request) {
        PoiExcelInfo info = new PoiExcelInfo();
        byte[] bytes = Base64.decodeBase64(request.getPaperFile());
        info.setExcelInput(new ByteArrayInputStream(bytes));
        info.setFromRow(2);
        info.setToRow(-1);
        info.setColSize(12);
        return info.setXlsx(true);
    }

    @Override
    public PapersImportSubjectModel row2Model(int row, Map<Integer, Cell> rowData) {
        PapersImportSubjectModel model = new PapersImportSubjectModel();
        try {
            model.setList(row - 1);
            model.setSubject(getCellValue(rowData, 1));
            Integer type = Integer.valueOf(getCellValue(rowData, 2));
            model.setType(type);
            model.setScore(Integer.valueOf(getCellValue(rowData, 3)));
            String correctNumStr = getCellValue(rowData, 4);
            correctNumStr = validCorrectNum(correctNumStr);
            if (hasText(correctNumStr)){
                model.setCorrectNum(correctNumStr);
            }else {
                return model.setError(toLogString("第{}行答案序号不合法", row - 1));
            }
        } catch (InterruptedException e) {
            return model.setError(toLogString("第{}行{}", row - 1, e));
        }
        List<PapersImportOptionModel> optionModels = Lists.newArrayList();
        for (int i = 0; i < 7; i++) {
            try {
                PapersImportOptionModel optionModel = new PapersImportOptionModel();
                optionModel.setList(i + 1);
                optionModel.setContent(getCellValue(rowData, 5 + i));
                optionModels.add(optionModel);
            } catch (InterruptedException e) {
                break;
            }
        }
        model.setOptionModels(optionModels);
        return model;
    }

    private String getCellValue(Map<Integer, Cell> rowData, int index) throws InterruptedException {
        Cell cell = rowData.get(index);
        switch (cell.getCellType()) {
            case NUMERIC:
                return String.valueOf(Double.valueOf(cell.getNumericCellValue()).intValue());
            case STRING:
                return cell.getStringCellValue();
            case BLANK:
                throw new InterruptedException("必填单元格为空");
            default:
                throw new InterruptedException("格式不正确");
        }
    }

    private String validCorrectNum(String correctNum){
        String[] correctNums = correctNum.split("\\|");
        List<Integer> list = Lists.newArrayList();
        for (String num : correctNums) {
            try {
                list.add(Integer.valueOf(num));
            } catch (NumberFormatException e) {
                return "答案序号仅允许填写数字";
            }
        }
        Collections.sort(list);
        StringBuilder newCorrectNum = new StringBuilder("");
        for (Integer num : list) {
            newCorrectNum.append(num + "|");
        }
        return newCorrectNum.deleteCharAt(newCorrectNum.length() - 1).toString();
    }

    @Override
    public String view(PapersViewRequest request){
        CommonResponse response = new CommonResponse();
        Optional<Papers> op = papersRepo.findById(request.getId());
        if (op.isEmpty()){
            return response.setError(ErrorType.NOPAPERS);
        }else {
            if (op.get().getStatus() == PapersStatus.WDR){
                return response.setError(ErrorType.NOIMPORT);
            }
        }
        PapersViewResponse viewResponse = new PapersViewResponse();
        List<PapersViewModel> subjectList = Lists.newArrayList();
        //分页查询
        PapersSubjectPageRequest pageRequest = new PapersSubjectPageRequest();
        BeanUtils.copyProperties(request, pageRequest);
        pageRequest.setPapersId(request.getId());
        Pagination page = subjectRepo.page(pageRequest);
        BeanUtils.copyProperties(page, viewResponse);
        for (PapersSubject subject : page.getResult(PapersSubject.class)) {
            PapersViewModel model = new PapersViewModel();
            model.setIndex(subject.getList());
            model.setStem(subject.getSubject());
            model.setType(subject.getType().getCode());
            model.setCorrectNum(subject.getCorrectNum());
            List<PapersOption> options = optionRepo.listBySubjectId(subject.getId());
            List<String> optionList = options.stream().map(PapersOption::getContent).collect(Collectors.toList());
            model.setOptionList(optionList);
            subjectList.add(model);
        }
        viewResponse.setSubjectList(subjectList);
        response.setData(viewResponse);
        return response.toJson();
    }

    @Override
    public String push(PapersPushRequest request){
        CommonResponse response = new CommonResponse();
        List<PapersPush> pushList = Lists.newArrayList();
        List<String> rankCodeList = request.getRankCodeList();
        //查询已推送数据
        List<PapersPush> pushedList = pushRepo.listByPapersId(request.getPaperId());
        Map<String, PapersPush> pushedMap = pushedList.stream()
                .collect(Collectors.toMap(PapersPush::getAgentCode, Function.identity()));
        for (String rankCode : rankCodeList) {
            try {
                PositionsClass.valueOf(rankCode);
            } catch (IllegalArgumentException e) {
                logger.error("无此类型{}", rankCode);
                return response.setError(ErrorType.CONVERT);
            }
            List<MarketingManpower> manpowers = manpowerRepo.listByAgentGrade(rankCode);
            for (MarketingManpower manpower : manpowers) {
                if(null != pushedMap.get(manpower.getAgentCode())) continue;
                PapersPush entity = new PapersPush();
                entity.setAgentCode(manpower.getAgentCode());
                entity.setStaffName(manpower.getName());
                entity.setPapersId(request.getPaperId());
                entity.setInsertTime(LocalDateTime.now());
                pushList.add(entity);
            }
        }
        //保存实体
        persistPush(request, pushList);
        try {
            addSysLog("推送试卷" + request.getPaperId() + "至职级" + JsonTools.toJsonStr(rankCodeList), request.getToken(), request.getPaperId());
        } catch (IOException e) {
            logger.error("", e);
        }
        response.setMessage("推送试卷成功！");
        return response.toJson();
    }

    @Transactional(rollbackFor=Exception.class)
    private void persistPush(PapersPushRequest request, List<PapersPush> pushList) {
        pushRepo.persistAll(pushList);
        papersRepo.updateStatus(request.getPaperId(), PapersStatus.YTS, LocalDateTime.now());
    }

    @Override
    public String resultView(PapersIdRequest request){
        CommonResponse response = new CommonResponse();
        List<PapersPush> pushes = pushRepo.listByPapersId(request.getId());
        pushes = pushes.stream().filter(s -> s.getAnswerType() == PapersAnswerType.YDT).collect(Collectors.toList());
        if (isEmpty(pushes)){
            return response.setError(ErrorType.VALID, "目前暂无人答题");
        }
        PapersResultResponse resultResponse = new PapersResultResponse();
        InputStream is = FileTools.getResourcesFileInputStream("template/papersAnswerTemplate.xlsx");
        try {
            ByteArrayOutputStream op = ExcelTemplateHelper.generateExcel(is, true, request, this);
            byte[] data = op.toByteArray();
            String resultFileStr = new String(Base64.encodeBase64(data));
            resultResponse.setResultFile(resultFileStr);
        } catch (Exception e) {
            logger.error("", e);
            return response.setError(ErrorType.CONVERT);
        }
        response.setData(resultResponse);
        return response.toJson();
    }

    @Override
    public void fillWorkbook(Workbook workbook, PapersIdRequest data) throws Exception {
        //处理试卷名称
        Optional<Papers> op = papersRepo.findById(data.getId());
        Sheet sheet = workbook.getSheetAt(0);
        Cell cell = ExcelTemplateHelper.getCell(sheet, 1, 0);
        cell.setCellValue("试卷名称：" + op.get().getName());

        List<PapersPush> pushList = pushRepo.listByPapersIdYDT(data.getId());
        List<PapersSubject> subjects = subjectRepo.listByPapersId(data.getId());
        List<String> pushIdList = pushList.stream().map(PapersPush::getId).collect(Collectors.toList());
        List<PapersPushAnswer> answerList = answerRepo.listByPushIds(pushIdList);
        Map<String, PapersPushAnswer> answerMaps = answerList.stream().collect(Collectors.toMap(PapersPushAnswer::getSubjectId, Function.identity()));
        for (int i = 0; i < pushList.size(); i++) {
            if (i == 0){//处理表头
                int index = 1;
                int colInterval = 0;
                for (PapersSubject subject : subjects) {
                    regionCells(workbook, sheet, cell, "题目" + index,
                            2, 2, 4 + colInterval, 5 + colInterval);
                    cell = ExcelTemplateHelper.getCell(sheet, 3, 4 + colInterval);
                    cell.setCellValue("选择");
                    setCellStyle(cell, workbook, true, false);
                    cell = ExcelTemplateHelper.getCell(sheet, 3, 5 + colInterval);
                    cell.setCellValue("答案");
                    setCellStyle(cell, workbook, true, false);
                    colInterval += 2;
                    index++;
                }
            }
            //序号
            cell = ExcelTemplateHelper.getCell(sheet, 4 + i, 0);
            cell.setCellValue(i + 1);
            setCellStyle(cell, workbook, false, false);
            //工号
            cell = ExcelTemplateHelper.getCell(sheet, 4 + i, 1);
            cell.setCellValue(pushList.get(i).getAgentCode());
            setCellStyle(cell, workbook, false, false);
            //姓名
            cell = ExcelTemplateHelper.getCell(sheet, 4 + i, 2);
            cell.setCellValue(pushList.get(i).getStaffName());
            setCellStyle(cell, workbook, false, false);
            //得分
            cell = ExcelTemplateHelper.getCell(sheet, 4 + i, 3);
            cell.setCellValue(pushList.get(i).getScore());
            setCellStyle(cell, workbook, false, false);
            //题目处理
            int colInterval = 0;
            for (PapersSubject subject : subjects) {
                PapersPushAnswer answer = answerMaps.get(subject.getId());
                boolean error = !answer.getAnswer().equals(subject.getCorrectNum());
                cell = ExcelTemplateHelper.getCell(sheet, 3, 4 + colInterval);
                cell.setCellValue(answer.getAnswer());
                setCellStyle(cell, workbook, true, error);
                cell = ExcelTemplateHelper.getCell(sheet, 3, 5 + colInterval);
                cell.setCellValue(subject.getCorrectNum());
                setCellStyle(cell, workbook, true, error);
                colInterval += 2;
            }
        }
    }

    private void regionCells(Workbook workbook, Sheet sheet, Cell cell, String cellValue,
                                int firstRow, int lastRow, int firstCol, int lastCol){
        //创建其余空白表格，不创建会报错
        for (int i = firstRow; i <= lastRow; i++) {
            for (int j = firstCol; j <= lastCol; j++) {
                cell = ExcelTemplateHelper.getCell(sheet, i, j);
                if (i == firstRow && j == firstCol){
                    cell.setCellValue(cellValue);
                }
            }
        }

        CellRangeAddress region = new CellRangeAddress(firstRow, lastRow, firstCol, lastCol);
        sheet.addMergedRegion(region);
        CellStyle cellType = setCellStyle(cell, workbook, true, false);
        for (int i = region.getFirstRow(); i <= region.getLastRow(); i++) {
            Row row = sheet.getRow(i);
            for (int j = region.getFirstColumn(); j <= region.getLastColumn(); j++) {
                cell = row.getCell(j);
                cell.setCellStyle(cellType);
            }
        }
    }

    private CellStyle setCellStyle(Cell cell, Workbook workbook, boolean bold, boolean error){
        CellStyle cellStyle = workbook.createCellStyle();
        cellStyle.cloneStyleFrom(cell.getCellStyle());
        cellStyle.setAlignment(HorizontalAlignment.CENTER);//水平居中
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);//垂直居中
        Font font = workbook.createFont();
        font.setBold(bold);
        cellStyle.setFont(font);
        //设置边框线
        cellStyle.setBorderBottom(BorderStyle.THIN);
        cellStyle.setBorderLeft(BorderStyle.THIN);
        cellStyle.setBorderTop(BorderStyle.THIN);
        cellStyle.setBorderRight(BorderStyle.THIN);
        if (error) {
            cellStyle.setFillForegroundColor(IndexedColors.RED.getIndex());
        }
        return cellStyle;
    }
}
