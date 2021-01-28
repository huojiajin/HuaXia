package hx.base.core.dao.repo.request;

import hx.base.core.dao.entity.MarketingManpower;
import hx.base.core.dao.repo.request.common.HqlBuilder;
import hx.base.core.dao.repo.request.common.JpaPageableDataRequest;

/**
 * @name: MarketingManpowerPageRequest
 * @description: 人力清单分页查询
 * @author: huojiajin
 * @time: 2020/7/3 11:42
 */
public class MarketingManpowerPageRequest extends JpaPageableDataRequest<MarketingManpower> {

    private String deptCode1;//营服代码
    private String deptCode2;//总监区代码
    private String deptCode3;//部代码
    private String deptCode4;//组代码
    private String agentCode;//营销员编码


    @Override
    public HqlBuilder toSelectHql() {
        HqlBuilder hql = new HqlBuilder("from " + clazz.getName() + " where 1=1");
        hql.append(" and outworkDate is null");
        hql.append(" and agentCode = :agentCode", agentCode);
        hql.append(" and deptCode1 = :deptCode1", deptCode1);
        hql.append(" and deptCode2 = :deptCode2", deptCode2);
        hql.append(" and deptCode3 = :deptCode3", deptCode3);
        hql.append(" and deptCode4 = :deptCode4", deptCode4);
        return hql;
    }

    public String getDeptCode1() {
        return deptCode1;
    }

    public void setDeptCode1(String deptCode1) {
        this.deptCode1 = deptCode1;
    }

    public String getDeptCode2() {
        return deptCode2;
    }

    public void setDeptCode2(String deptCode2) {
        this.deptCode2 = deptCode2;
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

    public String getAgentCode() {
        return agentCode;
    }

    public void setAgentCode(String agentCode) {
        this.agentCode = agentCode;
    }
}
