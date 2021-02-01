package hx.service.mobile.model.test.paper;

import hx.service.mobile.model.common.MobileCommonRequest;

/**
 * @ClassName PaperListRequest
 * @Description 查询试卷Request
 * @Author HuoJiaJin
 * @Date 2020/6/27 21:58
 * @Version 1.0
 **/
public class PaperListRequest extends MobileCommonRequest {

    private Integer type;//试卷类型

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
