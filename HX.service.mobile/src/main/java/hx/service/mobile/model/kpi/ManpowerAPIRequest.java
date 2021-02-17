package hx.service.mobile.model.kpi;

import hx.service.mobile.model.common.MobileCommonRequest;

/**
 * @ClassName: ManpowerAPIRequest
 * @Description: 人力KPIRequest
 * @Author HuoJiaJin
 * @Date 2021/2/6 3:38
 * @Version 1.0
 **/
public class ManpowerAPIRequest extends MobileCommonRequest {

    private String campCode;//营业区代码

    public String getCampCode() {
        return campCode;
    }

    public void setCampCode(String campCode) {
        this.campCode = campCode;
    }
}
