package hx.base.core.dao.repo.request.hualife;

import hx.base.core.dao.entity.hualife.Business;
import hx.base.core.dao.repo.request.common.HqlBuilder;
import hx.base.core.dao.repo.request.common.JpaPageableDataRequest;

import java.time.LocalDate;

/**
 * @name: MarketingManpowerPageRequest
 * @description: 人力清单分页查询
 * @author: huojiajin
 * @time: 2020/7/3 11:42
 */
public class BusinessPageRequest extends JpaPageableDataRequest<Business> {

    private String agentCode;//部代码
    private LocalDate issueDateStart;//承保时间开始
    private LocalDate issueDateEnd;//承保结束时间

    public BusinessPageRequest() {
        this.orderBy = "issueDate";
        this.desc = true;
    }

    @Override
    public String toCountHql()
    {
        return "select count(*) " + toSelectHql().toString();
    }

    @Override
    public HqlBuilder toSelectHql() {
        HqlBuilder hql = new HqlBuilder("from " + clazz.getName() + " where 1=1");
        hql.append(" and agentCode = :agentCode", agentCode);
        hql.append(" and issueDate >= :issueDateStart", issueDateStart);
        hql.append(" and issueDate < :issueDateEnd", issueDateEnd);
        return hql;
    }

    public String getAgentCode() {
        return agentCode;
    }

    public void setAgentCode(String agentCode) {
        this.agentCode = agentCode;
    }

    public LocalDate getIssueDateStart() {
        return issueDateStart;
    }

    public void setIssueDateStart(LocalDate issueDateStart) {
        this.issueDateStart = issueDateStart;
    }

    public LocalDate getIssueDateEnd() {
        return issueDateEnd;
    }

    public void setIssueDateEnd(LocalDate issueDateEnd) {
        this.issueDateEnd = issueDateEnd;
    }
}
