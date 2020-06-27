package hx.service.mobile.manage.model.radar;

import hx.service.mobile.manage.model.common.MobileCommonRequest;

/**
 * @ClassName PersonStadpremRequest
 * @Description 个人标保
 * @Author HuoJiaJin
 * @Date 2020/6/27 9:33
 * @Version 1.0
 **/
public class PersonStadpremRequest extends MobileCommonRequest {

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
