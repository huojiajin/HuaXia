package hx.service.mobile.model.test.course;

import hx.base.core.dao.entity.common.BaseEntity;

/**
 * @name: CourseLearnedResponse
 * @description: 学习资料已学习提交Response
 * @author: huojiajin
 * @time: 2020/6/30 23:23
 */
public class CourseLearnedResponse extends BaseEntity {

    private Integer integral;//所得积分

    public Integer getIntegral() {
        return integral;
    }

    public void setIntegral(Integer integral) {
        this.integral = integral;
    }
}
