package hx.service.mobile.model.back.org;

import hx.service.mobile.model.common.MobileCommonRequest;

/**
 * @name: RadarDirectorListRequest
 * @description: 雷达图-获取相关总监区列表Request
 * @author: huojiajin
 * @time: 2021/2/25 12:03
 */
public class BackDirectorListRequest extends MobileCommonRequest {

    private String campCode;//营业区代码

    public String getCampCode() {
        return campCode;
    }

    public void setCampCode(String campCode) {
        this.campCode = campCode;
    }
}
