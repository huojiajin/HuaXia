package hx.service.manage.dao.entity.test.integral;

import hx.service.manage.dao.entity.common.AbstractInsertTimeEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;

/**
 * @ClassName Integral
 * @Description 积分表
 * @Author HuoJiaJin
 * @Date 2020/6/23 19:30
 * @Version 1.0
 **/
@Entity
@Table(name = "hx_integral", indexes = {
        @Index(columnList = "month", name = "hx_integral_index")
})
public class Integral extends AbstractInsertTimeEntity {

    private String month;//年月yyyy-MM
    private String agentCode;//营销员编码
    private String name;//员工姓名
    private int allNum;//总积分
    private int signInNum;//每日签到分数
    private int courseNum;//资料学习积分
    private int testNum;//参与考试积分
    private int performNum;//每月开单积分

    @Column(name = "month")
    public String getMonth() {
        return month;
    }

    public void setMonth(String yearMonth) {
        this.month = yearMonth;
    }

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

    @Column(name = "all_num")
    public int getAllNum() {
        return allNum;
    }

    public void setAllNum(int allNum) {
        this.allNum = allNum;
    }

    @Column(name = "sign_in_num")
    public int getSignInNum() {
        return signInNum;
    }

    public void setSignInNum(int signInNum) {
        this.signInNum = signInNum;
    }

    @Column(name = "course_num")
    public int getCourseNum() {
        return courseNum;
    }

    public void setCourseNum(int courseNum) {
        this.courseNum = courseNum;
    }

    @Column(name = "test_num")
    public int getTestNum() {
        return testNum;
    }

    public void setTestNum(int testNum) {
        this.testNum = testNum;
    }

    @Column(name = "perform_num")
    public int getPerformNum() {
        return performNum;
    }

    public void setPerformNum(int performNum) {
        this.performNum = performNum;
    }
}
