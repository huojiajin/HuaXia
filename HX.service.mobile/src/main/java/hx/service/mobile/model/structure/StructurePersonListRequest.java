package hx.service.mobile.model.structure;

import hx.service.mobile.model.common.MobileCommonPageRequest;

/**
 * @name: StructureListRequest
 * @description: 获取组员列表Request
 * @author: huojiajin
 * @time: 2020/7/10 0:00
 */
public class StructurePersonListRequest extends MobileCommonPageRequest {

    private String groupCode;//组代码

    public String getGroupCode() {
        return groupCode;
    }

    public void setGroupCode(String groupCode) {
        this.groupCode = groupCode;
    }
}
