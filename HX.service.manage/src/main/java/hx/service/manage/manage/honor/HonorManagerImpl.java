package hx.service.manage.manage.honor;

import hx.base.core.dao.dict.acl.ErrorType;
import hx.base.core.dao.dict.honor.HonorStatus;
import hx.base.core.dao.entity.hualife.MarketingManpower;
import hx.base.core.dao.entity.honor.Honor;
import hx.base.core.dao.entity.honor.HonorPeople;
import hx.base.core.dao.repo.jpa.hualife.MarketingManpowerRepo;
import hx.base.core.dao.repo.jpa.honor.HonorPeopleRepo;
import hx.base.core.dao.repo.jpa.honor.HonorRepo;
import hx.base.core.dao.repo.request.common.Pagination;
import hx.base.core.dao.repo.request.honor.HonorPageRequest;
import hx.base.core.manage.model.CommonResponse;
import hx.base.core.manage.poi.ExcelReadRowHandler;
import hx.base.core.manage.poi.ExcelTemplateHelper;
import hx.base.core.manage.poi.PoiExcelInfo;
import hx.base.core.manage.poi.WorkbookWithDataHandler;
import hx.base.core.manage.tools.FileTools;
import hx.base.core.manage.tools.MyTimeTools;
import hx.service.manage.manage.common.AbstractExcelManager;
import hx.service.manage.model.common.CommonExportResponse;
import hx.service.manage.model.common.CommonRequest;
import hx.service.manage.model.common.CommonTemplateResponse;
import hx.service.manage.model.honor.*;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.compress.utils.Lists;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * @ClassName: HonorManagerImpl
 * @Description: 荣誉管理ManagerImpl
 * @Author HuoJiaJin
 * @Date 2021/2/1 1:23
 * @Version 1.0
 **/
@Service
public class HonorManagerImpl extends AbstractExcelManager implements HonorManager,
        ExcelReadRowHandler<HonorStaffImportModel>, WorkbookWithDataHandler<HonorIdRequest> {

    @Autowired
    private MarketingManpowerRepo manpowerRepo;
    @Autowired
    private HonorRepo honorRepo;
    @Autowired
    private HonorPeopleRepo peopleRepo;

    @Override
    public String query(HonorQueryRequest request){
        CommonResponse response = new CommonResponse();
        HonorPageRequest pageRequest = new HonorPageRequest();
        BeanUtils.copyProperties(request, pageRequest);
        Pagination page = honorRepo.page(pageRequest);
        page.convertResult(this::convert);
        response.setData(page);
        return response.toJson();
    }

    private HonorQueryResponse convert(Honor entity){
        HonorQueryResponse model = new HonorQueryResponse();
        model.setId(entity.getId());
        model.setName(entity.getName());
        model.setStatus(entity.getStatus().getCode());
        byte[] iconBytes = entity.getIcon();
        String base64 = new String(Base64.encodeBase64(iconBytes));
        model.setIcon(base64);
        model.setCreateTime(MyTimeTools.timeToStr(entity.getInsertTime(), "yyyy-MM-dd"));
        return model;
    }

    @Override
    public String add(HonorAddRequest request){
        CommonResponse response = new CommonResponse();
        Honor honor = new Honor();
        honor.setName(request.getName());
        honor.setIcon(Base64.decodeBase64(request.getIcon()));
        honorRepo.persist(honor);
//        honorRepo.blobSave(honor, "icon", new ByteArrayInputStream(Base64.decodeBase64(request.getIcon())));
        addSysLog("添加荣誉", request.getToken(), honor.getId());
        response.setMessage("添加荣誉成功！");
        return response.toJson();
    }

    @Override
    public String delete(HonorIdRequest request){
        CommonResponse response = new CommonResponse();
        honorRepo.updateDelete(request.getHonorId(), LocalDateTime.now());
        addSysLog("删除荣誉", request.getToken(), request.getHonorId());
        response.setMessage("删除荣誉成功！");
        return response.toJson();
    }

    @Override
    public String downloadTemplate(CommonRequest request){
        CommonResponse response = new CommonResponse();
        CommonTemplateResponse templateResponse = new CommonTemplateResponse();
        InputStream fis = FileTools.getResourcesFileInputStream("template/honor/honorStaffImportTemplate.xlsx");
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
    public String importExcel(HonorImportRequest request){
        CommonResponse response = new CommonResponse();
        String honorId = request.getHonorId();
        //查询荣誉
        Optional<Honor> op = honorRepo.findById(honorId);
        if (!op.isPresent() || op.get().isStop()){
            return response.setError(ErrorType.NOHONOR);
        }
        //处理并读取excel
        PoiExcelInfo excelInfo = getExcelInfo(request);
        List<HonorStaffImportModel> importModels;
        try {
            importModels = ExcelTemplateHelper.readData(excelInfo, this);
        } catch (Exception e) {
            logger.error("", e);
            return response.setError(ErrorType.CONVERT);
        }

        List<HonorPeople> peopleList = Lists.newArrayList();
        int row = 1;
        for (HonorStaffImportModel model : importModels) {
            MarketingManpower byAgentCode = manpowerRepo.findByAgentCode(model.getAgentCode().trim());
            if (null == byAgentCode){
                logger.error("读取荣誉人员名单失败，行数{}，错误信息：{}", row, "未找到该人员");
                return response.setError(ErrorType.CONVERT,
                        toLogString("第{}行读取失败：{}", row, "未找到该人员"));
            }
            HonorPeople people = new HonorPeople();
            people.setHonorId(honorId);
            people.setAgentCode(model.getAgentCode());
            people.setName(byAgentCode.getName());
            people.setCampName(byAgentCode.getDeptName1());
            people.setCampCode(byAgentCode.getDeptCode1());
            people.setSectionName(byAgentCode.getDeptName3());
            people.setSectionCode(byAgentCode.getDeptCode3());
            people.setGroupName(byAgentCode.getDeptName4());
            people.setGroupCode(byAgentCode.getDeptCode4());
            peopleList.add(people);
            row++;
        }
        peopleRepo.persistAll(peopleList);
        honorRepo.updateImport(LocalDateTime.now(), honorId);
        response.setMessage("导入人员成功！");
        addSysLog("导入荣誉人员名单", request.getToken(), request.getHonorId());
        return response.toJson();
    }

    private PoiExcelInfo getExcelInfo(HonorImportRequest request) {
        PoiExcelInfo info = new PoiExcelInfo();
        byte[] bytes = Base64.decodeBase64(request.getHonorFile());
        info.setExcelInput(new ByteArrayInputStream(bytes));
        info.setFromRow(1);
        info.setToRow(-1);
        info.setColSize(3);
        return info.setXlsx(true);
    }



    @Override
    public HonorStaffImportModel row2Model(int row, Map<Integer, Cell> rowData) throws Exception {
        HonorStaffImportModel model = new HonorStaffImportModel();
        try {
            model.setAgentCode(String.valueOf(getCellValue(rowData, 1)));
            model.setName(String.valueOf(getCellValue(rowData, 2)));
        } catch (Exception e) {
            logger.error("", e);
            throw new Exception("第" + row + "行数据读取错误");
        }
        return model;
    }

    @Override
    public String exportPeople(HonorIdRequest request){
        CommonResponse response = new CommonResponse();
        //校验荣誉状态
        Honor honor = honorRepo.findById(request.getHonorId()).orElse(null);
        if (null == honor || honor.isStop() || honor.getStatus() == HonorStatus.WDR){
            return response.setError(ErrorType.CONVERT, "该荣誉无法导出人员信息");
        }
        CommonExportResponse resultResponse = new CommonExportResponse();
        InputStream is = FileTools.getResourcesFileInputStream("template/honor/honorStaffExportTemplate.xlsx");
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
    public void fillWorkbook(Workbook workbook, HonorIdRequest request) throws Exception {
        String honorId = request.getHonorId();
        Honor honor = honorRepo.findById(honorId).orElse(null);
        Sheet sheet = workbook.getSheetAt(0);
        Cell cell = ExcelTemplateHelper.getCell(sheet, 1, 0);
        cell.setCellValue("荣誉名称：" + honor.getName());

        //查询人员
        List<HonorPeople> honorPeopleList = peopleRepo.listByHonorId(honorId);
        int i = 0;
        for (HonorPeople honorPeople : honorPeopleList) {
            //序号
            cell = ExcelTemplateHelper.getCell(sheet, 3 + i, 0);
            cell.setCellValue(i + 1);
            setCellStyle(cell, workbook, false, false);
            //营销员编码
            cell = ExcelTemplateHelper.getCell(sheet, 3 + i, 1);
            cell.setCellValue(honorPeople.getAgentCode());
            setCellStyle(cell, workbook, false, false);
            //姓名
            cell = ExcelTemplateHelper.getCell(sheet, 3 + i, 2);
            cell.setCellValue(honorPeople.getName());
            setCellStyle(cell, workbook, false, false);
            //营服名称
            cell = ExcelTemplateHelper.getCell(sheet, 3 + i, 3);
            cell.setCellValue(honorPeople.getCampName());
            setCellStyle(cell, workbook, false, false);
            //部名称
            cell = ExcelTemplateHelper.getCell(sheet, 3 + i, 4);
            cell.setCellValue(honorPeople.getSectionName());
            setCellStyle(cell, workbook, false, false);
            //组名称
            cell = ExcelTemplateHelper.getCell(sheet, 3 + i, 5);
            cell.setCellValue(honorPeople.getGroupName());
            setCellStyle(cell, workbook, false, false);
            i++;
        }
    }
}
