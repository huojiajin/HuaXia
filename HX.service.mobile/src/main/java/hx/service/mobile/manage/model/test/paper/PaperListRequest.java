package hx.service.mobile.manage.model.test.paper;

import hx.service.mobile.manage.model.common.MobileCommonRequest;

/**
 * @ClassName PaperListRequest
 * @Description 查询试卷Request
 * @Author HuoJiaJin
 * @Date 2020/6/27 21:58
 * @Version 1.0
 **/
public class PaperListRequest extends MobileCommonRequest {

    private int type;//试卷类型

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
