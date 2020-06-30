package hx.service.mobile.manage.model.test.course;

import hx.service.mobile.manage.model.common.MobileCommonRequest;

/**
 * @name: CourseDetailRequest
 * @description: 学习资料详情Request
 * @author: huojiajin
 * @time: 2020/6/30 23:08
 */
public class CourseDetailRequest extends MobileCommonRequest {

    private String courseId;//学习资料ID

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }
}
