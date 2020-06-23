package hx.service.manage.manage.model.test.course;

import hx.service.manage.manage.model.CommonRequest;

/**
 * @ClassName CourseIdRequest
 * @Description 学习资料ID通用Request
 * @Author HuoJiaJin
 * @Date 2020/6/22 22:43
 * @Version 1.0
 **/
public class CourseIdRequest extends CommonRequest {

    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
