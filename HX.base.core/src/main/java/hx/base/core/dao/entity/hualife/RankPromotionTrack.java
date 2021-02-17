package hx.base.core.dao.entity.hualife;

import hx.base.core.dao.entity.common.BaseEntity;
import hx.base.core.dao.entity.hualife.composite.RankPromotionTrackId;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * @ClassName: RankPromotionTrack
 * @Description: 职级晋升轨迹实体
 * @Author HuoJiaJin
 * @Date 2021/2/6 1:25
 * @Version 1.0
 **/
@Entity
@Table(name = "LATREEB", indexes = {
        @Index(name = "LATREEB_INDEX", columnList = "AGENTCODE")
})
@IdClass(RankPromotionTrackId.class)
public class RankPromotionTrack extends BaseEntity {

    private String agentCode;//工号
    private String agentGroup;//团队内码
    private String managetCom;//管理机构
    private String agentSeries;//职级系列
    private String agentGrade;//职级
    private String agentLastSeries;//上次职级系列
    private String agentLastGrade;//上次职级
    private String introAgency;//推荐人代码
    private String upAgent;//主管工号
    private String othupAgent;//分组代码
    private String introBreakFlag;
    private LocalDateTime introCommStart;
    private LocalDateTime introCommEnd;
    private String eduManager;
    private String rearBreakFlag;
    private LocalDateTime rearCommStart;
    private LocalDateTime rearCommEnd;
    private String ascriptSeries;
    private LocalDateTime oldStartDate;
    private LocalDateTime oldEndDate;
    private LocalDateTime startDate;//职级起期
    private LocalDateTime astartDate;//上次职级起期
    private String assesstype;
    private String state;
    private String operator2;//历史记录操作人员
    private LocalDateTime makeDate2;//历史记录录入日期
    private String makeTime2;//历史记录录入时间HH:mm:ss
    private LocalDateTime modifyDate2;//历史记录修改日期
    private String modifyTime2;//历史记录修改时间HH:mm:ss
    private String edorno;//操作轨迹流水号
    private String operator;//操作人
    private LocalDateTime makeDate;//操作日期
    private String makeTime;//操作时间
    private LocalDateTime modifyDate;//修改日期
    private String modifyTime;//修改时间
    private String branchCode;//团队内码（必定为组内码）
    private String indexCalNo;
    private String agentKind;
    private String branchType;//渠道
    private String isConnMan;
    private String initGrade;//入司职级
    private String agentLine;
    private String insideFlag;
    private String speciFlag;
    private String branchType2;
    private String vipProperty;
    private String cmAgentGrade;
    private String cmAgentLastGrade;
    private String cmUpAgent;
    private LocalDateTime cmOldStartDate;
    private LocalDateTime cmOldEndDate;
    private LocalDateTime cmStartDate;
    private LocalDateTime cmAstartDate;
    private String cmAssessType;
    private String cmState;
    private String cmInitGrade;
    private LocalDateTime operateDate;

    @Id
    @Column(name = "AGENTCODE")
    public String getAgentCode() {
        return agentCode;
    }

    public void setAgentCode(String agentCode) {
        this.agentCode = agentCode;
    }

    @Column(name = "AGENTGROUP")
    public String getAgentGroup() {
        return agentGroup;
    }

    public void setAgentGroup(String agentGroup) {
        this.agentGroup = agentGroup;
    }

    @Column(name = "MANAGECOM")
    public String getManagetCom() {
        return managetCom;
    }

    public void setManagetCom(String managetCom) {
        this.managetCom = managetCom;
    }

    @Column(name = "AGENTSERIES")
    public String getAgentSeries() {
        return agentSeries;
    }

    public void setAgentSeries(String agentSeries) {
        this.agentSeries = agentSeries;
    }

    @Column(name = "AGENTGRADE")
    public String getAgentGrade() {
        return agentGrade;
    }

    public void setAgentGrade(String agentGrade) {
        this.agentGrade = agentGrade;
    }

    @Column(name = "AGENTLASTSERIES")
    public String getAgentLastSeries() {
        return agentLastSeries;
    }

    public void setAgentLastSeries(String agentLastSeries) {
        this.agentLastSeries = agentLastSeries;
    }

    @Column(name = "AGENTLASTGRADE")
    public String getAgentLastGrade() {
        return agentLastGrade;
    }

    public void setAgentLastGrade(String agentLastGrade) {
        this.agentLastGrade = agentLastGrade;
    }

    @Column(name = "INTROAGENCY")
    public String getIntroAgency() {
        return introAgency;
    }

    public void setIntroAgency(String introAgency) {
        this.introAgency = introAgency;
    }

    @Column(name = "UPAGENT")
    public String getUpAgent() {
        return upAgent;
    }

    public void setUpAgent(String upAgent) {
        this.upAgent = upAgent;
    }

    @Column(name = "OTHUPAGENT")
    public String getOthupAgent() {
        return othupAgent;
    }

    public void setOthupAgent(String othupAgent) {
        this.othupAgent = othupAgent;
    }

    @Column(name = "INTROBREAKFLAG")
    public String getIntroBreakFlag() {
        return introBreakFlag;
    }

    public void setIntroBreakFlag(String introBreakFlag) {
        this.introBreakFlag = introBreakFlag;
    }

    @Column(name = "INTROCOMMSTART")
    public LocalDateTime getIntroCommStart() {
        return introCommStart;
    }

    public void setIntroCommStart(LocalDateTime introCommStart) {
        this.introCommStart = introCommStart;
    }

    @Column(name = "INTROCOMMEND")
    public LocalDateTime getIntroCommEnd() {
        return introCommEnd;
    }

    public void setIntroCommEnd(LocalDateTime introCommEnd) {
        this.introCommEnd = introCommEnd;
    }

    @Column(name = "EDUMANAGER")
    public String getEduManager() {
        return eduManager;
    }

    public void setEduManager(String eduManager) {
        this.eduManager = eduManager;
    }

    @Column(name = "REARBREAKFLAG")
    public String getRearBreakFlag() {
        return rearBreakFlag;
    }

    public void setRearBreakFlag(String rearBreakFlag) {
        this.rearBreakFlag = rearBreakFlag;
    }

    @Column(name = "REARCOMMSTART")
    public LocalDateTime getRearCommStart() {
        return rearCommStart;
    }

    public void setRearCommStart(LocalDateTime rearCommStart) {
        this.rearCommStart = rearCommStart;
    }

    @Column(name = "REARCOMMEND")
    public LocalDateTime getRearCommEnd() {
        return rearCommEnd;
    }

    public void setRearCommEnd(LocalDateTime rearCommEnd) {
        this.rearCommEnd = rearCommEnd;
    }

    @Column(name = "ASCRIPTSERIES")
    public String getAscriptSeries() {
        return ascriptSeries;
    }

    public void setAscriptSeries(String ascriptSeries) {
        this.ascriptSeries = ascriptSeries;
    }

    @Column(name = "OLDSTARTDATE")
    public LocalDateTime getOldStartDate() {
        return oldStartDate;
    }

    public void setOldStartDate(LocalDateTime oldStartDate) {
        this.oldStartDate = oldStartDate;
    }

    @Column(name = "OLDENDDATE")
    public LocalDateTime getOldEndDate() {
        return oldEndDate;
    }

    public void setOldEndDate(LocalDateTime oldEndDate) {
        this.oldEndDate = oldEndDate;
    }

    @Column(name = "STARTDATE")
    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    @Column(name = "ASTARTDATE")
    public LocalDateTime getAstartDate() {
        return astartDate;
    }

    public void setAstartDate(LocalDateTime astartDate) {
        this.astartDate = astartDate;
    }

    @Column(name = "ASSESSTYPE")
    public String getAssesstype() {
        return assesstype;
    }

    public void setAssesstype(String assesstype) {
        this.assesstype = assesstype;
    }

    @Column(name = "STATE")
    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Column(name = "OPERATOR2")
    public String getOperator2() {
        return operator2;
    }

    public void setOperator2(String operator2) {
        this.operator2 = operator2;
    }

    @Column(name = "MAKEDATE2")
    public LocalDateTime getMakeDate2() {
        return makeDate2;
    }

    public void setMakeDate2(LocalDateTime makeDate2) {
        this.makeDate2 = makeDate2;
    }

    @Column(name = "MAKETIME2")
    public String getMakeTime2() {
        return makeTime2;
    }

    public void setMakeTime2(String makeTime2) {
        this.makeTime2 = makeTime2;
    }

    @Column(name = "MODIFYDATE2")
    public LocalDateTime getModifyDate2() {
        return modifyDate2;
    }

    public void setModifyDate2(LocalDateTime modifyDate2) {
        this.modifyDate2 = modifyDate2;
    }

    @Column(name = "MODIFYTIME2")
    public String getModifyTime2() {
        return modifyTime2;
    }

    public void setModifyTime2(String modifyTime2) {
        this.modifyTime2 = modifyTime2;
    }

    @Id
    @Column(name = "EDORNO", nullable = false)
    public String getEdorno() {
        return edorno;
    }

    public void setEdorno(String edorno) {
        this.edorno = edorno;
    }

    @Column(name = "OPERATOR")
    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    @Column(name = "MAKEDATE")
    public LocalDateTime getMakeDate() {
        return makeDate;
    }

    public void setMakeDate(LocalDateTime makeDate) {
        this.makeDate = makeDate;
    }

    @Column(name = "MAKETIME")
    public String getMakeTime() {
        return makeTime;
    }

    public void setMakeTime(String makeTime) {
        this.makeTime = makeTime;
    }

    @Column(name = "MODIFYDATE")
    public LocalDateTime getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(LocalDateTime modifyDate) {
        this.modifyDate = modifyDate;
    }

    @Column(name = "MODIFYTIME")
    public String getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(String modifyTime) {
        this.modifyTime = modifyTime;
    }

    @Column(name = "BRANCHCODE")
    public String getBranchCode() {
        return branchCode;
    }

    public void setBranchCode(String branchCode) {
        this.branchCode = branchCode;
    }

    @Column(name = "INDEXCALNO")
    public String getIndexCalNo() {
        return indexCalNo;
    }

    public void setIndexCalNo(String indexCalNo) {
        this.indexCalNo = indexCalNo;
    }

    @Column(name = "AGENTKIND")
    public String getAgentKind() {
        return agentKind;
    }

    public void setAgentKind(String agentKind) {
        this.agentKind = agentKind;
    }

    @Column(name = "BRANCHTYPE")
    public String getBranchType() {
        return branchType;
    }

    public void setBranchType(String branchType) {
        this.branchType = branchType;
    }

    @Column(name = "ISCONNMAN")
    public String getIsConnMan() {
        return isConnMan;
    }

    public void setIsConnMan(String isConnMan) {
        this.isConnMan = isConnMan;
    }

    @Column(name = "INITGRADE")
    public String getInitGrade() {
        return initGrade;
    }

    public void setInitGrade(String initGrade) {
        this.initGrade = initGrade;
    }

    @Column(name = "AGENTLINE")
    public String getAgentLine() {
        return agentLine;
    }

    public void setAgentLine(String agentLine) {
        this.agentLine = agentLine;
    }

    @Column(name = "INSIDEFLAG")
    public String getInsideFlag() {
        return insideFlag;
    }

    public void setInsideFlag(String insideFlag) {
        this.insideFlag = insideFlag;
    }

    @Column(name = "SPECIFLAG")
    public String getSpeciFlag() {
        return speciFlag;
    }

    public void setSpeciFlag(String speciFlag) {
        this.speciFlag = speciFlag;
    }

    @Column(name = "BRANCHTYPE2")
    public String getBranchType2() {
        return branchType2;
    }

    public void setBranchType2(String branchType2) {
        this.branchType2 = branchType2;
    }

    @Column(name = "VIPPROPERTY")
    public String getVipProperty() {
        return vipProperty;
    }

    public void setVipProperty(String vipProperty) {
        this.vipProperty = vipProperty;
    }

    @Column(name = "CMAGENTGRADE")
    public String getCmAgentGrade() {
        return cmAgentGrade;
    }

    public void setCmAgentGrade(String cmAgentGrade) {
        this.cmAgentGrade = cmAgentGrade;
    }

    @Column(name = "CMAGENTLASTGRADE")
    public String getCmAgentLastGrade() {
        return cmAgentLastGrade;
    }

    public void setCmAgentLastGrade(String cmAgentLastGrade) {
        this.cmAgentLastGrade = cmAgentLastGrade;
    }

    @Column(name = "CMUPAGENT")
    public String getCmUpAgent() {
        return cmUpAgent;
    }

    public void setCmUpAgent(String cmUpAgent) {
        this.cmUpAgent = cmUpAgent;
    }

    @Column(name = "CMOLDSTARTDATE")
    public LocalDateTime getCmOldStartDate() {
        return cmOldStartDate;
    }

    public void setCmOldStartDate(LocalDateTime cmOldStartDate) {
        this.cmOldStartDate = cmOldStartDate;
    }

    @Column(name = "CMOLDENDDATE")
    public LocalDateTime getCmOldEndDate() {
        return cmOldEndDate;
    }

    public void setCmOldEndDate(LocalDateTime cmOldEndDate) {
        this.cmOldEndDate = cmOldEndDate;
    }

    @Column(name = "CMSTARTDATE")
    public LocalDateTime getCmStartDate() {
        return cmStartDate;
    }

    public void setCmStartDate(LocalDateTime cmStartDate) {
        this.cmStartDate = cmStartDate;
    }

    @Column(name = "CMASTARTDATE")
    public LocalDateTime getCmAstartDate() {
        return cmAstartDate;
    }

    public void setCmAstartDate(LocalDateTime cmAstartDate) {
        this.cmAstartDate = cmAstartDate;
    }

    @Column(name = "CMASSESSTYPE")
    public String getCmAssessType() {
        return cmAssessType;
    }

    public void setCmAssessType(String cmAssessType) {
        this.cmAssessType = cmAssessType;
    }

    @Column(name = "CMSTATE")
    public String getCmState() {
        return cmState;
    }

    public void setCmState(String cmState) {
        this.cmState = cmState;
    }

    @Column(name = "CMINITGRADE")
    public String getCmInitGrade() {
        return cmInitGrade;
    }

    public void setCmInitGrade(String cmInitGrade) {
        this.cmInitGrade = cmInitGrade;
    }

    @Column(name = "OPERATEDATE")
    public LocalDateTime getOperateDate() {
        return operateDate;
    }

    public void setOperateDate(LocalDateTime operateDate) {
        this.operateDate = operateDate;
    }
}
