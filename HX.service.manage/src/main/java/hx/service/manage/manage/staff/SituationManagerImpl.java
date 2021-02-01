package hx.service.manage.manage.staff;

import hx.base.core.dao.dict.ErrorType;
import hx.base.core.dao.entity.MarketingManpower;
import hx.base.core.dao.entity.StarRating;
import hx.base.core.dao.repo.jpa.AttendanceRepo;
import hx.base.core.dao.repo.jpa.BusinessRepo;
import hx.base.core.dao.repo.jpa.MarketingManpowerRepo;
import hx.base.core.dao.repo.jpa.StarRatingRepo;
import hx.base.core.dao.repo.request.MarketingManpowerPageRequest;
import hx.base.core.dao.repo.request.common.Pagination;
import hx.base.core.manage.model.CommonResponse;
import hx.base.core.manage.poi.ExcelTemplateHelper;
import hx.base.core.manage.poi.WorkbookWithDataHandler;
import hx.base.core.manage.tools.FileTools;
import hx.base.core.manage.tools.MyTimeTools;
import hx.service.manage.manage.AbstractExcelManager;
import hx.service.manage.manage.model.CommonExportResponse;
import hx.service.manage.manage.model.staff.situation.SituationAttendModel;
import hx.service.manage.manage.model.staff.situation.SituationQueryRequest;
import hx.service.manage.manage.model.staff.situation.SituationQueryResponse;
import hx.service.manage.manage.model.staff.situation.SituationStadpremModel;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.compress.utils.Lists;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @ClassName: SituationManagerImpl
 * @Description: 人员情况统计Manager
 * @Author huojiajin
 * @Date 2021/1/29 0:38
 * @Version 1.0
 **/
@Service
public class SituationManagerImpl extends AbstractExcelManager implements SituationManager, WorkbookWithDataHandler<SituationQueryRequest> {

    @Autowired
    private MarketingManpowerRepo manpowerRepo;
    @Autowired
    private BusinessRepo businessRepo;
    @Autowired
    private AttendanceRepo attendanceRepo;
    @Autowired
    private StarRatingRepo starRatingRepo;

    @Override
    public String query(SituationQueryRequest request){
        CommonResponse response = new CommonResponse();
        MarketingManpowerPageRequest pageRequest = new MarketingManpowerPageRequest();
        BeanUtils.copyProperties(request, pageRequest);
        pageRequest.setDeptCode1(request.getCampCode());
        pageRequest.setDeptName2(request.getChiefName());
        pageRequest.setDeptName3(request.getSectionName());
        pageRequest.setDeptName4(request.getGroupName());
        pageRequest.setAgentCode(request.getEmployeeNum());
        Pagination page = manpowerRepo.page(pageRequest);
        page.convertResult(this::convertModel);
        response.setData(page);
        return response.toJson();
    }

    private SituationQueryResponse convertModel(MarketingManpower manpower){
        SituationQueryResponse model = new SituationQueryResponse();
        String agentCode = manpower.getAgentCode();
        model.setEmployeeNum(agentCode);
        model.setName(manpower.getName());
        model.setCampName(manpower.getDeptName1());
        model.setChiefName(manpower.getDeptName2());
        model.setSectionName(manpower.getDeptName3());
        model.setGroupName(manpower.getDeptName4());
        model.setGradeName(manpower.getAgentGradeName());
        model.setEntryTime(manpower.getEmployDate().toString());
        //获取星级
        StarRating starRating = starRatingRepo.findByAgentCode(agentCode);
        if (starRating == null){
            model.setStar("FH00");
        }else {
            model.setStar(starRating.getFhagentGrade());
        }
        //业绩
        List<SituationStadpremModel> stadpremList = Lists.newArrayList();
        //出勤
        List<SituationAttendModel> attendList = Lists.newArrayList();
        List<LocalDate> threeMonths = MyTimeTools.getThreeMonths(LocalDate.now());
        for (LocalDate month : threeMonths) {
            //业绩
            SituationStadpremModel stadpremModel = new SituationStadpremModel();
            stadpremModel.setMonth(month.getMonthValue());
            LocalDate startOfMonth = LocalDate.of(month.getYear(), month.getMonth(), 1);
            Double stadpremSum = businessRepo.sumByAgentCode(agentCode, startOfMonth, startOfMonth.plusMonths(1));
            if (stadpremSum == null){
                stadpremSum = 0d;
            }
            stadpremModel.setStadprem(String.valueOf(stadpremSum));
            stadpremList.add(stadpremModel);
            //出勤
            SituationAttendModel attendModel = new SituationAttendModel();
            attendModel.setMonth(month.getMonthValue());
            Long attendNum = attendanceRepo.countByAgentCode(agentCode, startOfMonth, startOfMonth.plusMonths(1));
            attendModel.setAttendNum(String.valueOf(attendNum));
            attendList.add(attendModel);
        }
        model.setStadpremList(stadpremList);
        model.setAttendList(attendList);
        return model;
    }

    @Override
    public String export(SituationQueryRequest request){

        CommonResponse response = new CommonResponse();
        if (!hasText(request.getCampCode())){
            return response.setError(ErrorType.VALID, "请选择营服");
        }
        CommonExportResponse resultResponse = new CommonExportResponse();
        InputStream is = FileTools.getResourcesFileInputStream("template/situationExportTemplate.xlsx");
        try {
            ByteArrayOutputStream op = ExcelTemplateHelper.generateExcel(is, true, request, this);
            byte[] data = op.toByteArray();
            String resultFileStr = new String(Base64.encodeBase64(data));
            resultResponse.setResultFile(resultFileStr);
        } catch (Exception e) {
            logger.error("", e);
            return response.setError(ErrorType.CONVERT, e.getMessage());
        }
        response.setData(resultResponse);
        return response.toJson();
    }

    @Override
    public void fillWorkbook(Workbook workbook, SituationQueryRequest request) throws Exception {
        //查询数据结果
        MarketingManpowerPageRequest pageRequest = new MarketingManpowerPageRequest();
        BeanUtils.copyProperties(request, pageRequest);
        pageRequest.setDeptCode1(request.getCampCode());
        pageRequest.setDeptName2(request.getChiefName());
        pageRequest.setDeptName3(request.getSectionName());
        pageRequest.setDeptName4(request.getGroupName());
        pageRequest.setAgentCode(request.getEmployeeNum());
        List<MarketingManpower> marketingManpowerList = manpowerRepo.list(pageRequest);
        List<SituationQueryResponse> result = marketingManpowerList.stream().map(this::convertModel).collect(Collectors.toList());

        if (isEmpty(result)){
            throw new RuntimeException("未查询到相关信息");
        }

        //初始化表格
        Sheet sheet = workbook.getSheetAt(0);
        Cell cell = ExcelTemplateHelper.getCell(sheet, 0, 0);
        //创建表头
        String titleValue = "人员情况统计";
        int lastcol = 9 + result.get(0).getStadpremList().size() + result.get(0).getAttendList().size();
        regionCells(workbook, sheet, cell, titleValue, 0, 0, 0, lastcol);
        //添加查询条件
        StringBuilder searchValue = new StringBuilder();
        if (hasText(request.getCampCode())){
            searchValue.append("营服名称：" + request.getCampName());
        }
        if (hasText(request.getChiefName())){
            searchValue.append("总监名称：" + request.getChiefName());
        }
        if (hasText(request.getSectionName())){
            searchValue.append("部名称：" + request.getSectionName());
        }
        if (hasText(request.getGroupName())){
            searchValue.append("组名称：" + request.getGroupName());
        }
        if (hasText(request.getEmployeeNum())){
            searchValue.append("工号：" + request.getEmployeeNum());
        }
        regionCells(workbook, sheet, cell, searchValue.toString(), 1, 1, 0, lastcol);

        //添加数据
        for (int i = 0; i < result.size(); i++) {
            SituationQueryResponse model = result.get(i);
            if (i == 0){
                //创建属性列表
                regionCells(workbook, sheet, cell, "序号", 2, 3, 0, 0);
                regionCells(workbook, sheet, cell, "工号", 2, 3, 1, 1);
                regionCells(workbook, sheet, cell, "人员名称", 2, 3, 2, 2);
                regionCells(workbook, sheet, cell, "营服名称", 2, 3, 3, 3);
                regionCells(workbook, sheet, cell, "总监名称", 2, 3, 4, 4);
                regionCells(workbook, sheet, cell, "部名称", 2, 3, 5, 5);
                regionCells(workbook, sheet, cell, "组名称", 2, 3, 6, 6);
                regionCells(workbook, sheet, cell, "星级", 2, 3, 7, 7);
                regionCells(workbook, sheet, cell, "职级名称", 2, 3, 8, 8);
                regionCells(workbook, sheet, cell, "入职时间", 2, 3, 9, 9);
                //业绩
                regionCells(workbook, sheet, cell, "业绩", 2, 3, 10, 12);
                for (int j = 0; j < model.getStadpremList().size(); j++) {
                    SituationStadpremModel stadpremModel = model.getStadpremList().get(j);
                    cell = ExcelTemplateHelper.getCell(sheet, 3, 10 + j);
                    setCellValueAndStyle(cell, workbook, stadpremModel.getMonth() + "月", true);
                }
                regionCells(workbook, sheet, cell, "出勤", 2, 3, 13, 15);
                //出勤
                for (int K = 0; K < model.getStadpremList().size(); K++) {
                    SituationAttendModel attendModel = model.getAttendList().get(K);
                    cell = ExcelTemplateHelper.getCell(sheet, 3, 13 + K);
                    setCellValueAndStyle(cell, workbook, attendModel.getMonth() + "月", true);
                }
            }
            //序号
            cell = ExcelTemplateHelper.getCell(sheet, 4 + i, 0);
            setCellValueAndStyle(cell, workbook, String.valueOf(i + 1), false);
            //工号
            cell = ExcelTemplateHelper.getCell(sheet, 4 + i, 1);
            setCellValueAndStyle(cell, workbook, model.getEmployeeNum(), false);
            //人员名称
            cell = ExcelTemplateHelper.getCell(sheet, 4 + i, 2);
            setCellValueAndStyle(cell, workbook, model.getName(), false);
            //营服名称
            cell = ExcelTemplateHelper.getCell(sheet, 4 + i, 3);
            setCellValueAndStyle(cell, workbook, model.getCampName(), false);
            //总监名称
            cell = ExcelTemplateHelper.getCell(sheet, 4 + i, 4);
            setCellValueAndStyle(cell, workbook, model.getChiefName(), false);
            //部名称
            cell = ExcelTemplateHelper.getCell(sheet, 4 + i, 5);
            setCellValueAndStyle(cell, workbook, model.getSectionName(), false);
            //组名称
            cell = ExcelTemplateHelper.getCell(sheet, 4 + i, 6);
            setCellValueAndStyle(cell, workbook, model.getGroupName(), false);
            //星级
            cell = ExcelTemplateHelper.getCell(sheet, 4 + i, 7);
            setCellValueAndStyle(cell, workbook, model.getStar(), false);
            //职级名称
            cell = ExcelTemplateHelper.getCell(sheet, 4 + i, 8);
            setCellValueAndStyle(cell, workbook, model.getGradeName(), false);
            //入职时间
            cell = ExcelTemplateHelper.getCell(sheet, 4 + i, 9);
            setCellValueAndStyle(cell, workbook, model.getEntryTime(), false);
            //业绩
            for (int j = 0; j < model.getStadpremList().size(); j++) {
                SituationStadpremModel stadpremModel = model.getStadpremList().get(j);
                cell = ExcelTemplateHelper.getCell(sheet, 4 + i, 10 + j);
                setCellValueAndStyle(cell, workbook, stadpremModel.getStadprem(), false);
            }
            //出勤
            for (int k = 0; k < model.getStadpremList().size(); k++) {
                SituationAttendModel attendModel = model.getAttendList().get(k);
                cell = ExcelTemplateHelper.getCell(sheet, 4 + i, 13 + k);
                setCellValueAndStyle(cell, workbook, attendModel.getAttendNum(), false);
            }
        }
    }
}
