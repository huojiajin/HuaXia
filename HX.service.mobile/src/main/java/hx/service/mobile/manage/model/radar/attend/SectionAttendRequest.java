package hx.service.mobile.manage.model.radar.attend;

import hx.service.mobile.manage.model.common.MobileCommonRequest;

/**
 * @ClassName SectionAttendRequest
 * @Description 部出勤人力Request
 * @Author HuoJiaJin
 * @Date 2020/6/27 2:07
 * @Version 1.0
 **/
public class SectionAttendRequest extends MobileCommonRequest {

    private String sectionCode;//部代码
    private String groupCode;//组代码

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
