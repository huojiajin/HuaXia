package hx.service.mobile.model.radar.stadprem;

import hx.base.core.dao.entity.common.BaseEntity;

/**
 * @ClassName PersonStadpremDetailModel
 * @Description 个人标保详情Model
 * @Author HuoJiaJin
 * @Date 2020/6/27 9:55
 * @Version 1.0
 **/
public class PersonStadpremDetailModel extends BaseEntity {

    private String policyNo;//保单号
    private String customerName;//客户姓名
    private String insuranceName;//保险名称
    private String stadprem;//标保数额
    private String isContinue;//是否续保保单
    private String insuranceTime;//承保时间
    private String receiptTime;//回执时间
    private String visitTime;//回访时间

    public String getPolicyNo() {
        return policyNo;
    }

    public void setPolicyNo(String policyNo) {
        this.policyNo = policyNo;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getInsuranceName() {
        return insuranceName;
    }

    public void setInsuranceName(String insuranceName) {
        this.insuranceName = insuranceName;
    }

    public String getStadprem() {
        return stadprem;
    }

    public void setStadprem(String stadprem) {
        this.stadprem = stadprem;
    }

    public String getIsContinue() {
        return isContinue;
    }

    public void setIsContinue(String isContinue) {
        this.isContinue = isContinue;
    }

    public String getInsuranceTime() {
        return insuranceTime;
    }

    public void setInsuranceTime(String insuranceTime) {
        this.insuranceTime = insuranceTime;
    }

    public String getReceiptTime() {
        return receiptTime;
    }

    public void setReceiptTime(String receiptTime) {
        this.receiptTime = receiptTime;
    }

    public String getVisitTime() {
        return visitTime;
    }

    public void setVisitTime(String visitTime) {
        this.visitTime = visitTime;
    }
}
