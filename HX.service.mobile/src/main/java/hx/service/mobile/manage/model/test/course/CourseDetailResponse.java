package hx.service.mobile.manage.model.test.course;

import hx.base.core.dao.entity.common.BaseEntity;

/**
 * @name: CourseDetailResponse
 * @description: 学习资料详情Detail
 * @author: huojiajin
 * @time: 2020/6/30 23:09
 */
public class CourseDetailResponse extends BaseEntity {

    private String courseFile;

    public String getCourseFile() {
        return courseFile;
    }

    public void setCourseFile(String courseFile) {
        this.courseFile = courseFile;
    }
}
