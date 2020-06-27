package hx.service.manage.manage.model.test.course;

import hx.base.core.dao.entity.common.BaseEntity;

/**
 * @ClassName CourseViewResponse
 * @Description 学习资料查询Response
 * @Author HuoJiaJin
 * @Date 2020/6/22 23:11
 * @Version 1.0
 **/
public class CourseViewResponse extends BaseEntity {

    private String courseFile;//资料文件

    public String getCourseFile() {
        return courseFile;
    }

    public void setCourseFile(String courseFile) {
        this.courseFile = courseFile;
    }
}
