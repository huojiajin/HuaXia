package hx.base.core.dao.repo.request.message;

import hx.base.core.dao.entity.message.MobileMessage;
import hx.base.core.dao.repo.request.common.HqlBuilder;
import hx.base.core.dao.repo.request.common.JpaPageableDataRequest;

/**
 * @name: MobileMessagePageRequest
 * @description: 移动端消息日志分页查询
 * @author: huojiajin
 * @time: 2020/7/3 11:42
 */
public class MobileMessagePageRequest extends JpaPageableDataRequest<MobileMessage> {

    private String agentCode;//员工编码

    public MobileMessagePageRequest() {
        this.orderBy = "sendTime";
        this.desc = true;
    }

    @Override
    public HqlBuilder toSelectHql() {
        HqlBuilder hql = new HqlBuilder("from " + clazz.getName() + " where 1=1");
        hql.append(" and agentCode = :agentCode", agentCode);
        return hql;
    }

    public String getAgentCode() {
        return agentCode;
    }

    public void setAgentCode(String agentCode) {
        this.agentCode = agentCode;
    }
}
