package hx.service.manage.manage.staff;

import hx.base.core.dao.dict.ErrorType;
import hx.base.core.dao.dict.TrainStatus;
import hx.base.core.dao.entity.MarketingManpower;
import hx.base.core.dao.entity.staff.Train;
import hx.base.core.dao.entity.staff.TrainPeople;
import hx.base.core.dao.repo.jpa.MarketingManpowerRepo;
import hx.base.core.dao.repo.jpa.staff.TrainPeopleRepo;
import hx.base.core.dao.repo.jpa.staff.TrainRepo;
import hx.base.core.dao.repo.request.common.Pagination;
import hx.base.core.dao.repo.request.staff.TrainPageRequest;
import hx.base.core.dao.repo.request.staff.TrainPeoplePageRequest;
import hx.base.core.manage.model.CommonResponse;
import hx.base.core.manage.poi.ExcelReadRowHandler;
import hx.base.core.manage.poi.ExcelTemplateHelper;
import hx.base.core.manage.poi.PoiExcelInfo;
import hx.base.core.manage.poi.WorkbookWithDataHandler;
import hx.base.core.manage.tools.FileTools;
import hx.base.core.manage.tools.MyTimeTools;
import hx.service.manage.manage.AbstractExcelManager;
import hx.service.manage.manage.model.CommonExportResponse;
import hx.service.manage.manage.model.CommonRequest;
import hx.service.manage.manage.model.CommonTemplateResponse;
import hx.service.manage.manage.model.staff.train.*;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.compress.utils.Lists;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * @name: TrainManagerImpl
 * @description: 参训人员管理ManagerImpl
 * @author: huojiajin
 * @time: 2020/11/12 11:51
 */
@Service
public class TrainManagerImpl extends AbstractExcelManager implements TrainManager,
        ExcelReadRowHandler<TrainStaffImportModel>, WorkbookWithDataHandler<TrainPeopleQueryRequest> {

    @Autowired
    private TrainRepo trainRepo;
    @Autowired
    private TrainPeopleRepo trainPeopleRepo;
    @Autowired
    private MarketingManpowerRepo manpowerRepo;

    @Override
    public String query(TrainQueryRequest request){
        CommonResponse response = new CommonResponse();
        TrainPageRequest pageRequest = new TrainPageRequest();
        BeanUtils.copyProperties(request, pageRequest);
        pageRequest.setHasDelete(false);
        if (null != request.getStatus()) {//拼装培训状态
            TrainStatus status;
            try {
                status = TrainStatus.fromCode(request.getStatus());
            } catch (Exception e) {
                logger.error("", e);
                return response.setError(ErrorType.CONVERT);
            }
            pageRequest.setStatus(status);
        }
        if (hasText(request.getMonth())) {
            LocalDate queryDate;
            try {
                queryDate = LocalDate.parse(request.getMonth() + "-01", DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            } catch (Exception e) {
                logger.error("", e);
                return response.setError(ErrorType.VALID, "日期格式错误");
            }
            pageRequest.setTrainTimeStart(queryDate.atStartOfDay());
            pageRequest.setTrainTimeEnd(queryDate.plusMonths(1).atStartOfDay());
        }
        Pagination page = trainRepo.page(pageRequest);
        page.convertResult(this::convert);
        response.setData(page);
        return response.toJson();
    }

    private TrainQueryResponse convert(Train train){
        TrainQueryResponse response = new TrainQueryResponse();
        BeanUtils.copyProperties(train, response);
        response.setStatus(train.getStatus().getCode());
        response.setTrainTime(MyTimeTools.timeToStr(train.getTrainTime()));
        return response;
    }

    @Override
    public String add(TrainAddRequest request){
        CommonResponse response = new CommonResponse();
        Train train = new Train();
        BeanUtils.copyProperties(request, train, new String[]{"id"});
        train.setUpdateTime(LocalDateTime.now());
        train.setInsertTime(LocalDateTime.now());
        try {
            train.setTrainTime(LocalDateTime.parse(request.getTrainTime(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        } catch (Exception e) {
            logger.error("", e);
            return response.setError(ErrorType.CONVERT, "日期格式错误");
        }
        trainRepo.persist(train);
        return response.toJson();
    }

    @Override
    public String delete(TrainIdRequest request){
        trainRepo.updateDelete(request.getTrainId());
        return new CommonResponse<>().toJson();
    }

    @Override
    public String downloadTemplate(CommonRequest request){
        CommonResponse response = new CommonResponse();
        CommonTemplateResponse templateResponse = new CommonTemplateResponse();
        InputStream fis = FileTools.getResourcesFileInputStream("template/trainStaffTemplate.xlsx");
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
    @Transactional
    public String importPeople(TrainImportRequest request){
        CommonResponse response = new CommonResponse();
        String trainId = request.getTrainId();
        //查询场次
        Optional<Train> op = trainRepo.findById(trainId);
        if (!op.isPresent() || op.get().isHasDelete()){
            return response.setError(ErrorType.NOTRAIN);
        }
        //处理并读取excel
        PoiExcelInfo excelInfo = getExcelInfo(request);
        List<TrainStaffImportModel> importModels;
        try {
            importModels = ExcelTemplateHelper.readData(excelInfo, this);
        } catch (Exception e) {
            logger.error("", e);
            return response.setError(ErrorType.CONVERT);
        }
        //拼装实体
        List<TrainPeople> staffList = Lists.newArrayList();
        int row = 1;
        for (TrainStaffImportModel model : importModels) {
            MarketingManpower byAgentCode = manpowerRepo.findByAgentCode(model.getAgentCode().trim());
            if (null == byAgentCode){
                logger.error("读取参训人员名单失败，行数{}，错误信息：{}", row, "未找到该人员");
                return response.setError(ErrorType.CONVERT,
                        toLogString("第{}行读取失败：{}", row, "未找到该人员"));
            }
            TrainPeople trainPeople = new TrainPeople();
            trainPeople.setTrainId(trainId);
            trainPeople.setMobile(model.getMobile());
            trainPeople.setName(byAgentCode.getName());
            trainPeople.setAgentCode(byAgentCode.getAgentCode());
            trainPeople.setCampName(byAgentCode.getDeptName1());
            trainPeople.setCampCode(byAgentCode.getDeptCode1());
            trainPeople.setSectionName(byAgentCode.getDeptName3());
            trainPeople.setSectionCode(byAgentCode.getDeptCode3());
            trainPeople.setGroupName(byAgentCode.getDeptName4());
            trainPeople.setGroupCode(byAgentCode.getDeptCode4());
            trainPeople.setInsertTime(LocalDateTime.now());
            staffList.add(trainPeople);
            row++;
        }
        if (isEmpty(staffList)){
            return response.setError(ErrorType.CONVERT, "未找到可导入人员");
        }
        trainRepo.updateImport(staffList.size(),LocalDateTime.now(), trainId);
        trainPeopleRepo.persistAll(staffList);
        return response.toJson();
    }

    private PoiExcelInfo getExcelInfo(TrainImportRequest request) {
        PoiExcelInfo info = new PoiExcelInfo();
        byte[] bytes = Base64.decodeBase64(request.getTrainFile());
        info.setExcelInput(new ByteArrayInputStream(bytes));
        info.setFromRow(1);
        info.setToRow(-1);
        info.setColSize(4);
        return info.setXlsx(true);
    }

    @Override
    public TrainStaffImportModel row2Model(int row, Map<Integer, Cell> rowData) throws Exception {
        TrainStaffImportModel model = new TrainStaffImportModel();
        try {
            model.setAgentCode(String.valueOf(getCellValue(rowData, 1)));
            model.setMobile(String.valueOf(getCellValue(rowData, 3)));
        } catch (Exception e) {
            logger.error("", e);
            throw new Exception("第" + row + "行数据读取错误");
        }
        return model;
    }


    @Override
    public String peopleQuery(TrainPeopleQueryRequest request){
        CommonResponse response = new CommonResponse();
        TrainPeoplePageRequest pageRequest = new TrainPeoplePageRequest();
        BeanUtils.copyProperties(request, pageRequest);
        Pagination page = trainPeopleRepo.page(pageRequest);
        response.setData(page);
        return response.toJson();
    }

    @Override
    public String exportPeople(TrainPeopleQueryRequest request){
        CommonResponse response = new CommonResponse();
        //校验培训场次状态
        Train train = trainRepo.findById(request.getTrainId()).orElse(null);
        if (null == train || train.isHasDelete() || train.getStatus() == TrainStatus.WDR){
            return response.setError(ErrorType.CONVERT, "该场次无法导出人员信息");
        }
        CommonExportResponse resultResponse = new CommonExportResponse();
        InputStream is = FileTools.getResourcesFileInputStream("template/trainStaffExportTemplate.xlsx");
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
    public void fillWorkbook(Workbook workbook, TrainPeopleQueryRequest data) throws Exception {
        Train train = trainRepo.findById(data.getTrainId()).orElse(null);
        Sheet sheet = workbook.getSheetAt(0);
        Cell cell = ExcelTemplateHelper.getCell(sheet, 1, 0);
        cell.setCellValue("培训场次：" + train.getName());

        cell = ExcelTemplateHelper.getCell(sheet, 1, 3);
        cell.setCellValue("培训时间：" + MyTimeTools.timeToStr(train.getTrainTime()));

        //查询人员
        TrainPeoplePageRequest pageRequest = new TrainPeoplePageRequest();
        BeanUtils.copyProperties(data, pageRequest);
        List<TrainPeople> trainPeopleList = trainPeopleRepo.list(pageRequest);
        int i = 0;
        for (TrainPeople trainPeople : trainPeopleList) {
            //序号
            cell = ExcelTemplateHelper.getCell(sheet, 3 + i, 0);
            cell.setCellValue(i + 1);
            setCellStyle(cell, workbook, false, false);
            //营销员编码
            cell = ExcelTemplateHelper.getCell(sheet, 3 + i, 1);
            cell.setCellValue(trainPeople.getAgentCode());
            setCellStyle(cell, workbook, false, false);
            //姓名
            cell = ExcelTemplateHelper.getCell(sheet, 3 + i, 2);
            cell.setCellValue(trainPeople.getName());
            setCellStyle(cell, workbook, false, false);
            //手机号码
            cell = ExcelTemplateHelper.getCell(sheet, 3 + i, 3);
            cell.setCellValue(trainPeople.getMobile());
            setCellStyle(cell, workbook, false, false);
            //营服名称
            cell = ExcelTemplateHelper.getCell(sheet, 3 + i, 4);
            cell.setCellValue(trainPeople.getCampName());
            setCellStyle(cell, workbook, false, false);
            //部名称
            cell = ExcelTemplateHelper.getCell(sheet, 3 + i, 5);
            cell.setCellValue(trainPeople.getSectionName());
            setCellStyle(cell, workbook, false, false);
            //组名称
            cell = ExcelTemplateHelper.getCell(sheet, 3 + i, 6);
            cell.setCellValue(trainPeople.getGroupName());
            setCellStyle(cell, workbook, false, false);
            i++;
        }
    }
}
