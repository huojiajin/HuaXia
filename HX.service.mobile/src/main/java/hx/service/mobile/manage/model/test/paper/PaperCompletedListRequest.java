package hx.service.mobile.manage.model.test.paper;

import hx.service.mobile.manage.model.common.MobileCommonRequest;

/**
 * @name: PaperCompletedListRequest
 * @description: 已答试卷列表查询Request
 * @author: huojiajin
 * @time: 2020/6/30 13:26
 */
public class PaperCompletedListRequest extends MobileCommonRequest {

    private int type;//试卷类型

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
