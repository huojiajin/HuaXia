package hx.service.mobile.model.person.team;

import hx.service.mobile.model.common.MobileCommonRequest;

/**
 * @ClassName: TeamInfoGroupListReqeust
 * @Description: 团队信息获取相关组列表Request
 * @Author HuoJiaJin
 * @Date 2021/2/25 0:44
 * @Version 1.0
 **/
public class TeamInfoGroupListReqeust extends MobileCommonRequest {

    private String sectionCode;//部代码

    public String getSectionCode() {
        return sectionCode;
    }

    public void setSectionCode(String sectionCode) {
        this.sectionCode = sectionCode;
    }
}
