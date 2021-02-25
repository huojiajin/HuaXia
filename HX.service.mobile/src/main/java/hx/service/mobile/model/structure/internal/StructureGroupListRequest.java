package hx.service.mobile.model.structure.internal;

import hx.service.mobile.model.common.MobileCommonRequest;

/**
 * @name: StructureGroupListRequest
 * @description: 组织架构-获取相关组列表Request
 * @author: huojiajin
 * @time: 2021/2/25 12:08
 */
public class StructureGroupListRequest extends MobileCommonRequest {

    private String sectionCode;//部代码

    public String getSectionCode() {
        return sectionCode;
    }

    public void setSectionCode(String sectionCode) {
        this.sectionCode = sectionCode;
    }
}
