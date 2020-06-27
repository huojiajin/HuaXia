package hx.service.mobile.manage.model.radar;

import hx.service.mobile.manage.model.common.MobileCommonRequest;

/**
 * @ClassName GroupListReqeust
 * @Description 获取组列表Request
 * @Author HuoJiaJin
 * @Date 2020/6/27 1:25
 * @Version 1.0
 **/
public class GroupListReqeust extends MobileCommonRequest {

    private String sectionCode;//部代码

    public String getSectionCode() {
        return sectionCode;
    }

    public void setSectionCode(String sectionCode) {
        this.sectionCode = sectionCode;
    }
}
