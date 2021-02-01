package hx.service.mobile.model.radar;

import hx.service.mobile.model.common.MobileCommonRequest;

/**
 * @ClassName RadarRequest
 * @Description 主管经营雷达图
 * @Author HuoJiaJin
 * @Date 2020/6/27 2:07
 * @Version 1.0
 **/
public class RadarRequest extends MobileCommonRequest {

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
