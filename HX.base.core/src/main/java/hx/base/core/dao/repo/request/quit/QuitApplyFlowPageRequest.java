package hx.base.core.dao.repo.request.quit;

import hx.base.core.dao.dict.quit.QuitApplyStatus;
import hx.base.core.dao.entity.quit.QuitApplyFlow;
import hx.base.core.dao.repo.request.common.HqlBuilder;
import hx.base.core.dao.repo.request.common.JpaPageableDataRequest;

/**
 * @ClassName: QuitApplyFlowPageRequest
 * @Description: 离职申请流程分页查询Request
 * @Author HuoJiaJin
 * @Date 2021/3/14 15:31
 * @Version 1.0
 **/
public class QuitApplyFlowPageRequest extends JpaPageableDataRequest<QuitApplyFlow> {

    private String approvalName;//审批人员
    private String approvalCode;//审批人员工号
    private QuitApplyStatus status;//审批状态

    @Override
    public HqlBuilder toSelectHql() {
        HqlBuilder hql = new HqlBuilder("from " + clazz.getName() + " where 1=1");
        hql.append(" and approvalName = :approvalName", approvalName);
        hql.append(" and approvalCode = :approvalCode", approvalCode);
        hql.append(" and status = :status", status);
        return hql;
    }

    public String getApprovalName() {
        return approvalName;
    }

    public void setApprovalName(String approvalName) {
        this.approvalName = approvalName;
    }

    public String getApprovalCode() {
        return approvalCode;
    }

    public void setApprovalCode(String approvalCode) {
        this.approvalCode = approvalCode;
    }

    public QuitApplyStatus getStatus() {
        return status;
    }

    public void setStatus(QuitApplyStatus status) {
        this.status = status;
    }
}
