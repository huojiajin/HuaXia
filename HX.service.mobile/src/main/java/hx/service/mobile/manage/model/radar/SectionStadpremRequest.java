package hx.service.mobile.manage.model.radar;

import hx.service.mobile.manage.model.common.MobileCommonRequest;

/**
 * @ClassName SectionStadpremRequest
 * @Description 部标保详情
 * @Author HuoJiaJin
 * @Date 2020/6/27 5:39
 * @Version 1.0
 **/
public class SectionStadpremRequest extends MobileCommonRequest {

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
