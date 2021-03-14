package hx.base.core.dao.entity.quit;

import hx.base.core.dao.dict.quit.QuitApplyApprovalType;
import hx.base.core.dao.dict.quit.QuitApplyStatus;
import hx.base.core.dao.entity.common.AbstractInsertTimeEntity;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * @ClassName: QuitApplyFlow
 * @Description: 离职申请审批流程实体
 * @Author HuoJiaJin
 * @Date 2021/2/2 0:23
 * @Version 1.0
 **/
@Entity
@Table(name = "hx_quit_apply_flow", indexes = {
        @Index(name = "hx_quit_apply_flow_index", columnList = "apply_id"),
        @Index(name = "hx_quit_apply_flow_index1", columnList = "approval_code")
})
public class QuitApplyFlow extends AbstractInsertTimeEntity {

    private String applyId;//申请ID
    private String approvalName;//审批人员
    private String approvalCode;//审批人员工号
    private QuitApplyApprovalType approvalType;//审批阶段
    private QuitApplyStatus status;//审批状态
    private String remark;//意见或备注
    private LocalDateTime startTime;//审批开始时间
    private LocalDateTime endTime;//审批结束时间
    private byte[] signImg;//签字图片
    private QuitApplyApprovalType NextApprovalType;//下一个审批阶段

    @Column(name = "apply_id")
    public String getApplyId() {
        return applyId;
    }

    public void setApplyId(String applyId) {
        this.applyId = applyId;
    }

    @Column(name = "approval_name")
    public String getApprovalName() {
        return approvalName;
    }

    public void setApprovalName(String approvalName) {
        this.approvalName = approvalName;
    }

    @Column(name = "approval_code")
    public String getApprovalCode() {
        return approvalCode;
    }

    public void setApprovalCode(String approvalCode) {
        this.approvalCode = approvalCode;
    }

    @Enumerated(EnumType.STRING)
    @Column(name = "approval_type")
    public QuitApplyApprovalType getApprovalType() {
        return approvalType;
    }

    public void setApprovalType(QuitApplyApprovalType approvalType) {
        this.approvalType = approvalType;
    }

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    public QuitApplyStatus getStatus() {
        return status;
    }

    public void setStatus(QuitApplyStatus status) {
        this.status = status;
    }

    @Column(name = "remark")
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Column(name = "start_time")
    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    @Column(name = "end_time")
    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(name = "sign_img")
    public byte[] getSignImg() {
        return signImg;
    }

    public void setSignImg(byte[] signImg) {
        this.signImg = signImg;
    }

    @Enumerated(EnumType.STRING)
    @Column(name = "next_approval_type")
    public QuitApplyApprovalType getNextApprovalType() {
        return NextApprovalType;
    }

    public void setNextApprovalType(QuitApplyApprovalType nextApprovalType) {
        NextApprovalType = nextApprovalType;
    }
}
