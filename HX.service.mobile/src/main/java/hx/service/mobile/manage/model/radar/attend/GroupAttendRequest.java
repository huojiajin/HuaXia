package hx.service.mobile.manage.model.radar.attend;

import hx.service.mobile.manage.model.common.MobileCommonRequest;

/**
 * @ClassName GroupAttendRequest
 * @Description 组出勤人力Request
 * @Author HuoJiaJin
 * @Date 2020/6/27 2:07
 * @Version 1.0
 **/
public class GroupAttendRequest extends MobileCommonRequest {

    private int month;//月份
    private String sectionCode;//部代码

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public String getSectionCode() {
        return sectionCode;
    }

    public void setSectionCode(String sectionCode) {
        this.sectionCode = sectionCode;
    }
}
