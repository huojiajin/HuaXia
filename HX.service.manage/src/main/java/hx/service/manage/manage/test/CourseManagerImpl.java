package hx.service.manage.manage.test;

import hx.base.core.dao.dict.CourseStatus;
import hx.base.core.dao.dict.CourseType;
import hx.base.core.dao.dict.ErrorType;
import hx.base.core.dao.dict.PositionsClass;
import hx.base.core.dao.entity.MarketingManpower;
import hx.base.core.dao.entity.test.course.Course;
import hx.base.core.dao.entity.test.course.CoursePush;
import hx.base.core.dao.repo.jpa.MarketingManpowerRepo;
import hx.base.core.dao.repo.jpa.test.course.CoursePushRepo;
import hx.base.core.dao.repo.jpa.test.course.CourseRepo;
import hx.base.core.dao.repo.request.common.Pagination;
import hx.base.core.dao.repo.request.test.CoursePageRequest;
import hx.base.core.manage.model.CommonResponse;
import hx.base.core.manage.poi.ExcelTemplateHelper;
import hx.base.core.manage.poi.WorkbookWithDataHandler;
import hx.base.core.manage.tools.FileTools;
import hx.base.core.manage.tools.JsonTools;
import hx.base.core.manage.tools.MyTimeTools;
import hx.service.manage.manage.AbstractManager;
import hx.service.manage.manage.model.test.course.*;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.compress.utils.Lists;
import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @name: CourseManagerImpl
 * @description: 学习资料Repo
 * @author: huojiajin
 * @time: 2020/6/22 17:10
 */
@Service
public class CourseManagerImpl extends AbstractManager implements CourseManager,
        WorkbookWithDataHandler<CourseIdRequest> {

    @Autowired
    private CourseRepo courseRepo;
    @Autowired
    private CoursePushRepo coursePushRepo;
    @Autowired
    private MarketingManpowerRepo manpowerRepo;

    @Override
    public String query(CourseQueryRequest request){
        CommonResponse response = new CommonResponse();
        CoursePageRequest pageRequest = new CoursePageRequest();
        pageRequest.setName(request.getName());
        Pagination page = courseRepo.page(pageRequest);
        page.convertResult(this::convert);
        response.setData(page);
        return response.toJson();
    }

    public CourseQueryModel convert(Course course){
        CourseQueryModel model = new CourseQueryModel();
        model.setId(course.getId());
        model.setName(course.getName());
        model.setCreateTime(MyTimeTools.timeToStr(course.getInsertTime()));
        model.setType(course.getType().getCode());
        model.setStatus(course.getStatus().getCode());
        model.setUseStatus(course.isHasDelete() ? 1 : 0);
        return model;
    }

    @Override
    public String add(CourseAddRequest request){
        CommonResponse response = new CommonResponse();
        Course course = new Course();
        course.setName(request.getName());
        try {
            CourseType courseType = CourseType.fromCode(request.getType());
            course.setType(courseType);
        } catch (InterruptedException e) {
            logger.error("", e);
            return response.setError(ErrorType.CONVERT);
        }
        courseRepo.persist(course);
        addSysLog("添加学习资料" + request.getName() + "成功", request.getToken(), course.getId());
        response.setMessage("添加学习资料成功！");
        return response.toJson();
    }

    @Override
    public String delete(CourseIdRequest request){
        CommonResponse response = new CommonResponse();
        Optional<Course> op = courseRepo.findById(request.getId());
        if (op.isEmpty()){
            return response.setError(ErrorType.NOCOURSE);
        }
        if (op.get().getStatus() != CourseStatus.WDR && op.get().getStatus() != CourseStatus.YDR){
            response.setError(ErrorType.NOCOURSE);
            response.setMessage("学习资料已推送，无法删除");
            return response.toJson();
        }
        courseRepo.updateDelete(request.getId());
        addSysLog("删除学习资料" + op.get().getName() + "成功", request.getToken(), request.getId());
        response.setMessage("删除学习资料成功！");
        return response.toJson();
    }

    @Override
    public String importCourse(CourseImportRequest request){
        CommonResponse response = new CommonResponse();
        Optional<Course> op = courseRepo.findById(request.getCourseId());
        if (op.isEmpty()){
            return response.setError(ErrorType.NOCOURSE);
        }
        Course course = op.get();
        course.setUpdateTime(LocalDateTime.now());
        course.setStatus(CourseStatus.YDR);
        byte[] bytes = Base64.decodeBase64(request.getCourseFile());
        ByteArrayInputStream inputStream = new ByteArrayInputStream(bytes);
        courseRepo.blobSave(course, "content", inputStream);
        addSysLog("导入学习资料" + op.get().getName() + "成功", request.getToken(), request.getCourseId());
        response.setMessage("导入学习资料成功！");
        return response.toJson();
    }

    @Override
    public String view(CourseIdRequest request){
        CommonResponse response = new CommonResponse();
        Optional<Course> op = courseRepo.findById(request.getId());
        if (op.isEmpty()){
            return response.setError(ErrorType.NOCOURSE);
        }
        CourseViewResponse viewResponse = new CourseViewResponse();
        try {
            InputStream fis = op.get().getContent().getBinaryStream();
            String fileStr = inputStreamToBase64Str(fis);
            viewResponse.setCourseFile(fileStr);
        } catch (Exception e) {
            logger.error("", e);
            return response.setError(ErrorType.CONVERT);
        }
        response.setData(viewResponse);
        return response.toJson();
    }

    @Override
    public String push(CoursePushRequest request){
        CommonResponse response = new CommonResponse();
        Optional<Course> op = courseRepo.findById(request.getCourseId());
        if (op.isEmpty()){
            return response.setError(ErrorType.NOCOURSE);
        }
        List<CoursePush> pushList = Lists.newArrayList();
        List<String> rankCodeList = request.getRankCodeList();
        for (String rankCode : rankCodeList) {
            try {
                PositionsClass.valueOf(rankCode);
            } catch (IllegalArgumentException e) {
                logger.error("无此类型{}", rankCode);
                return response.setError(ErrorType.CONVERT);
            }
            List<MarketingManpower> manpowers = manpowerRepo.listByAgentGrade(rankCode);
            for (MarketingManpower manpower : manpowers) {
                CoursePush entity = new CoursePush();
                entity.setAgentCode(manpower.getAgentCode());
                entity.setStaffName(manpower.getName());
                entity.setCourseId(request.getCourseId());
                pushList.add(entity);
            }
        }
        persistPush(request, pushList);
        try {
            addSysLog("推送学习资料" + request.getCourseId() + "至职级" + JsonTools.toJsonStr(rankCodeList),
                    request.getToken(), request.getCourseId());
        } catch (IOException e) {
            logger.error("", e);
        }
        response.setMessage("推送学习资料成功！");
        return response.toJson();
    }

    @Transactional
    private void persistPush(CoursePushRequest request, List<CoursePush> pushList) {
        coursePushRepo.persistAll(pushList);
        courseRepo.updateStatus(request.getCourseId(), CourseStatus.YTS);
    }

    @Override
    public String viewLearned(CourseIdRequest request){
        CommonResponse response = new CommonResponse();
        CourseViewLearnedResponse learnedResponse = new CourseViewLearnedResponse();
        InputStream is = FileTools.getResourcesFileInputStream("template/courseLearnedTemplate.xlsx");
        try {
            ByteArrayOutputStream op = ExcelTemplateHelper.generateExcel(is, true, request, this);
            byte[] data = op.toByteArray();
            String resultFileStr = new String(Base64.encodeBase64(data));
            learnedResponse.setLearnedFile(resultFileStr);
        } catch (Exception e) {
            logger.error("", e);
            return response.setError(ErrorType.CONVERT);
        }
        response.setData(learnedResponse);
        return response.toJson();
    }

    @Override
    public void fillWorkbook(Workbook workbook, CourseIdRequest data) throws Exception {
        String courseId = data.getId();
        //处理资料名称
        Optional<Course> op = courseRepo.findById(courseId);
        Sheet sheet = workbook.getSheetAt(0);
        Cell cell = ExcelTemplateHelper.getCell(sheet, 1, 0);
        cell.setCellValue("资料名称：" + op.get().getName());

        List<CoursePush> pushList = coursePushRepo.listByCourseId(courseId);
        pushList = pushList.stream().filter(c -> c.isHasLearn()).collect(Collectors.toList());
        int index = 0;
        for (CoursePush push : pushList) {
            //序号
            cell = ExcelTemplateHelper.getCell(sheet, 3 + index, 0);
            cell.setCellValue(index + 1);
            setCellStyle(cell, workbook, false);
            //工号
            cell = ExcelTemplateHelper.getCell(sheet, 3 + index, 1);
            cell.setCellValue(push.getAgentCode());
            setCellStyle(cell, workbook, false);
            //姓名
            cell = ExcelTemplateHelper.getCell(sheet, 3 + index, 2);
            cell.setCellValue(push.getStaffName());
            setCellStyle(cell, workbook, false);
            //学习时间
            cell = ExcelTemplateHelper.getCell(sheet, 3 + index, 3);
            cell.setCellValue(MyTimeTools.timeToStr(push.getLearnTime()));
            setCellStyle(cell, workbook, false);

            index++;
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
