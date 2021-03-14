package hx.service.mobile.model.person.outwork.apply;

import hx.service.mobile.model.common.MobileCommonRequest;

/**
 * @ClassName: OutworkApplyDetailRequest
 * @Description: “我发起的”-查看离职详情Request
 * @Author HuoJiaJin
 * @Date 2021/3/14 1:33
 * @Version 1.0
 **/
public class OutworkApplyDetailRequest extends MobileCommonRequest {

    private String applyId;//离职申请ID

    public String getApplyId() {
        return applyId;
    }

    public void setApplyId(String applyId) {
        this.applyId = applyId;
    }
}
