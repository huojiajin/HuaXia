package hx.service.mobile.manage.test;

import hx.service.mobile.manage.model.test.course.CourseDetailRequest;
import hx.service.mobile.manage.model.test.course.CourseLearnedRequest;
import hx.service.mobile.manage.model.test.course.CourseListRequest;

/**
 * @name: CourseManager
 * @description: 学习资料相关Manager
 * @author: huojiajin
 * @time: 2020/6/30 22:24
 */
public interface CourseManager {
    String getList(CourseListRequest request);

    String getDetail(CourseDetailRequest request);

    String learned(CourseLearnedRequest request);
}
