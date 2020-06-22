package hx.service.manage.manage.test;

import hx.service.manage.manage.model.test.course.CourseAddRequest;
import hx.service.manage.manage.model.test.course.CourseQueryRequest;

/**
 *@ClassName CourseManager
 *@Description 学习资料Manager
 *@Author HuoJiaJin
 *@Date 2020/6/22 17:10
 *@Version 1.0
 **/
public interface CourseManager {
    String query(CourseQueryRequest request);

    String add(CourseAddRequest addRequest);
}
