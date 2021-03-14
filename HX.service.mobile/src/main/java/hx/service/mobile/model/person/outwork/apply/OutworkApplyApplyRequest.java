package hx.service.mobile.model.person.outwork.apply;

import hx.service.mobile.model.common.MobileCommonRequest;

/**
 * @ClassName: OutworkApplyApplyRequest
 * @Description: “我发起的”-发起离职申请Request
 * @Author HuoJiaJin
 * @Date 2021/3/14 0:23
 * @Version 1.0
 **/
public class OutworkApplyApplyRequest extends MobileCommonRequest {

    private String reason;//离职原因
    private String idcardFrontImg;//身份证正面照片
    private String idcardBackImg;//身份证背面照片
    private String signImg;//签字照片
    private boolean special;//是否特批

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getIdcardFrontImg() {
        return idcardFrontImg;
    }

    public void setIdcardFrontImg(String idcardFrontImg) {
        this.idcardFrontImg = idcardFrontImg;
    }

    public String getIdcardBackImg() {
        return idcardBackImg;
    }

    public void setIdcardBackImg(String idcardBackImg) {
        this.idcardBackImg = idcardBackImg;
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
