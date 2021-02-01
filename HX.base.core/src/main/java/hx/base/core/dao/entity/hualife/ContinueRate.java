package hx.base.core.dao.entity.hualife;

import hx.base.core.dao.entity.common.BaseEntity;
import hx.base.core.dao.entity.hualife.composite.ContinueRateId;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * @name: PersistencyRate
 * @description: 个人继续率表
 * @author: huojiajin
 * @time: 2020/6/17 15:05
 */
@Entity
@Table(name = "FACT_XGPST_YEAR_AGENT")
@IdClass(ContinueRateId.class)
public class ContinueRate extends BaseEntity {

    private String statMonth;//统计月
    private String manageCom;//管理机构
    private String salechnlFlag;//销售渠道
    private String agentCode;//业务员编码
    private String agentGrade;//业务员职级
    private String agentState;//业务员状态
    private String agentNoFlag;//是否递归标志
    private String selfFlag;//自互保件标志
    private Double writtenPrem;//应收保费
    private Long writtenProductnum;//应收件数
    private Double paidPrem;//实收保费
    private Long paidProductnum;//实收件数
    private Double premRate;//保费继续率
    private Double numRate;//件数继续率
    private Double allRate;//综合继续率
    private Long calType;//统计类型
    private LocalDate timeStamp;//日期戳
    private LocalDate loadDate;//加载日期

    @Id
    @Column(name = "STAT_MONTH", length = 8)
    public String getStatMonth() {
        return statMonth;
    }

    public void setStatMonth(String statMonth) {
        this.statMonth = statMonth;
    }

    @Column(name = "MANAGECOM", length = 8)
    public String getManageCom() {
        return manageCom;
    }

    public void setManageCom(String manageCom) {
        this.manageCom = manageCom;
    }

    @Column(name = "SALECHNL_FLAG", length = 5)
    public String getSalechnlFlag() {
        return salechnlFlag;
    }

    public void setSalechnlFlag(String salechnlFlag) {
        this.salechnlFlag = salechnlFlag;
    }

    @Id
    @Column(name = "AGENT_CODE", length = 20)
    public String getAgentCode() {
        return agentCode;
    }

    public void setAgentCode(String agentCode) {
        this.agentCode = agentCode;
    }

    @Column(name = "AGENT_GRADE", length = 6)
    public String getAgentGrade() {
        return agentGrade;
    }

    public void setAgentGrade(String agentGrade) {
        this.agentGrade = agentGrade;
    }

    @Column(name = "AGENT_STATE", length = 5)
    public String getAgentState() {
        return agentState;
    }

    public void setAgentState(String agentState) {
        this.agentState = agentState;
    }

    @Column(name = "AGENT_NO_FLAG", length = 1)
    public String getAgentNoFlag() {
        return agentNoFlag;
    }

    public void setAgentNoFlag(String agentNoFlag) {
        this.agentNoFlag = agentNoFlag;
    }

    @Column(name = "SELF_FLAG", length = 1)
    public String getSelfFlag() {
        return selfFlag;
    }

    public void setSelfFlag(String selfFlag) {
        this.selfFlag = selfFlag;
    }

    @Column(name = "WRITTEN_PREM", length = 16)
    public Double getWrittenPrem() {
        return writtenPrem;
    }

    public void setWrittenPrem(Double writtenPrem) {
        this.writtenPrem = writtenPrem;
    }

    @Column(name = "WRITTEN_PRODUCTNUM", length = 10)
    public Long getWrittenProductnum() {
        return writtenProductnum;
    }

    public void setWrittenProductnum(Long writtenProductnum) {
        this.writtenProductnum = writtenProductnum;
    }

    @Column(name = "PAID_PREM", length = 16)
    public Double getPaidPrem() {
        return paidPrem;
    }

    public void setPaidPrem(Double paidPrem) {
        this.paidPrem = paidPrem;
    }

    @Column(name = "PAID_PRODUCTNUM", length = 10)
    public Long getPaidProductnum() {
        return paidProductnum;
    }

    public void setPaidProductnum(Long paidProductnum) {
        this.paidProductnum = paidProductnum;
    }

    @Column(name = "PREM_RATE", length = 20)
    public Double getPremRate() {
        return premRate;
    }

    public void setPremRate(Double premRate) {
        this.premRate = premRate;
    }

    @Column(name = "NUM_RATE", length = 20)
    public Double getNumRate() {
        return numRate;
    }

    public void setNumRate(Double numRate) {
        this.numRate = numRate;
    }

    @Column(name = "ALL_RATE", length = 20)
    public Double getAllRate() {
        return allRate;
    }

    public void setAllRate(Double allRate) {
        this.allRate = allRate;
    }

    @Id
    @Column(name = "CALTYPE")
    public Long getCalType() {
        return calType;
    }

    public void setCalType(Long calType) {
        this.calType = calType;
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
}
