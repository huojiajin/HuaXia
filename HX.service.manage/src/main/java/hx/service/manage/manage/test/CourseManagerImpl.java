package hx.service.manage.manage.test;

import hx.service.manage.dao.dict.CourseType;
import hx.service.manage.dao.dict.ErrorType;
import hx.service.manage.dao.entity.test.course.Course;
import hx.service.manage.dao.repo.jpa.test.course.CoursePushRepo;
import hx.service.manage.dao.repo.jpa.test.course.CourseRepo;
import hx.service.manage.dao.repo.request.common.Pagination;
import hx.service.manage.dao.repo.request.test.CoursePageRequest;
import hx.service.manage.manage.common.AbstractManager;
import hx.service.manage.manage.model.CommonResponse;
import hx.service.manage.manage.model.test.course.CourseAddRequest;
import hx.service.manage.manage.model.test.course.CourseQueryModel;
import hx.service.manage.manage.model.test.course.CourseQueryRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;

/**
 * @name: CourseManagerImpl
 * @description: 学习资料Repo
 * @author: huojiajin
 * @time: 2020/6/22 17:10
 */
@Service
public class CourseManagerImpl extends AbstractManager implements CourseManager{

    @Autowired
    private CourseRepo courseRepo;
    @Autowired
    private CoursePushRepo coursePushRepo;

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
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        model.setCreateTime(df.format(course.getInsertTime()));
        model.setType(course.getType().getCode());
        model.setStatus(course.getStatus().getCode());
        model.setUseStatus(course.isHasStop() ? 1 : 0);
        return model;
    }

    @Override
    public String add(CourseAddRequest addRequest){
        CommonResponse response = new CommonResponse();
        Course course = new Course();
        course.setName(addRequest.getName());
        try {
            CourseType courseType = CourseType.fromCode(addRequest.getType());
        } catch (InterruptedException e) {
            logger.error("", e);
            return response.setError(ErrorType.CONVERT);
        }
        return null;
    }
}
