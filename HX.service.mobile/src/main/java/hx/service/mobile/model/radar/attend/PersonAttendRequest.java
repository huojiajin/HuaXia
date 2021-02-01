package hx.service.mobile.model.radar.attend;

import hx.service.mobile.model.common.MobileCommonPageRequest;

/**
 * @ClassName PersonAttendRequest
 * @Description 个人出勤人力Request
 * @Author HuoJiaJin
 * @Date 2020/6/27 17:54
 * @Version 1.0
 **/
public class PersonAttendRequest extends MobileCommonPageRequest {

    private int month;//月份
    private String groupCode;//组代码

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public String getGroupCode() {
        return groupCode;
    }

    public void setGroupCode(String groupCode) {
        this.groupCode = groupCode;
    }
}
