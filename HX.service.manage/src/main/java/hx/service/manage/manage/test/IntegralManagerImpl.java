package hx.service.manage.manage.test;

import hx.base.core.dao.dict.acl.ErrorType;
import hx.base.core.dao.entity.test.integral.Integral;
import hx.base.core.dao.repo.jpa.test.integral.IntegralRepo;
import hx.base.core.dao.repo.request.common.Pagination;
import hx.base.core.dao.repo.request.test.IntegralPageRequest;
import hx.base.core.manage.model.CommonResponse;
import hx.base.core.manage.poi.ExcelTemplateHelper;
import hx.base.core.manage.poi.WorkbookWithDataHandler;
import hx.base.core.manage.tools.FileTools;
import hx.service.manage.manage.common.AbstractManager;
import hx.service.manage.model.test.integral.IntegralExportResponse;
import hx.service.manage.model.test.integral.IntegralQueryModel;
import hx.service.manage.model.test.integral.IntegralQueryRequest;
import org.apache.commons.codec.binary.Base64;
import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.List;

/**
 * @ClassName IntegralManagerImpl
 * @Description 积分管理ManagerImpl
 * @Author HuoJiaJin
 * @Date 2020/6/23 19:43
 * @Version 1.0
 **/
@Service
public class IntegralManagerImpl extends AbstractManager implements IntegralManager, WorkbookWithDataHandler<IntegralQueryRequest> {

    @Autowired
    private IntegralRepo repo;

    @Override
    public String query(IntegralQueryRequest request){
        CommonResponse response = new CommonResponse();
        if (!hasText(request.getMonth())){
            response.setError(ErrorType.VALID, "请选择月份");
        }
        IntegralPageRequest pageRequest = new IntegralPageRequest();
        BeanUtils.copyProperties(request, pageRequest);
        pageRequest.setMonth(request.getMonth());
        pageRequest.setAgentCode(request.getEmployeeNum());
        Pagination page = repo.page(pageRequest);
        page.convertResult(this::convert);
        response.setData(page);
        return response.toJson();
    }

    private IntegralQueryModel convert(Integral integral){
        IntegralQueryModel model = new IntegralQueryModel();
        BeanUtils.copyProperties(integral, model);
        model.setEmployeeNum(integral.getAgentCode());
        return model;
    }

    @Override
    public String export(IntegralQueryRequest request){
        CommonResponse response = new CommonResponse();
        IntegralExportResponse exportResponse = new IntegralExportResponse();
        InputStream is = FileTools.getResourcesFileInputStream("template/test/integralTemplate.xlsx");
        try {
            ByteArrayOutputStream op = ExcelTemplateHelper.generateExcel(is, true, request, this);
            byte[] data = op.toByteArray();
            String resultFileStr = new String(Base64.encodeBase64(data));
            exportResponse.setIntegralFile(resultFileStr);
        } catch (Exception e) {
            logger.error("", e);
            return response.setError(ErrorType.CONVERT);
        }
        response.setData(exportResponse);
        return response.toJson();
    }

    @Override
    public void fillWorkbook(Workbook workbook, IntegralQueryRequest data) throws Exception {
        IntegralPageRequest pageRequest = new IntegralPageRequest();
        pageRequest.setMonth(data.getMonth());
        pageRequest.setAgentCode(data.getEmployeeNum());
        List<Integral> integralList = repo.list(pageRequest);
        //查询月份
        Sheet sheet = workbook.getSheetAt(0);
        Cell cell = ExcelTemplateHelper.getCell(sheet, 1, 0);
        cell.setCellValue("查询月份：" + data.getMonth());
        int row = 3;
        for (Integral model : integralList) {
            //序号
            cell = ExcelTemplateHelper.getCell(sheet, row, 0);
            cell.setCellValue(row - 2);
            setCellStyle(cell, workbook, false);
            //工号
            cell = ExcelTemplateHelper.getCell(sheet, row, 1);
            cell.setCellValue(model.getAgentCode());
            setCellStyle(cell, workbook, false);
            //姓名
            cell = ExcelTemplateHelper.getCell(sheet, row, 2);
            cell.setCellValue(model.getName());
            setCellStyle(cell, workbook, false);
            //总分
            cell = ExcelTemplateHelper.getCell(sheet, row, 3);
            cell.setCellValue(model.getAllNum());
            setCellStyle(cell, workbook, false);
            //每日签到积分
            cell = ExcelTemplateHelper.getCell(sheet, row, 4);
            cell.setCellValue(model.getSignInNum());
            setCellStyle(cell, workbook, false);
            //资料学习积分
            cell = ExcelTemplateHelper.getCell(sheet, row, 5);
            cell.setCellValue(model.getCourseNum());
            setCellStyle(cell, workbook, false);
            //参与考试积分
            cell = ExcelTemplateHelper.getCell(sheet, row, 6);
            cell.setCellValue(model.getTestNum());
            setCellStyle(cell, workbook, false);
            //每月开单积分
            cell = ExcelTemplateHelper.getCell(sheet, row, 7);
            cell.setCellValue(model.getPerformNum());
            setCellStyle(cell, workbook, false);
            row++;
        }
    }

    private CellStyle setCellStyle(Cell cell, Workbook workbook, boolean bold){
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
        return cellStyle;
    }
}
