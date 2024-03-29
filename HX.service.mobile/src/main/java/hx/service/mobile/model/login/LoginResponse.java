package hx.service.mobile.model.login;

import hx.base.core.dao.entity.common.BaseEntity;

import java.util.List;

/**
 * @ClassName LoginUserModel
 * @Description 登录用户信息Model
 * @Author HuoJiaJin
 * @Date 2020/6/26 14:51
 * @Version 1.0
 **/
public class LoginResponse extends BaseEntity {

    private String employeeNum;//工号
    private String name;//姓名
    private String avatar;//头像URL
    private String grade;//职级
    private String star;//星级
    private String sectionRateGrade;//部评级
    private String groupRateGrade;//组评级
    private Integer integral;//当前积分
    private String enryTme;//入职时间
    private List<Integer> resourceCodeList;//资源代码集合
    private boolean hasSignIn;//是否已签到
    private Integer type;//展示类型 1、雷达图 2、入职时长 3、内勤
    private String campName;//营服名称
    private String sectionName;//部名称
    private String groupName;//组名称

    public String getEmployeeNum() {
        return employeeNum;
    }

    public void setEmployeeNum(String employeeNum) {
        this.employeeNum = employeeNum;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getStar() {
        return star;
    }

    public void setStar(String star) {
        this.star = star;
    }

    public String getSectionRateGrade() {
        return sectionRateGrade;
    }

    public void setSectionRateGrade(String sectionRateGrade) {
        this.sectionRateGrade = sectionRateGrade;
    }

    public String getGroupRateGrade() {
        return groupRateGrade;
    }

    public void setGroupRateGrade(String groupRateGrade) {
        this.groupRateGrade = groupRateGrade;
    }

    public Integer getIntegral() {
        return integral;
    }

    public void setIntegral(Integer integral) {
        this.integral = integral;
    }

    public String getEnryTme() {
        return enryTme;
    }

    public void setEnryTme(String enryTme) {
        this.enryTme = enryTme;
    }

    public List<Integer> getResourceCodeList() {
        return resourceCodeList;
    }

    public void setResourceCodeList(List<Integer> resourceCodeList) {
        this.resourceCodeList = resourceCodeList;
    }

    public boolean isHasSignIn() {
        return hasSignIn;
    }

    public void setHasSignIn(boolean hasSignIn) {
        this.hasSignIn = hasSignIn;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getCampName() {
        return campName;
    }

    public void setCampName(String campName) {
        this.campName = campName;
    }

    public String getSectionName() {
        return sectionName;
    }

    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }
}
