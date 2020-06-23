package hx.service.manage.manage.model.test.course;

import hx.service.manage.manage.model.CommonRequest;

/**
 * @ClassName CourseImportRequest
 * @Description 学习资料导入Request
 * @Author HuoJiaJin
 * @Date 2020/6/22 22:55
 * @Version 1.0
 **/
public class CourseImportRequest extends CommonRequest {

    private String courseId;//
    private String courseFile;//资料文件

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getCourseFile() {
        return courseFile;
    }

    public void setCourseFile(String courseFile) {
        this.courseFile = courseFile;
    }
}
