package hx.base.core.dao.entity.staff;

import hx.base.core.dao.entity.common.AbstractInsertTimeEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;

/**
 * @ClassName TrainStaff
 * @Description 培训场次参会人员表
 * @Author HuoJiaJin
 * @Date 2020/11/8 2:30
 * @Version 1.0
 **/
@Entity
@Table(name = "hx_train_staff", indexes = {
        @Index(name = "hx_train_staff_index", columnList = "")
})
public class TrainStaff extends AbstractInsertTimeEntity {

    private String agentCode;//营销员编码
    private String name;//营销员姓名
    private String mobile;//营销员手机号
    private String deptCode1;//营业区代码
    private String deptName1;//营业区名称
    private String deptCode2;//总监区代码
    private String deptName2;//总监区名称
    private String deptCode3;//业务部代码
    private String deptName3;//业务部名称
    private String deptCode4;//业务组代码
    private String deptName4;//业务组名称

    @Column(name = "agent_code")
    public String getAgentCode() {
        return agentCode;
    }

    public void setAgentCode(String agentCode) {
        this.agentCode = agentCode;
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "mobile")
    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    @Column(name = "dept_code1")
    public String getDeptCode1() {
        return deptCode1;
    }

    public void setDeptCode1(String deptCode1) {
        this.deptCode1 = deptCode1;
    }

    @Column(name = "dept_name1")
    public String getDeptName1() {
        return deptName1;
    }

    public void setDeptName1(String deptName1) {
        this.deptName1 = deptName1;
    }

    @Column(name = "dept_code2")
    public String getDeptCode2() {
        return deptCode2;
    }

    public void setDeptCode2(String deptCode2) {
        this.deptCode2 = deptCode2;
    }

    @Column(name = "dept_name2")
    public String getDeptName2() {
        return deptName2;
    }

    public void setDeptName2(String deptName2) {
        this.deptName2 = deptName2;
    }

    @Column(name = "dept_code3")
    public String getDeptCode3() {
        return deptCode3;
    }

    public void setDeptCode3(String deptCode3) {
        this.deptCode3 = deptCode3;
    }

    @Column(name = "dept_name3")
    public String getDeptName3() {
        return deptName3;
    }

    public void setDeptName3(String deptName3) {
        this.deptName3 = deptName3;
    }

    @Column(name = "dept_code4")
    public String getDeptCode4() {
        return deptCode4;
    }

    public void setDeptCode4(String deptCode4) {
        this.deptCode4 = deptCode4;
    }

    @Column(name = "dept_name4")
    public String getDeptName4() {
        return deptName4;
    }

    public void setDeptName4(String deptName4) {
        this.deptName4 = deptName4;
    }
}
