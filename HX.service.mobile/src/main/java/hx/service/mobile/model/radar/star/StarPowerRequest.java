package hx.service.mobile.model.radar.star;

import hx.service.mobile.model.common.MobileCommonRequest;

/**
 * @ClassName StarPowerRequest
 * @Description 星级人力Request
 * @Author HuoJiaJin
 * @Date 2020/6/27 15:22
 * @Version 1.0
 **/
public class StarPowerRequest extends MobileCommonRequest {

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
