package hx.service.mobile.model.person.outwork.approval;

import hx.base.core.dao.entity.common.BaseEntity;

/**
 * @ClassName: OutworkApprovalDetailResponse
 * @Description: “待我审批的”-查看离职详情Response
 * @Author HuoJiaJin
 * @Date 2021/3/14 1:41
 * @Version 1.0
 **/
public class OutworkApprovalDetailResponse extends BaseEntity {

    private String id;
    private String name;//名称
    private String sectionName;//部名称
    private String groupName;//组名称
    private String applyTime;//申请时间
    private String entryTime;//入职时间
    private String reason;//离职原因

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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
}
