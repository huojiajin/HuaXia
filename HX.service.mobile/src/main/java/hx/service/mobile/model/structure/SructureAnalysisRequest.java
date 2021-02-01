package hx.service.mobile.model.structure;

import hx.service.mobile.model.common.MobileCommonRequest;

/**
 * @name: SructureAnalysisRequest
 * @description: 组织架构分析Request
 * @author: huojiajin
 * @time: 2020/7/7 1:32
 */
public class SructureAnalysisRequest extends MobileCommonRequest {

    private String orgCode;//组织代码
    private Integer orgType;//组织类型

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    public Integer getOrgType() {
        return orgType;
    }

    public void setOrgType(Integer orgType) {
        this.orgType = orgType;
    }
}
