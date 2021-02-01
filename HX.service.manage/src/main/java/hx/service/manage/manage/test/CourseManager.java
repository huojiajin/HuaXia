package hx.service.manage.manage.test;

import hx.service.manage.model.test.course.*;

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

    String delete(CourseIdRequest request);

    String importCourse(CourseImportRequest request);

    String view(CourseIdRequest request);

    String push(CoursePushRequest request);

    String viewLearned(CourseIdRequest request);
}
