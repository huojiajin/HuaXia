package hx.service.mobile.model.test.course;

import hx.service.mobile.model.common.MobileCommonRequest;

/**
 * @name: CourseLearnedRequest
 * @description: 学习资料已学习提交Request
 * @author: huojiajin
 * @time: 2020/6/30 23:22
 */
public class CourseLearnedRequest extends MobileCommonRequest {

    private String courseId;//学习资料id

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }
}
