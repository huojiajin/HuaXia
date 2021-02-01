package hx.service.manage.model.staff.situation;

import hx.service.manage.model.common.CommonPageRequest;

/**
 * @ClassName: SituationQueryRequest
 * @Description: 人员情况统计查询请求Request
 * @Author huojiajin
 * @Date 2021/1/29 0:56
 * @Version 1.0
 **/
public class SituationQueryRequest extends CommonPageRequest {

    private String campCode;//营服代码
    private String campName;//营服名称
    private String chiefName;//总监名称
    private String sectionName;//部名称
    private String groupName;//组名称
    private String employeeNum;//工号

    public String getCampCode() {
        return campCode;
    }

    public void setCampCode(String campCode) {
        this.campCode = campCode;
    }

    public String getCampName() {
        return campName;
    }

    public void setCampName(String campName) {
        this.campName = campName;
    }

    public String getChiefName() {
        return chiefName;
    }

    public void setChiefName(String chiefName) {
        this.chiefName = chiefName;
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

    public String getEmployeeNum() {
        return employeeNum;
    }

    public void setEmployeeNum(String employeeNum) {
        this.employeeNum = employeeNum;
    }
}
