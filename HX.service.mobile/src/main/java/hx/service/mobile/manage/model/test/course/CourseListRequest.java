package hx.service.mobile.manage.model.test.course;

import hx.service.mobile.manage.model.common.MobileCommonRequest;

/**
 * @name: CourseListRequest
 * @description: 学习资料列表Request
 * @author: huojiajin
 * @time: 2020/6/30 22:36
 */
public class CourseListRequest extends MobileCommonRequest {

    private Integer type;//资料类型

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
