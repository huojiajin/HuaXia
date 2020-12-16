package hx.base.core.dao.repo.request.test;

import hx.base.core.dao.repo.request.common.HqlBuilder;
import hx.base.core.dao.entity.test.course.Course;
import hx.base.core.dao.repo.request.common.JpaPageableDataRequest;

/**
 * @name: CoursePageRequest
 * @description: 学习资料分页Request
 * @author: huojiajin
 * @time: 2020/5/28 10:57
 */
public class CoursePageRequest extends JpaPageableDataRequest<Course> {

    private String name;//资料名称

    public CoursePageRequest() {
        this.orderBy = "insertTime";
        this.desc = true;
    }

    @Override
    public HqlBuilder toSelectHql() {
        HqlBuilder hql = new HqlBuilder("from " + clazz.getName() + " where 1=1");
        hql.append(" and hasDelete = 0");
        hql.append(" and name = :name", name);
        return hql;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
