package hx.service.mobile.web.test;

import hx.base.core.manage.common.CommonAbstract;
import hx.service.mobile.manage.model.test.course.CourseDetailRequest;
import hx.service.mobile.manage.model.test.course.CourseLearnedRequest;
import hx.service.mobile.manage.model.test.course.CourseListRequest;
import hx.service.mobile.manage.test.CourseManager;
import org.apache.coyote.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @name: CourseController
 * @description: 学习资料相关Controller
 * @author: huojiajin
 * @time: 2020/6/30 23:38
 */
@RestController
@RequestMapping("/test/course")
public class CourseController extends CommonAbstract {

    @Autowired
    private CourseManager manager;

    @PostMapping("/list")
    public String getList(@RequestBody CourseListRequest request){
        return manager.getList(request);
    }

    @PostMapping("/detail")
    public String getDetail(@RequestBody CourseDetailRequest request){
        return manager.getDetail(request);
    }

    @PostMapping("/submit")
    public String learned(@RequestBody CourseLearnedRequest request){
        return manager.learned(request);
    }
}
