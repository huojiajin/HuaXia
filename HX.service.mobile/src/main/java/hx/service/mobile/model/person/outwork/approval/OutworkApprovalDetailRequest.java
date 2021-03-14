package hx.service.mobile.model.person.outwork.approval;

import hx.service.mobile.model.common.MobileCommonRequest;

/**
 * @ClassName: OutworkApprovalDetailRequest
 * @Description: “待我审批的”-查看离职详情Request
 * @Author HuoJiaJin
 * @Date 2021/3/14 1:41
 * @Version 1.0
 **/
public class OutworkApprovalDetailRequest extends MobileCommonRequest {

    private String applyId;//离职申请ID

    public String getApplyId() {
        return applyId;
    }

    public void setApplyId(String applyId) {
        this.applyId = applyId;
    }
}
