package hx.service.mobile.model.salary;

import hx.service.mobile.model.common.MobileCommonPageRequest;

/**
 * @ClassName: SalaryQueryRequest
 * @Description: 追踪支持查询Request
 * @Author HuoJiaJin
 * @Date 2021/2/24 20:36
 * @Version 1.0
 **/
public class SalaryQueryRequest extends MobileCommonPageRequest {

    private String directorCode;//区代码
    private String sectionCode;//部代码
    private String groupCode;//组代码
    private String month;//月份
    private boolean person;

    public String getDirectorCode() {
        return directorCode;
    }

    public void setDirectorCode(String directorCode) {
        this.directorCode = directorCode;
    }

    public String getSectionCode() {
        return sectionCode;
    }

    public void setSectionCode(String sectionCode) {
        this.sectionCode = sectionCode;
    }

    public String getGroupCode() {
        return groupCode;
    }

    public void setGroupCode(String groupCode) {
        this.groupCode = groupCode;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public boolean isPerson() {
        return person;
    }

    public void setPerson(boolean person) {
        this.person = person;
    }
}
