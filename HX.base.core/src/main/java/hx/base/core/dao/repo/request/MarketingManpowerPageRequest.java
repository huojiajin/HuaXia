package hx.base.core.dao.repo.request;

import hx.base.core.dao.entity.MarketingManpower;
import hx.base.core.dao.entity.test.course.Course;
import hx.base.core.dao.repo.request.common.HqlBuilder;
import hx.base.core.dao.repo.request.common.JpaPageableDataRequest;

/**
 * @name: MarketingManpowerPageRequest
 * @description: 人力清单分页查询
 * @author: huojiajin
 * @time: 2020/7/3 11:42
 */
public class MarketingManpowerPageRequest extends JpaPageableDataRequest<MarketingManpower> {

    private String deptCode3;//部代码
    private String deptCode4;//组代码

    @Override
    public HqlBuilder toSelectHql() {
        HqlBuilder hql = new HqlBuilder("from " + clazz.getName() + " where 1=1");
        hql.append(" and outworkDate is null");
        hql.append(" and deptCode3 = :deptCode3", deptCode3);
        hql.append(" and deptCode4 = :deptCode4", deptCode4);
        return hql;
    }

    public String getDeptCode3() {
        return deptCode3;
    }

    public void setDeptCode3(String deptCode3) {
        this.deptCode3 = deptCode3;
    }

    public String getDeptCode4() {
        return deptCode4;
    }

    public void setDeptCode4(String deptCode4) {
        this.deptCode4 = deptCode4;
    }
}
