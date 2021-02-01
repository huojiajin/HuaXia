package hx.service.mobile.model.radar.star;

import hx.service.mobile.model.common.MobileCommonRequest;

/**
 * @ClassName StarPowerDetailRequest
 * @Description 星级人力详情Request
 * @Author HuoJiaJin
 * @Date 2020/6/27 16:05
 * @Version 1.0
 **/
public class StarPowerDetailRequest extends MobileCommonRequest {

    private String sectionCode;//部代码
    private String groupCode;//组代码
    private int type;//查看四星以上人数时为0;查看预四星人数时为1

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

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
