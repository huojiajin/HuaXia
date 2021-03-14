package hx.service.mobile.model.person.team;

import hx.service.mobile.model.common.MobileCommonPageRequest;

/**
 * @ClassName: TeamInfoQueryRequesty
 * @Description: 团队信息查询Request
 * @Author HuoJiaJin
 * @Date 2021/2/24 23:31
 * @Version 1.0
 **/
public class TeamInfoQueryRequest extends MobileCommonPageRequest {

    private String startDate;//查询开始时间
    private String endDate;//查询结束时间
    private String sectionCode;//部代码
    private String groupCode;//组代码

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
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
}
