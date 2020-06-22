package hx.service.manage.manage.model.test.course;

import hx.service.manage.manage.model.CommonRequest;

/**
 * @name: CourseAddRequest
 * @description: 学习资料添加Request
 * @author: huojiajin
 * @time: 2020/6/22 17:37
 */
public class CourseAddRequest extends CommonRequest {

    private String name;//名称
    private int type;//类别

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
