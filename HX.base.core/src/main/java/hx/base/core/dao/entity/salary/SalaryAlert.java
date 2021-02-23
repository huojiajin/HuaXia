package hx.base.core.dao.entity.salary;

import hx.base.core.dao.dict.salary.PrizeType;
import hx.base.core.dao.entity.common.AbstractInsertTimeEntity;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * @ClassName: SalaryAlert
 * @Description: 核心薪资项目预警表实体
 * @Author HuoJiaJin
 * @Date 2021/2/7 2:53
 * @Version 1.0
 **/
@Entity
@Table(name = "hx_salary_alert")
public class SalaryAlert extends AbstractInsertTimeEntity {

    private LocalDate month;//月份yyyy-MM-01
    private String campName;//营服名称
    private String campCode;//营服代码
    private String sectionName;//部名称
    private String sectionCode;//部名称
    private String groupName;//组名称
    private String groupCode;//组名称
    private String name;//员工名称
    private String employeeCode;//员工代码
    private PrizeType type;//奖项类别
    private double salary;//预计收入
    private double gap;//差距
    private boolean fullPayment;//是否足额发放
    private Double allocations;//发放比例
    private String completeAlertId;//补发ID
    private String remark;//备注

    @Column(name = "month")
    public LocalDate getMonth() {
        return month;
    }

    public void setMonth(LocalDate month) {
        this.month = month;
    }

    @Column(name = "camp_name")
    public String getCampName() {
        return campName;
    }

    public void setCampName(String campName) {
        this.campName = campName;
    }

    @Column(name = "camp_code")
    public String getCampCode() {
        return campCode;
    }

    public void setCampCode(String campCode) {
        this.campCode = campCode;
    }

    @Column(name = "section_name")
    public String getSectionName() {
        return sectionName;
    }

    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }

    @Column(name = "section_code")
    public String getSectionCode() {
        return sectionCode;
    }

    public void setSectionCode(String sectionCode) {
        this.sectionCode = sectionCode;
    }

    @Column(name = "group_name")
    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    @Column(name = "group_code")
    public String getGroupCode() {
        return groupCode;
    }

    public void setGroupCode(String groupCode) {
        this.groupCode = groupCode;
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "employee_code")
    public String getEmployeeCode() {
        return employeeCode;
    }

    public void setEmployeeCode(String employeeCode) {
        this.employeeCode = employeeCode;
    }

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    public PrizeType getType() {
        return type;
    }

    public void setType(PrizeType type) {
        this.type = type;
    }

    @Column(name = "salary")
    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    @Column(name = "gap")
    public double getGap() {
        return gap;
    }

    public void setGap(double gap) {
        this.gap = gap;
    }

    @Column(name = "full_payment")
    public boolean isFullPayment() {
        return fullPayment;
    }

    public void setFullPayment(boolean fullPayment) {
        this.fullPayment = fullPayment;
    }

    @Column(name = "allocations")
    public Double getAllocations() {
        return allocations;
    }

    public void setAllocations(Double allocations) {
        this.allocations = allocations;
    }

    @Column(name = "complete_alert_id")
    public String getCompleteAlertId() {
        return completeAlertId;
    }

    public void setCompleteAlertId(String completeAlertId) {
        this.completeAlertId = completeAlertId;
    }

    @Column(name = "remark")
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
