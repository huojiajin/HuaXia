package hx.base.core.dao.entity;

import hx.base.core.dao.entity.common.BaseEntity;
import hx.base.core.dao.entity.composite.BusinessId;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * @name: Business
 * @description: 业务人力清单
 * @author: huojiajin
 * @time: 2020/6/17 16:47
 */
@Entity
@Table(name = "FACT_YX_PREPREM_LIST", indexes = {
        @Index(columnList = "AGENTCODE", name = "FACT_YX_PREPREM_LIST_INDEX"),
        @Index(columnList = "ISSUE_DATE", name = "FACT_YX_PREPREM_LIST_INDEX1"),
        @Index(columnList = "DEPTCODE3", name = "FACT_YX_PREPREM_LIST_INDEX2"),
        @Index(columnList = "DEPTCODE4", name = "FACT_YX_PREPREM_LIST_INDEX3")
})
@IdClass(BusinessId.class)
public class Business extends BaseEntity {

    private String provinceComCode;//分公司代码
    private String provinceComsCame;//分公司名称
    private String branchCode;//中支代码
    private String branchsCame;//中支名称
    private String saledeptCode;//管理机构代码
    private String saledeptsName;//管理机构名称
    private String deptCode1;//营业区代码
    private String deptName1;//营业区名称
    private String deptCode2;//总监区代码
    private String deptName2;//总监区名称
    private String deptCode3;//业务部编码
    private String deptName3;//业务部名称
    private String deptCode4;//业务组编码
    private String deptName4;//业务组名称
    private String subGroupNo;//业务分组编码
    private String subGroupName;//业务分组名称
    private String agentCode;//业务员编码
    private String agentName;//业务员名称
    private String agentState;//业务员状态
    private LocalDate employDate;//业务员入司时间
    private String agentGrade;//业务员职级代码
    private LocalDate startDate;//本职级开始日期
    private String applyBarCode;//投保单号码
    private String policyNo;//保单号码
    private String productCode;//险种代码
    private String productName;//险种名称
    private Long premPeriod;//交费年期
    private String premPeriodYype;//交费年期类型
    private String premPeriodTypeName;//交费年期类型名称
    private Long coverPeriod;//保险期间
    private String coverPeriodType;//保险期间类型
    private String coverPeriodTypeName;//保险期间类型名称
    private Long prePrem;//预收规模保费
    private Double preStadPrem;//预收标准保费
    private Double preInsbase;//预收保额
    private Long preNum;//预收件数
    private Double preFyc;//预收FYC
    private Long writtenPrem;//承保规模保费
    private Double writtenStadPrem;//承保标准保费
    private Double writtenInsbase;//承保保额
    private Long writtenNum;//承保件数
    private Double writteFyc;//承保FYC
    private String applyStatusDesc;//保单状态
    private LocalDate scanDate;//扫描时间
    private LocalDate receiveDate;//预收日期
    private String receiveTime;//预收时间
    private LocalDate issueDate;//承保日期
    private LocalDate clientSignDate;//客户签收日期
    private LocalDate confirmDate;//回执回销日期
    private LocalDate callDate;//回访成功日期
    private LocalDate ytDate;//犹退日期
    private LocalDate tbDate;//退保日期
    private LocalDate cancelDate;//撤件日期
    private String cancelReason;//撤件原因
    private String cancelReasonDesc;//撤件原因名称
    private String insuredNo;//被保人号
    private String insuredName;//被保人姓名
    private String insuredSex;//被保人性别
    private Long insuredAge;//被保人年龄
    private String insuredOccupation;//被保人职业
    private String applicantNo;//投保人号
    private String applicantName;//投保人姓名
    private String applicantSex;//投保人性别
    private Long applicantAge;//投保人年龄
    private String applicantOccupation;//投保人职业
    private String relationShip;//投保人与被保险人关系
    private String selfInsured;//自保件/互保件
    private String channelType;//销售渠道
    private String channelTypeName;//销售渠道名称
    private String chnlDetail;//明细渠道
    private LocalDate handinDate;//交单日期
    private LocalDate timeStamp;//时间戳
    private LocalDate loadDate;//加载日期
    private String isDesignatedEffectiveDate;//微信是否指定生效日
    private LocalDate designatedEffectiveDate;//微信指定生效日
    private String applyStatusCode;//
    private LocalDate applyYtDate;//犹退申请日期
    private String applyYtTime;//犹退申请时间
    private String rnFlag;//续保标识
    private String frequency;//缴费频率

    @Column(name = "PROVINCECOMCODE", length = 10)
    public String getProvinceComCode() {
        return provinceComCode;
    }

    public void setProvinceComCode(String provinceComCode) {
        this.provinceComCode = provinceComCode;
    }

    @Column(name = "PROVINCECOMSNAME", length = 200)
    public String getProvinceComsCame() {
        return provinceComsCame;
    }

    public void setProvinceComsCame(String provinceComsCame) {
        this.provinceComsCame = provinceComsCame;
    }

    @Column(name = "BRANCHCODE", length = 10)
    public String getBranchCode() {
        return branchCode;
    }

    public void setBranchCode(String branchCode) {
        this.branchCode = branchCode;
    }

    @Column(name = "BRANCHSNAME", length = 200)
    public String getBranchsCame() {
        return branchsCame;
    }

    public void setBranchsCame(String branchsCame) {
        this.branchsCame = branchsCame;
    }

    @Column(name = "SALEDEPTCODE", length = 10)
    public String getSaledeptCode() {
        return saledeptCode;
    }

    public void setSaledeptCode(String saledeptCode) {
        this.saledeptCode = saledeptCode;
    }

    @Column(name = "SALEDEPTSNAME", length = 200)
    public String getSaledeptsName() {
        return saledeptsName;
    }

    public void setSaledeptsName(String saledeptsName) {
        this.saledeptsName = saledeptsName;
    }

    @Column(name = "DEPTCODE1", length = 70)
    public String getDeptCode1() {
        return deptCode1;
    }

    public void setDeptCode1(String deptCode1) {
        this.deptCode1 = deptCode1;
    }

    @Column(name = "DEPTNAME1", length = 100)
    public String getDeptName1() {
        return deptName1;
    }

    public void setDeptName1(String deptName1) {
        this.deptName1 = deptName1;
    }

    @Column(name = "DEPTCODE2", length = 70)
    public String getDeptCode2() {
        return deptCode2;
    }

    public void setDeptCode2(String deptCode2) {
        this.deptCode2 = deptCode2;
    }

    @Column(name = "DEPTNAME2", length = 100)
    public String getDeptName2() {
        return deptName2;
    }

    public void setDeptName2(String deptName2) {
        this.deptName2 = deptName2;
    }

    @Column(name = "DEPTCODE3", length = 70)
    public String getDeptCode3() {
        return deptCode3;
    }

    public void setDeptCode3(String deptCode3) {
        this.deptCode3 = deptCode3;
    }

    @Column(name = "DEPTNAME3", length = 100)
    public String getDeptName3() {
        return deptName3;
    }

    public void setDeptName3(String deptName3) {
        this.deptName3 = deptName3;
    }

    @Column(name = "DEPTCODE4", length = 70)
    public String getDeptCode4() {
        return deptCode4;
    }

    public void setDeptCode4(String deptCode4) {
        this.deptCode4 = deptCode4;
    }

    @Column(name = "DEPTNAME4", length = 100)
    public String getDeptName4() {
        return deptName4;
    }

    public void setDeptName4(String deptName4) {
        this.deptName4 = deptName4;
    }

    @Column(name = "SUBGROUP_NO", length = 50)
    public String getSubGroupNo() {
        return subGroupNo;
    }

    public void setSubGroupNo(String subGroupNo) {
        this.subGroupNo = subGroupNo;
    }

    @Column(name = "SUBGROUP_NAME", length = 200)
    public String getSubGroupName() {
        return subGroupName;
    }

    public void setSubGroupName(String subGroupName) {
        this.subGroupName = subGroupName;
    }

    @Column(name = "AGENTCODE", length = 20)
    public String getAgentCode() {
        return agentCode;
    }

    public void setAgentCode(String agentCode) {
        this.agentCode = agentCode;
    }

    @Column(name = "AGENTNAME", length = 20)
    public String getAgentName() {
        return agentName;
    }

    public void setAgentName(String agentName) {
        this.agentName = agentName;
    }

    @Column(name = "AGENTSTATE", length = 4)
    public String getAgentState() {
        return agentState;
    }

    public void setAgentState(String agentState) {
        this.agentState = agentState;
    }

    @Column(name = "EMPLOYDATE")
    public LocalDate getEmployDate() {
        return employDate;
    }

    public void setEmployDate(LocalDate employDate) {
        this.employDate = employDate;
    }

    @Column(name = "AGENTGRADE", length = 6)
    public String getAgentGrade() {
        return agentGrade;
    }

    public void setAgentGrade(String agentGrade) {
        this.agentGrade = agentGrade;
    }

    @Column(name = "STARTDATE")
    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    @Column(name = "APPLY_BAR_CODE", length = 20)
    public String getApplyBarCode() {
        return applyBarCode;
    }

    public void setApplyBarCode(String applyBarCode) {
        this.applyBarCode = applyBarCode;
    }

    @Id
    @Column(name = "POLICY_NO", length = 20)
    public String getPolicyNo() {
        return policyNo;
    }

    public void setPolicyNo(String policyNo) {
        this.policyNo = policyNo;
    }

    @Id
    @Column(name = "PRODUCT_CODE", length = 10)
    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    @Column(name = "PRODUCT_NAME", length = 100)
    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    @Column(name = "PREM_PERIOD", length = 3)
    public Long getPremPeriod() {
        return premPeriod;
    }

    public void setPremPeriod(Long premPeriod) {
        this.premPeriod = premPeriod;
    }

    @Column(name = "PREM_PERIOD_TYPE", length = 20)
    public String getPremPeriodYype() {
        return premPeriodYype;
    }

    public void setPremPeriodYype(String premPeriodYype) {
        this.premPeriodYype = premPeriodYype;
    }

    @Column(name = "PREM_PERIOD_TYPE_NAME", length = 50)
    public String getPremPeriodTypeName() {
        return premPeriodTypeName;
    }

    public void setPremPeriodTypeName(String premPeriodTypeName) {
        this.premPeriodTypeName = premPeriodTypeName;
    }

    @Column(name = "COVER_PERIOD", length = 3)
    public Long getCoverPeriod() {
        return coverPeriod;
    }

    public void setCoverPeriod(Long coverPeriod) {
        this.coverPeriod = coverPeriod;
    }

    @Column(name = "COVER_PERIOD_TYPE", length = 20)
    public String getCoverPeriodType() {
        return coverPeriodType;
    }

    public void setCoverPeriodType(String coverPeriodType) {
        this.coverPeriodType = coverPeriodType;
    }

    @Column(name = "COVER_PERIOD_TYPE_NAME", length = 50)
    public String getCoverPeriodTypeName() {
        return coverPeriodTypeName;
    }

    public void setCoverPeriodTypeName(String coverPeriodTypeName) {
        this.coverPeriodTypeName = coverPeriodTypeName;
    }

    @Column(name = "PREPREM")
    public Long getPrePrem() {
        return prePrem;
    }

    public void setPrePrem(Long prePrem) {
        this.prePrem = prePrem;
    }

    @Column(name = "PRESTADPREM", length = 16)
    public Double getPreStadPrem() {
        return preStadPrem;
    }

    public void setPreStadPrem(Double preStadPrem) {
        this.preStadPrem = preStadPrem;
    }

    @Column(name = "PREINSBASE", length = 16)
    public Double getPreInsbase() {
        return preInsbase;
    }

    public void setPreInsbase(Double preInsbase) {
        this.preInsbase = preInsbase;
    }

    @Column(name = "PRENUM")
    public Long getPreNum() {
        return preNum;
    }

    public void setPreNum(Long preNum) {
        this.preNum = preNum;
    }

    @Column(name = "PRE_FYC", length = 15)
    public Double getPreFyc() {
        return preFyc;
    }

    public void setPreFyc(Double preFyc) {
        this.preFyc = preFyc;
    }

    @Column(name = "WRITTENPREM")
    public Long getWrittenPrem() {
        return writtenPrem;
    }

    public void setWrittenPrem(Long writtenPrem) {
        this.writtenPrem = writtenPrem;
    }

    @Column(name = "WRITTENSTADPREM", length = 16)
    public Double getWrittenStadPrem() {
        return writtenStadPrem;
    }

    public void setWrittenStadPrem(Double writtenStadPrem) {
        this.writtenStadPrem = writtenStadPrem;
    }

    @Column(name = "WRITTENINSBASE", length = 16)
    public Double getWrittenInsbase() {
        return writtenInsbase;
    }

    public void setWrittenInsbase(Double writtenInsbase) {
        this.writtenInsbase = writtenInsbase;
    }

    @Column(name = "WRITTENNUM")
    public Long getWrittenNum() {
        return writtenNum;
    }

    public void setWrittenNum(Long writtenNum) {
        this.writtenNum = writtenNum;
    }

    @Column(name = "WRITTE_FYC", length = 16)
    public Double getWritteFyc() {
        return writteFyc;
    }

    public void setWritteFyc(Double writteFyc) {
        this.writteFyc = writteFyc;
    }

    @Column(name = "APPLY_STATUS_DESC", length = 100)
    public String getApplyStatusDesc() {
        return applyStatusDesc;
    }

    public void setApplyStatusDesc(String applyStatusDesc) {
        this.applyStatusDesc = applyStatusDesc;
    }

    @Column(name = "SCAN_DATE")
    public LocalDate getScanDate() {
        return scanDate;
    }

    public void setScanDate(LocalDate scanDate) {
        this.scanDate = scanDate;
    }

    @Column(name = "RECEIVE_DATE")
    public LocalDate getReceiveDate() {
        return receiveDate;
    }

    public void setReceiveDate(LocalDate receiveDate) {
        this.receiveDate = receiveDate;
    }

    @Column(name = "RECEIVE_TIME", length = 8)
    public String getReceiveTime() {
        return receiveTime;
    }

    public void setReceiveTime(String receiveTime) {
        this.receiveTime = receiveTime;
    }

    @Column(name = "ISSUE_DATE")
    public LocalDate getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(LocalDate issueDate) {
        this.issueDate = issueDate;
    }

    @Column(name = "CLIENT_SIGN_DATE")
    public LocalDate getClientSignDate() {
        return clientSignDate;
    }

    public void setClientSignDate(LocalDate clientSignDate) {
        this.clientSignDate = clientSignDate;
    }

    @Column(name = "CONFIRM_DATE")
    public LocalDate getConfirmDate() {
        return confirmDate;
    }

    public void setConfirmDate(LocalDate confirmDate) {
        this.confirmDate = confirmDate;
    }

    @Column(name = "CALL_DATE")
    public LocalDate getCallDate() {
        return callDate;
    }

    public void setCallDate(LocalDate callDate) {
        this.callDate = callDate;
    }

    @Column(name = "YT_DATE")
    public LocalDate getYtDate() {
        return ytDate;
    }

    public void setYtDate(LocalDate ytDate) {
        this.ytDate = ytDate;
    }

    @Column(name = "TB_DATE")
    public LocalDate getTbDate() {
        return tbDate;
    }

    public void setTbDate(LocalDate tbDate) {
        this.tbDate = tbDate;
    }

    @Column(name = "CANCEL_DATE")
    public LocalDate getCancelDate() {
        return cancelDate;
    }

    public void setCancelDate(LocalDate cancelDate) {
        this.cancelDate = cancelDate;
    }

    @Column(name = "CANCEL_REASON", length = 2)
    public String getCancelReason() {
        return cancelReason;
    }

    public void setCancelReason(String cancelReason) {
        this.cancelReason = cancelReason;
    }

    @Column(name = "CANCEL_REASON_DESC", length = 100)
    public String getCancelReasonDesc() {
        return cancelReasonDesc;
    }

    public void setCancelReasonDesc(String cancelReasonDesc) {
        this.cancelReasonDesc = cancelReasonDesc;
    }

    @Column(name = "INSURED_NO", length = 20)
    public String getInsuredNo() {
        return insuredNo;
    }

    public void setInsuredNo(String insuredNo) {
        this.insuredNo = insuredNo;
    }

    @Column(name = "INSURED_NAME", length = 120)
    public String getInsuredName() {
        return insuredName;
    }

    public void setInsuredName(String insuredName) {
        this.insuredName = insuredName;
    }

    @Column(name = "INSURED_SEX", length = 4)
    public String getInsuredSex() {
        return insuredSex;
    }

    public void setInsuredSex(String insuredSex) {
        this.insuredSex = insuredSex;
    }

    @Column(name = "INSURED_AGE")
    public Long getInsuredAge() {
        return insuredAge;
    }

    public void setInsuredAge(Long insuredAge) {
        this.insuredAge = insuredAge;
    }

    @Column(name = "INSURED_OCCUPATION", length = 300)
    public String getInsuredOccupation() {
        return insuredOccupation;
    }

    public void setInsuredOccupation(String insuredOccupation) {
        this.insuredOccupation = insuredOccupation;
    }

    @Column(name = "APPLICANT_NO", length = 20)
    public String getApplicantNo() {
        return applicantNo;
    }

    public void setApplicantNo(String applicantNo) {
        this.applicantNo = applicantNo;
    }

    @Column(name = "APPLICANT_NAME", length = 120)
    public String getApplicantName() {
        return applicantName;
    }

    public void setApplicantName(String applicantName) {
        this.applicantName = applicantName;
    }

    @Column(name = "APPLICANT_SEX", length = 4)
    public String getApplicantSex() {
        return applicantSex;
    }

    public void setApplicantSex(String applicantSex) {
        this.applicantSex = applicantSex;
    }

    @Column(name = "APPLICANT_AGE")
    public Long getApplicantAge() {
        return applicantAge;
    }

    public void setApplicantAge(Long applicantAge) {
        this.applicantAge = applicantAge;
    }

    @Column(name = "APPLICANT_OCCUPATION", length = 300)
    public String getApplicantOccupation() {
        return applicantOccupation;
    }

    public void setApplicantOccupation(String applicantOccupation) {
        this.applicantOccupation = applicantOccupation;
    }

    @Column(name = "RELATIONSHIP", length = 50)
    public String getRelationShip() {
        return relationShip;
    }

    public void setRelationShip(String relationShip) {
        this.relationShip = relationShip;
    }

    @Column(name = "SELF_INSURED", length = 13)
    public String getSelfInsured() {
        return selfInsured;
    }

    public void setSelfInsured(String selfInsured) {
        this.selfInsured = selfInsured;
    }

    @Column(name = "CHANNEL_TYPE", length = 2)
    public String getChannelType() {
        return channelType;
    }

    public void setChannelType(String channelType) {
        this.channelType = channelType;
    }

    @Column(name = "CHANNEL_TYPE_NAME", length = 20)
    public String getChannelTypeName() {
        return channelTypeName;
    }

    public void setChannelTypeName(String channelTypeName) {
        this.channelTypeName = channelTypeName;
    }

    @Column(name = "CHNL_DETAIL", length = 5)
    public String getChnlDetail() {
        return chnlDetail;
    }

    public void setChnlDetail(String chnlDetail) {
        this.chnlDetail = chnlDetail;
    }

    @Column(name = "HANDIN_DATE")
    public LocalDate getHandinDate() {
        return handinDate;
    }

    public void setHandinDate(LocalDate handinDate) {
        this.handinDate = handinDate;
    }

    @Column(name = "TIMESTAMP")
    public LocalDate getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(LocalDate timeStamp) {
        this.timeStamp = timeStamp;
    }

    @Column(name = "LOADDATE")
    public LocalDate getLoadDate() {
        return loadDate;
    }

    public void setLoadDate(LocalDate loadDate) {
        this.loadDate = loadDate;
    }

    @Column(name = "IS_DESIGNATED_EFFECTIVE_DATE", length = 5)
    public String getIsDesignatedEffectiveDate() {
        return isDesignatedEffectiveDate;
    }

    public void setIsDesignatedEffectiveDate(String isDesignatedEffectiveDate) {
        this.isDesignatedEffectiveDate = isDesignatedEffectiveDate;
    }

    @Column(name = "DESIGNATED_EFFECTIVE_DATE")
    public LocalDate getDesignatedEffectiveDate() {
        return designatedEffectiveDate;
    }

    public void setDesignatedEffectiveDate(LocalDate designatedEffectiveDate) {
        this.designatedEffectiveDate = designatedEffectiveDate;
    }

    @Column(name = "APPLY_STATUS_CODE", length = 20)
    public String getApplyStatusCode() {
        return applyStatusCode;
    }

    public void setApplyStatusCode(String applyStatusCode) {
        this.applyStatusCode = applyStatusCode;
    }

    @Column(name = "APPLY_YT_DATE")
    public LocalDate getApplyYtDate() {
        return applyYtDate;
    }

    public void setApplyYtDate(LocalDate applyYtDate) {
        this.applyYtDate = applyYtDate;
    }

    @Column(name = "APPLY_YT_TIME", length = 8)
    public String getApplyYtTime() {
        return applyYtTime;
    }

    public void setApplyYtTime(String applyYtTime) {
        this.applyYtTime = applyYtTime;
    }

    @Column(name = "RNFLAG", length = 8)
    public String getRnFlag() {
        return rnFlag;
    }

    public void setRnFlag(String rnFlag) {
        this.rnFlag = rnFlag;
    }

    @Column(name = "FREQUENCY", length = 4)
    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }
}
