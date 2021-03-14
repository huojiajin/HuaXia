package hx.service.mobile.model.person.outwork.apply;

import hx.base.core.dao.entity.common.BaseEntity;

/**
 * @ClassName: OutworkApplyQueryResponse
 * @Description: “我发起的”-列表查询Response
 * @Author HuoJiaJin
 * @Date 2021/3/14 1:29
 * @Version 1.0
 **/
public class OutworkApplyQueryResponse extends BaseEntity {

    private String name;//名称
    private String sectionName;//部名称
    private String groupName;//组名称
    private String applyTime;//申请时间
    private String stage;//当前审批环节
    private int status;//审批状态

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

    public String getStage() {
        return stage;
    }

    public void setStage(String stage) {
        this.stage = stage;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
