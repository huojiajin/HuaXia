package hx.service.manage.web.test;

import hx.service.manage.manage.model.test.course.*;
import hx.service.manage.manage.test.CourseManager;
import hx.service.manage.web.MyBaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName CourseController
 * @Description 资料管理Controller
 * @Author HuoJiaJin
 * @Date 2020/6/22 23:49
 * @Version 1.0
 **/
@RestController
@RequestMapping("/manage/test/course")
public class CourseController extends MyBaseController {

    @Autowired
    private CourseManager manager;

    @PostMapping("/query")
    public String query(@RequestBody CourseQueryRequest request){
        return manager.query(request);
    }

    @PostMapping("/add")
    public String add(@RequestBody CourseAddRequest request){
        return manager.add(request);
    }

    @PostMapping("/delete")
    public String delete(@RequestBody CourseIdRequest request){
        return manager.delete(request);
    }

    @PostMapping("/import")
    public String importCourse(@RequestBody CourseImportRequest request){
        return manager.importCourse(request);
    }

    @PostMapping("/view")
    public String view(@RequestBody CourseIdRequest request){
        return manager.view(request);
    }

    @PostMapping("/push")
    public String push(@RequestBody CoursePushRequest request){
        return manager.push(request);
    }

    @PostMapping("/learned")
    public String add(@RequestBody CourseIdRequest request){
        return manager.viewLearned(request);
    }
}
