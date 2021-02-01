package hx.service.mobile.manage.test;

import hx.base.core.dao.dict.test.CourseType;
import hx.base.core.dao.dict.acl.ErrorType;
import hx.base.core.dao.entity.test.course.Course;
import hx.base.core.dao.entity.test.course.CoursePush;
import hx.base.core.dao.entity.test.integral.IntegralCourse;
import hx.base.core.dao.repo.jpa.test.course.CoursePushRepo;
import hx.base.core.dao.repo.jpa.test.course.CourseRepo;
import hx.base.core.dao.repo.jpa.test.integral.IntegralCourseRepo;
import hx.base.core.dao.repo.jpa.test.integral.IntegralRepo;
import hx.base.core.manage.model.CommonResponse;
import hx.base.core.manage.tools.MyTimeTools;
import hx.service.mobile.manage.common.AbstractMobileManager;
import hx.service.mobile.model.login.MobileUserModel;
import hx.service.mobile.model.test.course.*;
import org.apache.commons.compress.utils.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.InputStream;
import java.sql.Blob;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @name: CourseManagerImpl
 * @description: 学习资料相关ManagerImpl
 * @author: huojiajin
 * @time: 2020/6/30 22:25
 */
@Service
public class CourseManagerImpl extends AbstractMobileManager implements CourseManager {

    @Autowired
    private CourseRepo courseRepo;
    @Autowired
    private CoursePushRepo pushRepo;
    @Autowired
    private IntegralRepo integralRepo;
    @Autowired
    private IntegralCourseRepo integralCourseRepo;

    @Override
    public String getList(CourseListRequest request){
        CommonResponse response = new CommonResponse();
        CourseListResponse data = new CourseListResponse();
        MobileUserModel user = getUser(request.getToken());
        if (user == null) return response.setError(ErrorType.NOLOGIN);
        CourseType type;
        try {
            type = CourseType.fromCode(request.getType());
        } catch (InterruptedException e) {
            logger.error("", e);
            return response.setError(ErrorType.CONVERT);
        }
        //查询相关学习资料
        List<CoursePush> pushList = pushRepo.listByAgentCode(user.getEmployee_code());
        Map<String, CoursePush> pushMaps = pushList.stream()
                .collect(Collectors.toMap(CoursePush::getCourseId, Function.identity()));
        List<String> courseIds = pushList.stream().map(CoursePush::getCourseId).collect(Collectors.toList());
        List<Course> courseList = courseRepo.listByIds(courseIds, type);
        courseList = courseList.stream().filter(c -> !c.isHasDelete()).collect(Collectors.toList());
        List<CourseListModel> result = Lists.newArrayList();
        for (Course course : courseList) {
            CourseListModel model = new CourseListModel();
            model.setId(course.getId());
            model.setName(course.getName());
            model.setCreateTime(MyTimeTools.timeToStr(course.getInsertTime()));
            model.setHasStop(course.isHasDelete());
            CoursePush push = pushMaps.get(course.getId());
            model.setHasLearn(push.isHasLearn());
            result.add(model);
        }
        data.setResult(result);
        response.setData(data);
        return response.toJson();
    }

    @Override
    public String getDetail(CourseDetailRequest request){
        CommonResponse response = new CommonResponse();
        CourseDetailResponse data = new CourseDetailResponse();
        Optional<Course> op = courseRepo.findById(request.getCourseId());
        if (!op.isPresent()){
            return response.setError(ErrorType.NOCOURSE);
        }
        Course course = op.get();
        if (course.isHasDelete()){
            return response.setError(ErrorType.HASSTOP);
        }
        try {
            Blob content = course.getContent();
            InputStream fis = content.getBinaryStream();
            String fileStr = inputStreamToBase64Str(fis);
            data.setCourseFile(fileStr);
        } catch (Exception e) {
            logger.error("", e);
            return response.setError(ErrorType.CONVERT);
        }
        response.setData(data);
        return response.toJson();
    }

    @Override
    public String learned(CourseLearnedRequest request){
        CommonResponse response = new CommonResponse();
        CourseLearnedResponse data = new CourseLearnedResponse();
        MobileUserModel user = getUser(request.getToken());
        if (user == null) return response.setError(ErrorType.NOLOGIN);
        String agentCode = user.getEmployee_code();
        String courseId = request.getCourseId();
        CoursePush push = pushRepo.findOne(agentCode, courseId);
        if (push.isHasLearn()) return response.setError(ErrorType.HASLEARNED);
        int integral = 8;
        //保存数据
        updateLearned(agentCode, courseId, integral);
        data.setIntegral(integral);
        response.setData(data);
        return response.toJson();
    }

    @Transactional(rollbackFor=Exception.class)
    private void updateLearned(String agentCode, String courseId, int integral){
        LocalDateTime now = LocalDateTime.now();
        pushRepo.updateLearned(agentCode, courseId, now);
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM");
        integralRepo.updateCourse(df.format(now), agentCode, integral);
        IntegralCourse integralCourse = new IntegralCourse();
        integralCourse.setAgentCode(agentCode);
        integralCourse.setCourseId(courseId);
        integralCourse.setIntegral(integral);
        integralCourseRepo.persist(integralCourse);
    }
}
