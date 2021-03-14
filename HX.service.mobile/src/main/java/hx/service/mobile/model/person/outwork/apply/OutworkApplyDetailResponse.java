package hx.service.mobile.model.person.outwork.apply;

import hx.base.core.dao.entity.common.BaseEntity;

import java.util.List;

/**
 * @ClassName: OutworkApplyDetailResponse
 * @Description: “我发起的”-查看离职详情Response
 * @Author HuoJiaJin
 * @Date 2021/3/14 1:34
 * @Version 1.0
 **/
public class OutworkApplyDetailResponse extends BaseEntity {

    private String name;//名称
    private String sectionName;//部名称
    private String groupName;//组名称
    private String applyTime;//申请时间
    private String entryTime;//入职时间
    private String reason;//离职原因
    private Integer status;//审批状态
    private List<OutworkApplyDetailApprovalModel> approvalList;//审批流程集合

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSectionName() {
        return sectionName;
    }

    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getApplyTime() {
        return applyTime;
    }

    public void setApplyTime(String applyTime) {
        this.applyTime = applyTime;
    }

    public String getEntryTime() {
        return entryTime;
    }

    public void setEntryTime(String entryTime) {
        this.entryTime = entryTime;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public List<OutworkApplyDetailApprovalModel> getApprovalList() {
        return approvalList;
    }

    public void setApprovalList(List<OutworkApplyDetailApprovalModel> approvalList) {
        this.approvalList = approvalList;
    }
}
