package hx.service.mobile.model.radar.stadprem;

import hx.service.mobile.model.common.MobileCommonRequest;

/**
 * @ClassName GroupStadpremRequest
 * @Description 组标保详情
 * @Author HuoJiaJin
 * @Date 2020/6/27 6:17
 * @Version 1.0
 **/
public class GroupStadpremRequest extends MobileCommonRequest {

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
