package hx.base.core.dao.repo.request.quit;

import hx.base.core.dao.dict.quit.QuitApplyStatus;
import hx.base.core.dao.entity.quit.QuitApply;
import hx.base.core.dao.repo.request.common.HqlBuilder;
import hx.base.core.dao.repo.request.common.JpaPageableDataRequest;

/**
 * @ClassName: QuitApplyPageRequest
 * @Description: 离职申请分页查询Request
 * @Author HuoJiaJin
 * @Date 2021/2/1 23:37
 * @Version 1.0
**/
public class QuitApplyPageRequest extends JpaPageableDataRequest<QuitApply> {

    private String agentCode;//工号
    private QuitApplyStatus status;//状态

    @Override
    public HqlBuilder toSelectHql() {
        HqlBuilder hql = new HqlBuilder("from " + clazz.getName() + " where 1=1");
        hql.append(" and agentCode = :agentCode", agentCode);
        hql.append(" and status = :status", status);
        return hql;
    }

    public String getAgentCode() {
        return agentCode;
    }

    public void setAgentCode(String agentCode) {
        this.agentCode = agentCode;
    }

    public QuitApplyStatus getStatus() {
        return status;
    }

    public void setStatus(QuitApplyStatus status) {
        this.status = status;
    }
}
