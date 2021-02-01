package hx.base.core.dao.entity.hualife;

import hx.base.core.dao.entity.common.BaseEntity;
import hx.base.core.dao.entity.hualife.composite.IncubationId;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * @name: Incubation
 * @description: 育成关系表
 * @author: huojiajin
 * @time: 2020/6/17 17:18
 */
@Entity
@Table(name = "LARearRelation", indexes = {
        @Index(columnList = "REARAGENTCODE", name = "LARearRelation_index")
})
@IdClass(IncubationId.class)
public class Incubation extends BaseEntity {

    private String rearLevel;//育成层级
    private Long rearedgens;//育成代数
    private String agentCode;//被育成人
    private String rearAgentCode;//育成人
    private String agentGroup;
    private LocalDate startDate;//育成起期
    private LocalDate endDate;//育成止期
    private String rearFlag;
    private String rearCommFlag;
    private Long rearStartYear;
    private LocalDate makeDate;
    private String makeTime;
    private LocalDate modifyDate;
    private String modifyTime;
    private String operator;
    private String rearAgentGroup;
    private Long commMonth;
    private String indexcalno;
    private LocalDate operateDate = LocalDate.parse("2000-01-01");

    @Column(name = "REARLEVEL", nullable = false, length = 2)
    public String getRearLevel() {
        return rearLevel;
    }

    public void setRearLevel(String rearLevel) {
        this.rearLevel = rearLevel;
    }

    @Column(name = "REAREDGENS", nullable = false)
    public Long getRearedgens() {
        return rearedgens;
    }

    public void setRearedgens(Long rearedgens) {
        this.rearedgens = rearedgens;
    }

    @Id
    @Column(name = "AGENTCODE", nullable = false, length = 10)
    public String getAgentCode() {
        return agentCode;
    }

    public void setAgentCode(String agentCode) {
        this.agentCode = agentCode;
    }

    @Id
    @Column(name = "REARAGENTCODE", length = 10)
    public String getRearAgentCode() {
        return rearAgentCode;
    }

    public void setRearAgentCode(String rearAgentCode) {
        this.rearAgentCode = rearAgentCode;
    }

    @Column(name = "AGENTGROUP", nullable = false, length = 12)
    public String getAgentGroup() {
        return agentGroup;
    }

    public void setAgentGroup(String agentGroup) {
        this.agentGroup = agentGroup;
    }

    @Column(name = "STARTDATE")
    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    @Column(name = "ENDDATE")
    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    @Column(name = "REARFLAG", nullable = false, length = 2)
    public String getRearFlag() {
        return rearFlag;
    }

    public void setRearFlag(String rearFlag) {
        this.rearFlag = rearFlag;
    }

    @Column(name = "REARCOMMFLAG", nullable = false, length = 2)
    public String getRearCommFlag() {
        return rearCommFlag;
    }

    public void setRearCommFlag(String rearCommFlag) {
        this.rearCommFlag = rearCommFlag;
    }

    @Column(name = "REARSTARTYEAR", nullable = false)
    public Long getRearStartYear() {
        return rearStartYear;
    }

    public void setRearStartYear(Long rearStartYear) {
        this.rearStartYear = rearStartYear;
    }

    @Column(name = "MAKEDATE", nullable = false)
    public LocalDate getMakeDate() {
        return makeDate;
    }

    public void setMakeDate(LocalDate makeDate) {
        this.makeDate = makeDate;
    }

    @Column(name = "MAKETIME", nullable = false, length = 8)
    public String getMakeTime() {
        return makeTime;
    }

    public void setMakeTime(String makeTime) {
        this.makeTime = makeTime;
    }

    @Column(name = "MODIFYDATE", nullable = false)
    public LocalDate getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(LocalDate modifyDate) {
        this.modifyDate = modifyDate;
    }

    @Column(name = "MODIFYTIME", nullable = false, length = 8)
    public String getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(String modifyTime) {
        this.modifyTime = modifyTime;
    }

    @Column(name = "OPERATOR", length = 10)
    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    @Column(name = "REARAGENTGROUP", length = 12)
    public String getRearAgentGroup() {
        return rearAgentGroup;
    }

    public void setRearAgentGroup(String rearAgentGroup) {
        this.rearAgentGroup = rearAgentGroup;
    }

    @Column(name = "COMMMONTH")
    public Long getCommMonth() {
        return commMonth;
    }

    public void setCommMonth(Long commMonth) {
        this.commMonth = commMonth;
    }

    @Column(name = "INDEXCALNO", length = 8)
    public String getIndexcalno() {
        return indexcalno;
    }

    public void setIndexcalno(String indexcalno) {
        this.indexcalno = indexcalno;
    }

    @Column(name = "OPERATEDATE", nullable = false)
    public LocalDate getOperateDate() {
        return operateDate;
    }

    public void setOperateDate(LocalDate operateDate) {
        this.operateDate = operateDate;
    }
}
