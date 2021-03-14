package hx.service.mobile.model.person.outwork.apply;

import hx.base.core.dao.entity.common.BaseEntity;

/**
 * @ClassName: OutworkApplyDetailApprovalModel
 * @Description: “我发起的”-查看离职详情-审批流程Model
 * @Author HuoJiaJin
 * @Date 2021/3/14 1:35
 * @Version 1.0
 **/
public class OutworkApplyDetailApprovalModel extends BaseEntity {

    private String approvalStage;//审批阶段
    private String approvalName;//审批人名称
    private int approvalStatus;//审批状态
    private String approvalRemark;//备注
    private String approvalTime;//审批时间

    public String getApprovalStage() {
        return approvalStage;
    }

    public void setApprovalStage(String approvalStage) {
        this.approvalStage = approvalStage;
    }

    public String getApprovalName() {
        return approvalName;
    }

    public void setApprovalName(String approvalName) {
        this.approvalName = approvalName;
    }

    public int getApprovalStatus() {
        return approvalStatus;
    }

    public void setApprovalStatus(int approvalStatus) {
        this.approvalStatus = approvalStatus;
    }

    public String getApprovalRemark() {
        return approvalRemark;
    }

    public void setApprovalRemark(String approvalRemark) {
        this.approvalRemark = approvalRemark;
    }

    public String getApprovalTime() {
        return approvalTime;
    }

    public void setApprovalTime(String approvalTime) {
        this.approvalTime = approvalTime;
    }
}
