package hx.base.core.dao.entity.quit;

import hx.base.core.dao.dict.quit.QuitApplyApprovalType;
import hx.base.core.dao.dict.quit.QuitApplyFlowType;
import hx.base.core.dao.dict.quit.QuitApplyStatus;
import hx.base.core.dao.entity.common.AbstractInsertTimeEntity;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @ClassName: QuitApply
 * @Description: 离职申请实体
 * @Author HuoJiaJin
 * @Date 2021/2/2 0:07
 * @Version 1.0
 **/
@Entity
@Table(name = "hx_quit_apply", indexes = {
        @Index(name = "hx_quit_apply_index", columnList = "agent_code")
})
public class QuitApply extends AbstractInsertTimeEntity {

    private String name;//申请人名称
    private String agentCode;//工号
    private LocalDate entryTime;//入职时间
    private String campName;//营服名称
    private String campCode;//营服代码
    private String sectionName;//部名称
    private String sectionCode;//部代码
    private String groupName;//组名称
    private String groupCode;//组代码
    private String reason;//原因
    private LocalDateTime applyTime;//申请时间
    private QuitApplyFlowType flowType;//审批流程类别
    private QuitApplyApprovalType approvalType;//当前审批环节
    private String currentExaminer;//当前审批人
    private QuitApplyStatus status = QuitApplyStatus.NEW;//状态
    private byte[] idCardFrontImg;//身份证正面图片
    private byte[] idCardBackImg;//身份证背面图片
    private byte[] signImg;//申请人签字图片

    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "agent_code")
    public String getAgentCode() {
        return agentCode;
    }

    public void setAgentCode(String agentCode) {
        this.agentCode = agentCode;
    }

    @Column(name = "entry_time")
    public LocalDate getEntryTime() {
        return entryTime;
    }

    public void setEntryTime(LocalDate entryTime) {
        this.entryTime = entryTime;
    }

    @Column(name = "camp_name")
    public String getCampName() {
        return campName;
    }

    public void setCampName(String campName) {
        this.campName = campName;
    }

    @Column(name = "camp_code")
    public String getCampCode() {
        return campCode;
    }

    public void setCampCode(String campCode) {
        this.campCode = campCode;
    }

    @Column(name = "section_name")
    public String getSectionName() {
        return sectionName;
    }

    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }

    @Column(name = "section_code")
    public String getSectionCode() {
        return sectionCode;
    }

    public void setSectionCode(String sectionCode) {
        this.sectionCode = sectionCode;
    }

    @Column(name = "group_name")
    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    @Column(name = "group_code")
    public String getGroupCode() {
        return groupCode;
    }

    public void setGroupCode(String groupCode) {
        this.groupCode = groupCode;
    }

    @Column(name = "reason")
    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    @Column(name = "apply_time")
    public LocalDateTime getApplyTime() {
        return applyTime;
    }

    public void setApplyTime(LocalDateTime applyTime) {
        this.applyTime = applyTime;
    }

    @Enumerated(EnumType.STRING)
    @Column(name = "flow_type")
    public QuitApplyFlowType getFlowType() {
        return flowType;
    }

    public void setFlowType(QuitApplyFlowType flowType) {
        this.flowType = flowType;
    }

    @Enumerated(EnumType.STRING)
    @Column(name = "approval_type")
    public QuitApplyApprovalType getApprovalType() {
        return approvalType;
    }

    public void setApprovalType(QuitApplyApprovalType approvalType) {
        this.approvalType = approvalType;
    }

    @Column(name = "current_examiner")
    public String getCurrentExaminer() {
        return currentExaminer;
    }

    public void setCurrentExaminer(String currentExaminer) {
        this.currentExaminer = currentExaminer;
    }

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    public QuitApplyStatus getStatus() {
        return status;
    }

    public void setStatus(QuitApplyStatus status) {
        this.status = status;
    }

    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(name = "id_card_front_img")
    public byte[] getIdCardFrontImg() {
        return idCardFrontImg;
    }

    public void setIdCardFrontImg(byte[] idCardFrontImg) {
        this.idCardFrontImg = idCardFrontImg;
    }

    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(name = "id_card_back_img")
    public byte[] getIdCardBackImg() {
        return idCardBackImg;
    }

    public void setIdCardBackImg(byte[] idCardBackImg) {
        this.idCardBackImg = idCardBackImg;
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
}
