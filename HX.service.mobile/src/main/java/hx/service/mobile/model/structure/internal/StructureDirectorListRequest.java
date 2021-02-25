package hx.service.mobile.model.structure.internal;

import hx.service.mobile.model.common.MobileCommonRequest;

/**
 * @name: StructureDirectorListRequest
 * @description: 组织架构-获取相关总监区列表Request
 * @author: huojiajin
 * @time: 2021/2/25 12:03
 */
public class StructureDirectorListRequest extends MobileCommonRequest {

    private String campCode;//营业区代码

    public String getCampCode() {
        return campCode;
    }

    public void setCampCode(String campCode) {
        this.campCode = campCode;
    }
}
