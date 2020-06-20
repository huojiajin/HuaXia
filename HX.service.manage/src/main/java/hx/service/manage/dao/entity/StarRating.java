package hx.service.manage.dao.entity;

import hx.service.manage.dao.entity.common.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;

/**
 * @name: StarRating
 * @description: 凤凰社星级表
 * @author: huojiajin
 * @time: 2020/6/17 16:40
 */
@Entity
@Table(name = "lafhagent")
public class StarRating extends BaseEntity {
    private String agentCode;//人员编码
    private String manageCom;//管理机构编码
    private String fhagentGrade;//凤凰社星级
    private LocalDate employDate;//入司时间
    private LocalDate startDate;//进入凤凰社起期
    private String branchType;//渠道码
    private String operator;
    private LocalDate makeDate;
    private Character makeTime;
    private LocalDate modifyDate;
    private Character modifyTime;
    private LocalDate operateDate = LocalDate.parse("2000-01-01");

    @Id
    @Column(name = "AGENTCODE", nullable = false, length = 20)
    public String getAgentCode() {
        return agentCode;
    }

    public void setAgentCode(String agentCode) {
        this.agentCode = agentCode;
    }

    @Column(name = "MANAGECOM", length = 10)
    public String getManageCom() {
        return manageCom;
    }

    public void setManageCom(String manageCom) {
        this.manageCom = manageCom;
    }

    @Column(name = "FHAGENTGRADE", length = 6)
    public String getFhagentGrade() {
        return fhagentGrade;
    }

    public void setFhagentGrade(String fhagentGrade) {
        this.fhagentGrade = fhagentGrade;
    }

    @Column(name = "EMPLOYDATE")
    public LocalDate getEmployDate() {
        return employDate;
    }

    public void setEmployDate(LocalDate employDate) {
        this.employDate = employDate;
    }

    @Column(name = "STARTDATE")
    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    @Column(name = "BRANCHTYPE", length = 2)
    public String getBranchType() {
        return branchType;
    }

    public void setBranchType(String branchType) {
        this.branchType = branchType;
    }

    @Column(name = "OPERATOR", length = 10)
    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    @Column(name = "MAKEDATE")
    public LocalDate getMakeDate() {
        return makeDate;
    }

    public void setMakeDate(LocalDate makeDate) {
        this.makeDate = makeDate;
    }

    @Column(name = "MAKETIME", length = 8)
    public Character getMakeTime() {
        return makeTime;
    }

    public void setMakeTime(Character makeTime) {
        this.makeTime = makeTime;
    }

    @Column(name = "MODIFYDATE")
    public LocalDate getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(LocalDate modifyDate) {
        this.modifyDate = modifyDate;
    }

    @Column(name = "MODIFYTIME", length = 8)
    public Character getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Character modifyTime) {
        this.modifyTime = modifyTime;
    }

    @Column(name = "OPERATEDATE")
    public LocalDate getOperateDate() {
        return operateDate;
    }

    public void setOperateDate(LocalDate operateDate) {
        this.operateDate = operateDate;
    }
}
