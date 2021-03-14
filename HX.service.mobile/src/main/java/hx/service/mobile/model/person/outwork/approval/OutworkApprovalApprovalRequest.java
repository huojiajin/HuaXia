package hx.service.mobile.model.person.outwork.approval;

import hx.service.mobile.model.common.MobileCommonRequest;

/**
 * @ClassName: OutworkApprovalApprovalRequest
 * @Description: “待我审批的”-审批Request
 * @Author HuoJiaJin
 * @Date 2021/3/14 1:43
 * @Version 1.0
 **/
public class OutworkApprovalApprovalRequest extends MobileCommonRequest {

    private String applyId;//离职申请ID
    private String remark;//备注
    private boolean adopt;//是否通过
    private String signImg;//签字照片
    private boolean special;//是否特批修改

    public String getApplyId() {
        return applyId;
    }

    public void setApplyId(String applyId) {
        this.applyId = applyId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public boolean isAdopt() {
        return adopt;
    }

    public void setAdopt(boolean adopt) {
        this.adopt = adopt;
    }

    public String getSignImg() {
        return signImg;
    }

    public void setSignImg(String signImg) {
        this.signImg = signImg;
    }

    public boolean isSpecial() {
        return special;
    }

    public void setSpecial(boolean special) {
        this.special = special;
    }
}
