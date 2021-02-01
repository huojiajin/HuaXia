package hx.service.manage.model.test.course;

import hx.service.manage.model.common.CommonPageRequest;

/**
 * @name: CourseQueryRequest
 * @description: 学习资料分页查询Request
 * @author: huojiajin
 * @time: 2020/6/22 17:12
 */
public class CourseQueryRequest extends CommonPageRequest {

    private String name;//资料名称

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
