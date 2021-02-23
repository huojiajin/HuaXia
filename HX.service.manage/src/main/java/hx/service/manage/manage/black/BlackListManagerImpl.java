package hx.service.manage.manage.black;

import hx.base.core.dao.dict.acl.ErrorType;
import hx.base.core.dao.dict.black.BlackListType;
import hx.base.core.dao.entity.black.BlackList;
import hx.base.core.dao.repo.jpa.black.BlackListRepo;
import hx.base.core.dao.repo.request.black.BlackListPageRequest;
import hx.base.core.dao.repo.request.common.Pagination;
import hx.base.core.manage.model.CommonResponse;
import hx.base.core.manage.poi.ExcelTemplateHelper;
import hx.base.core.manage.poi.WorkbookWithDataHandler;
import hx.base.core.manage.tools.FileTools;
import hx.base.core.manage.tools.MyTimeTools;
import hx.service.manage.manage.common.AbstractExcelManager;
import hx.service.manage.model.black.BlackListQueryRequest;
import hx.service.manage.model.black.BlackListQueryResponse;
import hx.service.manage.model.common.CommonExportResponse;
import org.apache.commons.codec.binary.Base64;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.List;

/**
 * @ClassName: BlackListManagerImpl
 * @Description: 黑名单Manager
 * @Author HuoJiaJin
 * @Date 2021/2/3 0:49
 * @Version 1.0
 **/
@Service
public class BlackListManagerImpl extends AbstractExcelManager implements BlackListManager, WorkbookWithDataHandler<BlackListQueryRequest> {

    @Autowired
    private BlackListRepo blackListRepo;

    @Override
    public String query(BlackListQueryRequest request){
        CommonResponse response = new CommonResponse();
        BlackListPageRequest pageRequest = new BlackListPageRequest();
        if (request.getType() != 0) {
            try {
                pageRequest.setType(BlackListType.fromCode(request.getType()));
            } catch (InterruptedException e) {
                return response.setError(ErrorType.CONVERT, e.getMessage());
            }
        }
        Pagination page = blackListRepo.page(pageRequest);
        page.convertResult(this::convertModel);
        response.setData(page);
        return response.toJson();
    }

    private BlackListQueryResponse convertModel(BlackList entity){
        BlackListQueryResponse model = new BlackListQueryResponse();
        BeanUtils.copyProperties(entity, model);
        model.setEmployeeNum(entity.getAgentCode());
        model.setType(entity.getType().getCode());
        model.setCreateTime(MyTimeTools.timeToStr(entity.getInsertTime()));
        return model;
    }

    @Override
    public String export(BlackListQueryRequest request){

        CommonResponse response = new CommonResponse();

        CommonExportResponse resultResponse = new CommonExportResponse();
        InputStream is = FileTools.getResourcesFileInputStream("template/black/blackListExportTemplate.xlsx");
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
    public void fillWorkbook(Workbook workbook, BlackListQueryRequest request) throws Exception {

        //查询数据
        BlackListPageRequest pageRequest = new BlackListPageRequest();
        BlackListType type;
        try {
            type = BlackListType.fromCode(request.getType());
        } catch (InterruptedException e) {
            throw e;
        }
        pageRequest.setType(type);
        List<BlackList> blackLists = blackListRepo.list(pageRequest);

        //初始化表格
        Sheet sheet = workbook.getSheetAt(0);
        Cell cell = ExcelTemplateHelper.getCell(sheet, 0, 0);

        //查询条件
        if (type != null) {
            cell = ExcelTemplateHelper.getCell(sheet, 1, 0);
            setCellValueAndStyle(cell, workbook, type.getValue(), false);
        }

        for (int i = 0; i < blackLists.size(); i++) {
            BlackList model = blackLists.get(i);
            //序号
            cell = ExcelTemplateHelper.getCell(sheet, 2 + i, 0);
            setCellValueAndStyle(cell, workbook, String.valueOf(i + 1), false);
            //工号
            cell = ExcelTemplateHelper.getCell(sheet, 2 + i, 1);
            setCellValueAndStyle(cell, workbook, model.getAgentCode(), false);
            //人员名称
            cell = ExcelTemplateHelper.getCell(sheet, 2 + i, 2);
            setCellValueAndStyle(cell, workbook, model.getName(), false);
            //部名称
            cell = ExcelTemplateHelper.getCell(sheet, 2 + i, 3);
            setCellValueAndStyle(cell, workbook, model.getSectionName(), false);
            //组名称
            cell = ExcelTemplateHelper.getCell(sheet, 2 + i, 4);
            setCellValueAndStyle(cell, workbook, model.getGroupName(), false);
            //进入时间
            cell = ExcelTemplateHelper.getCell(sheet, 2 + i, 5);
            setCellValueAndStyle(cell, workbook, MyTimeTools.timeToStr(model.getInsertTime()), false);
            //黑名单类别
            cell = ExcelTemplateHelper.getCell(sheet, 2 + i, 6);
            setCellValueAndStyle(cell, workbook, model.getType().getValue(), false);
        }
    }
}
