package hx.service.mobile.model.person.outwork.approval;

import hx.base.core.dao.entity.common.BaseEntity;

/**
 * @ClassName: OutworkApprovalQueryResponse
 * @Description: “待我审批的”-列表查询Response
 * @Author HuoJiaJin
 * @Date 2021/3/14 1:39
 * @Version 1.0
 **/
public class OutworkApprovalQueryResponse extends BaseEntity {

    private String id;//id
    private String name;//名称
    private String sectionName;//部名称
    private String groupName;//组名称
    private String applyTime;//申请时间
    private boolean special;//是否特批

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

    public boolean isSpecial() {
        return special;
    }

    public void setSpecial(boolean special) {
        this.special = special;
    }
}
