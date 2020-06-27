package hx.service.manage.manage.model.test.integral;

import hx.base.core.dao.entity.common.BaseEntity;

/**
 * @ClassName IntegralQueryModel
 * @Description 试卷查询返回Model
 * @Author HuoJiaJin
 * @Date 2020/6/23 19:50
 * @Version 1.0
 **/
public class IntegralQueryModel extends BaseEntity {

    private String employeeNum;//工号
    private String name;//员工姓名
    private int allNum;//总积分
    private int signInNum;//每日签到分数
    private int courseNum;//资料学习积分
    private int testNum;//参与考试积分
    private int performNum;//每月开单积分

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

    public int getAllNum() {
        return allNum;
    }

    public void setAllNum(int allNum) {
        this.allNum = allNum;
    }

    public int getSignInNum() {
        return signInNum;
    }

    public void setSignInNum(int signInNum) {
        this.signInNum = signInNum;
    }

    public int getCourseNum() {
        return courseNum;
    }

    public void setCourseNum(int courseNum) {
        this.courseNum = courseNum;
    }

    public int getTestNum() {
        return testNum;
    }

    public void setTestNum(int testNum) {
        this.testNum = testNum;
    }

    public int getPerformNum() {
        return performNum;
    }

    public void setPerformNum(int performNum) {
        this.performNum = performNum;
    }
}
