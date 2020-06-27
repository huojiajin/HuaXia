package hx.base.core.dao.repo.request.test;

import hx.base.core.dao.repo.request.common.HqlBuilder;
import hx.base.core.dao.repo.request.common.JpaPageableDataRequest;
import hx.base.core.dao.entity.test.integral.Integral;

/**
 * @ClassName IntegralPageRequest
 * @Description 积分表分页查询Request
 * @Author HuoJiaJin
 * @Date 2020/6/23 19:40
 * @Version 1.0
 **/
public class IntegralPageRequest extends JpaPageableDataRequest<Integral> {

    private String month;//年月
    private String agentCode;//营销员编码

    @Override
    public HqlBuilder toSelectHql() {
        HqlBuilder hql = new HqlBuilder("from " + clazz.getName() + " where 1=1");
        hql.append(" and month = :month", month);
        hql.append(" and agentCode = :agentCode", agentCode);
        return hql;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getAgentCode() {
        return agentCode;
    }

    public void setAgentCode(String agentCode) {
        this.agentCode = agentCode;
    }
}
