package hx.service.manage.model.quit;

import hx.base.core.dao.entity.common.BaseEntity;

/**
 * @ClassName: QuitApplyQueryResponse
 * @Description: 离职申请查询Response
 * @Author HuoJiaJin
 * @Date 2021/2/2 0:05
 * @Version 1.0
 **/
public class QuitApplyQueryResponse extends BaseEntity {

    private String id;//申请ID
    private String name;//申请人名称
    private String employeeNum;//工号
    private String sectionName;//部名称
    private String groupName;//组名称
    private String reason;//原因
    private String applyTime;//申请时间
    private String currentExaminer;//当前审批人
    private int status;//状态

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

    public String getEmployeeNum() {
        return employeeNum;
    }

    public void setEmployeeNum(String employeeNum) {
        this.employeeNum = employeeNum;
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

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getApplyTime() {
        return applyTime;
    }

    public void setApplyTime(String applyTime) {
        this.applyTime = applyTime;
    }

    public String getCurrentExaminer() {
        return currentExaminer;
    }

    public void setCurrentExaminer(String currentExaminer) {
        this.currentExaminer = currentExaminer;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
