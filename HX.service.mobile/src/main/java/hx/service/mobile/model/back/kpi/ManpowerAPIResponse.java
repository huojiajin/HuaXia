package hx.service.mobile.model.back.kpi;

import hx.base.core.dao.entity.common.BaseEntity;

/**
 * @ClassName: ManpowerAPIResponse
 * @Description: 人力KPIResponse
 * @Author HuoJiaJin
 * @Date 2021/2/6 3:39
 * @Version 1.0
 **/
public class ManpowerAPIResponse extends BaseEntity {

    //总体情况
    private String manpower;//在册人力
    private String advanceMonth;//当月预收
    private String insuranceMonth;//当月承保
    private String realActionMonth;//当月实动
    private String moreWanMonth;//当月万元
    //新人情况
    private String increasedMonth;//当月新增
    private String increasedQuarter;//当季度新增
    private String increasedRealAction;//新增实动
    private String increasedWan;//新增万元
    private String increasedContribution;//新增保费贡献

    //主管情况
    private String chiefNum;//总监数
    private String sectionNum;//部数
    private String groupNum;//组数
    private String executiveNum;//主管在册人数
    private String executiveRealAction;//主管实动
    private String executiveRealActionRate;//主管实动率

    //绩优情况
    private String MDRTNum;//MDRT人数
    private String fourStarNum;//4星以上人数
    private String fiveStarNum;//4星-5星人数
    private String sixStarNum;//6星-9星人数
    private String tenStarNum;//10星以上人数
    private String starRate;//星级人数占比
    private String starExecutive;//星级主管人数
    private String starNotExecutive;//星级非主管人数
    private String starContribution;//星级保费贡献
    private String starContributionRate;//星级保费占比
    private String starExecutiveRate;//主管星级化

    //架构情况-部
    private String excellentSectionNum;//部卓越数
    private String outstandingSectionNum;//部优秀数
    private String highpotentialSectionNum;//部高潜数
    private String laggingbehindSectionNum;//部后进数

    //架构情况-组
    private String excellentGroupNum;//组卓越数
    private String outstandingGroupNum;//组优秀数
    private String highpotentialGroupNum;//组高潜数
    private String laggingbehindGroupNum;//组后进数


    public String getManpower() {
        return manpower;
    }

    public void setManpower(String manpower) {
        this.manpower = manpower;
    }

    public String getAdvanceMonth() {
        return advanceMonth;
    }

    public void setAdvanceMonth(String advanceMonth) {
        this.advanceMonth = advanceMonth;
    }

    public String getInsuranceMonth() {
        return insuranceMonth;
    }

    public void setInsuranceMonth(String insuranceMonth) {
        this.insuranceMonth = insuranceMonth;
    }

    public String getRealActionMonth() {
        return realActionMonth;
    }

    public void setRealActionMonth(String realActionMonth) {
        this.realActionMonth = realActionMonth;
    }

    public String getMoreWanMonth() {
        return moreWanMonth;
    }

    public void setMoreWanMonth(String moreWanMonth) {
        this.moreWanMonth = moreWanMonth;
    }

    public String getIncreasedMonth() {
        return increasedMonth;
    }

    public void setIncreasedMonth(String increasedMonth) {
        this.increasedMonth = increasedMonth;
    }

    public String getIncreasedQuarter() {
        return increasedQuarter;
    }

    public void setIncreasedQuarter(String increasedQuarter) {
        this.increasedQuarter = increasedQuarter;
    }

    public String getIncreasedRealAction() {
        return increasedRealAction;
    }

    public void setIncreasedRealAction(String increasedRealAction) {
        this.increasedRealAction = increasedRealAction;
    }

    public String getIncreasedWan() {
        return increasedWan;
    }

    public void setIncreasedWan(String increasedWan) {
        this.increasedWan = increasedWan;
    }

    public String getIncreasedContribution() {
        return increasedContribution;
    }

    public void setIncreasedContribution(String increasedContribution) {
        this.increasedContribution = increasedContribution;
    }

    public String getChiefNum() {
        return chiefNum;
    }

    public void setChiefNum(String chiefNum) {
        this.chiefNum = chiefNum;
    }

    public String getSectionNum() {
        return sectionNum;
    }

    public void setSectionNum(String sectionNum) {
        this.sectionNum = sectionNum;
    }

    public String getGroupNum() {
        return groupNum;
    }

    public void setGroupNum(String groupNum) {
        this.groupNum = groupNum;
    }

    public String getExecutiveNum() {
        return executiveNum;
    }

    public void setExecutiveNum(String executiveNum) {
        this.executiveNum = executiveNum;
    }

    public String getExecutiveRealAction() {
        return executiveRealAction;
    }

    public void setExecutiveRealAction(String executiveRealAction) {
        this.executiveRealAction = executiveRealAction;
    }

    public String getExecutiveRealActionRate() {
        return executiveRealActionRate;
    }

    public void setExecutiveRealActionRate(String executiveRealActionRate) {
        this.executiveRealActionRate = executiveRealActionRate;
    }

    public String getMDRTNum() {
        return MDRTNum;
    }

    public void setMDRTNum(String MDRTNum) {
        this.MDRTNum = MDRTNum;
    }

    public String getFourStarNum() {
        return fourStarNum;
    }

    public void setFourStarNum(String fourStarNum) {
        this.fourStarNum = fourStarNum;
    }

    public String getFiveStarNum() {
        return fiveStarNum;
    }

    public void setFiveStarNum(String fiveStarNum) {
        this.fiveStarNum = fiveStarNum;
    }

    public String getSixStarNum() {
        return sixStarNum;
    }

    public void setSixStarNum(String sixStarNum) {
        this.sixStarNum = sixStarNum;
    }

    public String getTenStarNum() {
        return tenStarNum;
    }

    public void setTenStarNum(String tenStarNum) {
        this.tenStarNum = tenStarNum;
    }

    public String getStarRate() {
        return starRate;
    }

    public void setStarRate(String starRate) {
        this.starRate = starRate;
    }

    public String getStarExecutive() {
        return starExecutive;
    }

    public void setStarExecutive(String starExecutive) {
        this.starExecutive = starExecutive;
    }

    public String getStarNotExecutive() {
        return starNotExecutive;
    }

    public void setStarNotExecutive(String starNotExecutive) {
        this.starNotExecutive = starNotExecutive;
    }

    public String getStarContribution() {
        return starContribution;
    }

    public void setStarContribution(String starContribution) {
        this.starContribution = starContribution;
    }

    public String getStarContributionRate() {
        return starContributionRate;
    }

    public void setStarContributionRate(String starContributionRate) {
        this.starContributionRate = starContributionRate;
    }

    public String getStarExecutiveRate() {
        return starExecutiveRate;
    }

    public void setStarExecutiveRate(String starExecutiveRate) {
        this.starExecutiveRate = starExecutiveRate;
    }

    public String getExcellentSectionNum() {
        return excellentSectionNum;
    }

    public void setExcellentSectionNum(String excellentSectionNum) {
        this.excellentSectionNum = excellentSectionNum;
    }

    public String getOutstandingSectionNum() {
        return outstandingSectionNum;
    }

    public void setOutstandingSectionNum(String outstandingSectionNum) {
        this.outstandingSectionNum = outstandingSectionNum;
    }

    public String getHighpotentialSectionNum() {
        return highpotentialSectionNum;
    }

    public void setHighpotentialSectionNum(String highpotentialSectionNum) {
        this.highpotentialSectionNum = highpotentialSectionNum;
    }

    public String getLaggingbehindSectionNum() {
        return laggingbehindSectionNum;
    }

    public void setLaggingbehindSectionNum(String laggingbehindSectionNum) {
        this.laggingbehindSectionNum = laggingbehindSectionNum;
    }

    public String getExcellentGroupNum() {
        return excellentGroupNum;
    }

    public void setExcellentGroupNum(String excellentGroupNum) {
        this.excellentGroupNum = excellentGroupNum;
    }

    public String getOutstandingGroupNum() {
        return outstandingGroupNum;
    }

    public void setOutstandingGroupNum(String outstandingGroupNum) {
        this.outstandingGroupNum = outstandingGroupNum;
    }

    public String getHighpotentialGroupNum() {
        return highpotentialGroupNum;
    }

    public void setHighpotentialGroupNum(String highpotentialGroupNum) {
        this.highpotentialGroupNum = highpotentialGroupNum;
    }

    public String getLaggingbehindGroupNum() {
        return laggingbehindGroupNum;
    }

    public void setLaggingbehindGroupNum(String laggingbehindGroupNum) {
        this.laggingbehindGroupNum = laggingbehindGroupNum;
    }
}
