package hx.base.core.dao.entity.test.integral;

import hx.base.core.dao.entity.common.AbstractInsertTimeEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;
import java.time.LocalDate;

/**
 * @ClassName IntegralSignIn
 * @Description 签到积分表
 * @Author HuoJiaJin
 * @Date 2020/6/26 17:16
 * @Version 1.0
 **/
@Entity
@Table(name = "hx_integral_sign_in", indexes = {
        @Index(columnList = "agent_code", name = "hx_integral_sign_in_index")
})
public class IntegralSignIn extends AbstractInsertTimeEntity {

    private String agentCode;//员工编号
    private LocalDate signInDate;//签到时间
    private int integral;//积分

    @Column(name = "agent_code")
    public String getAgentCode() {
        return agentCode;
    }

    public void setAgentCode(String agentCode) {
        this.agentCode = agentCode;
    }

    @Column(name = "sign_in_date")
    public LocalDate getSignInDate() {
        return signInDate;
    }

    public void setSignInDate(LocalDate signInTime) {
        this.signInDate = signInTime;
    }

    @Column(name = "integral")
    public int getIntegral() {
        return integral;
    }

    public void setIntegral(int integral) {
        this.integral = integral;
    }
}
