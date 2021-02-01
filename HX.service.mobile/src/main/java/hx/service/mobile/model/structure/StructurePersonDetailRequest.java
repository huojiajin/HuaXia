package hx.service.mobile.model.structure;

import hx.service.mobile.model.common.MobileCommonRequest;

/**
 * @name: StructurePersonDetailRequest
 * @description: 获取人员详情Request
 * @author: huojiajin
 * @time: 2020/7/11 0:35
 */
public class StructurePersonDetailRequest extends MobileCommonRequest {

    private String agentCode;//员工代码

    public String getAgentCode() {
        return agentCode;
    }

    public void setAgentCode(String agentCode) {
        this.agentCode = agentCode;
    }
}
