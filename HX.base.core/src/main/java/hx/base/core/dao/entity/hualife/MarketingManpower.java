package hx.base.core.dao.entity.hualife;

import hx.base.core.dao.entity.common.BaseEntity;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * @name: MarketingManpower
 * @description: 营销人力表
 * @author: huojiajin
 * @time: 2020/6/17 15:18
 */
@Entity
@Table(name = "D_AGENT_PROVINCE_YX", indexes = {
        @Index(columnList = "AGENTGRADE", name = "D_AGENT_PROVINCE_INDEX"),
        @Index(columnList = "AGENTCODE", name = "D_AGENT_PROVINCE_INDEX1"),
        @Index(columnList = "DEPTCODE3", name = "D_AGENT_PROVINCE_INDEX2"),
        @Index(columnList = "DEPTCODE4", name = "D_AGENT_PROVINCE_INDEX3"),
        @Index(columnList = "RECOMMAGENTCODE", name = "D_AGENT_PROVINCE_INDEX4")
})
public class MarketingManpower extends BaseEntity {

    private String agentCode;//营销员编码
    private String name;//营销员姓名
    private LocalDate employDate;//入司日期
    private String initGrade;//入司职级
    private String initGradeName;//入司职级名称
    private String agentGrade;//营销员级别
    private String agentGradeName;//营销员级别名称
    private LocalDate startDate;//本职级起聘日期
    private String agentState;//营销员状态
    private String provinceComCode;//分公司代码
    private String provinceComsName;//分公司名称
    private String branchCode;//中支代码
    private String branchsName;//中支名称
    private String saledeptCode;//支公司代码
    private String saledeptName;//支公司名称
    private String deptCode1;//营业区代码
    private String deptName1;//营业区名称
    private String recommAgentCode;//推荐人代码
    private String recommAgentName;//推荐人姓名
    private String deptCode2;//总监区代码
    private String deptName2;//总监区名称
    private String deptCode3;//业务部代码
    private String deptName3;//业务部名称
    private String deptCode4;//业务组代码
    private String deptName4;//业务组名称
    private String agentCode1;//业务组经理代码
    private String name1;//业务组经理名称
    private String sex;//性别
    private LocalDate outworkDate;//离职日期
    private String idType;//证件类型
    private String idno;//证件号
    private String openClassId;//培训班编码
    private String classSource;//培训来源
    private String agentMobile;//手机号
    private LocalDate birthday;//出生日期
    private String directorAgent;//聘才主管
    private String befrom;//业务员来源
    private String branchType;//渠道类型 1个险 6收展

    @Id
    @Column(name = "AGENTCODE", nullable = false, length = 10)
    public String getAgentCode() {
        return agentCode;
    }

    public void setAgentCode(String agentCode) {
        this.agentCode = agentCode;
    }

    @Column(name = "NAME", length = 30)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "EMPLOYDATE")
    public LocalDate getEmployDate() {
        return employDate;
    }

    public void setEmployDate(LocalDate employDate) {
        this.employDate = employDate;
    }

    @Column(name = "INITGRADE", length = 6)
    public String getInitGrade() {
        return initGrade;
    }

    public void setInitGrade(String initGrade) {
        this.initGrade = initGrade;
    }

    @Column(name = "INITGRADENAME", length = 50)
    public String getInitGradeName() {
        return initGradeName;
    }

    public void setInitGradeName(String initGradeName) {
        this.initGradeName = initGradeName;
    }

    @Column(name = "AGENTGRADE", length = 6)
    public String getAgentGrade() {
        return agentGrade;
    }

    public void setAgentGrade(String agentGrade) {
        this.agentGrade = agentGrade;
    }

    @Column(name = "AGENTGRADENAME", length = 50)
    public String getAgentGradeName() {
        return agentGradeName;
    }

    public void setAgentGradeName(String agentGradeName) {
        this.agentGradeName = agentGradeName;
    }

    @Column(name = "STARTDATE")
    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    @Column(name = "AGENTSTATE", length = 10)
    public String getAgentState() {
        return agentState;
    }

    public void setAgentState(String agentState) {
        this.agentState = agentState;
    }

    @Column(name = "PROVINCECOMCODE", nullable = false, length = 10)
    public String getProvinceComCode() {
        return provinceComCode;
    }

    public void setProvinceComCode(String provinceComCode) {
        this.provinceComCode = provinceComCode;
    }

    @Column(name = "PROVINCECOMSNAME", length = 200)
    public String getProvinceComsName() {
        return provinceComsName;
    }

    public void setProvinceComsName(String provinceComsName) {
        this.provinceComsName = provinceComsName;
    }

    @Column(name = "BRANCHCODE", nullable = false, length = 10)
    public String getBranchCode() {
        return branchCode;
    }

    public void setBranchCode(String branchCode) {
        this.branchCode = branchCode;
    }

    @Column(name = "BRANCHSNAME", length = 200)
    public String getBranchsName() {
        return branchsName;
    }

    public void setBranchsName(String branchsName) {
        this.branchsName = branchsName;
    }

    @Column(name = "SALEDEPTCODE", nullable = false, length = 10)
    public String getSaledeptCode() {
        return saledeptCode;
    }

    public void setSaledeptCode(String saledeptCode) {
        this.saledeptCode = saledeptCode;
    }

    @Column(name = "SALEDEPTNAME", length = 200)
    public String getSaledeptName() {
        return saledeptName;
    }

    public void setSaledeptName(String saledeptName) {
        this.saledeptName = saledeptName;
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

    @Column(name = "RECOMMAGENTCODE", length = 10)
    public String getRecommAgentCode() {
        return recommAgentCode;
    }

    public void setRecommAgentCode(String recommAgentCode) {
        this.recommAgentCode = recommAgentCode;
    }

    @Column(name = "RECOMMAGENTNAME", length = 30)
    public String getRecommAgentName() {
        return recommAgentName;
    }

    public void setRecommAgentName(String recommAgentName) {
        this.recommAgentName = recommAgentName;
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

    @Column(name = "AGENTCODE1", length = 10)
    public String getAgentCode1() {
        return agentCode1;
    }

    public void setAgentCode1(String agentCode1) {
        this.agentCode1 = agentCode1;
    }

    @Column(name = "NAME1", length = 30)
    public String getName1() {
        return name1;
    }

    public void setName1(String name1) {
        this.name1 = name1;
    }

    @Column(name = "SEX", length = 10)
    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    @Column(name = "OUTWORKDATE")
    public LocalDate getOutworkDate() {
        return outworkDate;
    }

    public void setOutworkDate(LocalDate outworkDate) {
        this.outworkDate = outworkDate;
    }

    @Column(name = "IDTYPE", length = 200)
    public String getIdType() {
        return idType;
    }

    public void setIdType(String idType) {
        this.idType = idType;
    }

    @Column(name = "IDNO", length = 20)
    public String getIdno() {
        return idno;
    }

    public void setIdno(String idno) {
        this.idno = idno;
    }

    @Column(name = "OPENCLASSID", length = 30)
    public String getOpenClassId() {
        return openClassId;
    }

    public void setOpenClassId(String openClassId) {
        this.openClassId = openClassId;
    }

    @Column(name = "CLASSSOURCE", length = 20)
    public String getClassSource() {
        return classSource;
    }

    public void setClassSource(String classSource) {
        this.classSource = classSource;
    }

    @Column(name = "AGENTMOBILE", length = 18)
    public String getAgentMobile() {
        return agentMobile;
    }

    public void setAgentMobile(String agentMobile) {
        this.agentMobile = agentMobile;
    }

    @Column(name = "BIRTHDAY")
    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    @Column(name = "DIRECTORAGENT", length = 200)
    public String getDirectorAgent() {
        return directorAgent;
    }

    public void setDirectorAgent(String directorAgent) {
        this.directorAgent = directorAgent;
    }

    @Column(name = "BEFROM", length = 10)
    public String getBefrom() {
        return befrom;
    }

    public void setBefrom(String befrom) {
        this.befrom = befrom;
    }

    @Column(name = "BRANCHTYPE", length = 4)
    public String getBranchType() {
        return branchType;
    }

    public void setBranchType(String branchType) {
        this.branchType = branchType;
    }
}
