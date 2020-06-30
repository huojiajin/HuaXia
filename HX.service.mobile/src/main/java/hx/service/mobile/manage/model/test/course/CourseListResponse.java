package hx.service.mobile.manage.model.test.course;

import hx.base.core.dao.entity.common.BaseEntity;

import java.util.List;

/**
 * @name: CourseListResponse
 * @description: 学习资料列表Response
 * @author: huojiajin
 * @time: 2020/6/30 22:47
 */
public class CourseListResponse extends BaseEntity {

    private List<CourseListModel> result;

    public List<CourseListModel> getResult() {
        return result;
    }

    public void setResult(List<CourseListModel> result) {
        this.result = result;
    }
}
