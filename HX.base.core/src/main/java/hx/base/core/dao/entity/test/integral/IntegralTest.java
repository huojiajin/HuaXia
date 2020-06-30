package hx.base.core.dao.entity.test.integral;

import hx.base.core.dao.entity.common.AbstractInsertTimeEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;

/**
 * @name: IntegralTest
 * @description: 考试积分表
 * @author: huojiajin
 * @time: 2020/6/30 23:00
 */
@Entity
@Table(name = "hx_integral_test", indexes = {
        @Index(columnList = "agent_code", name = "hx_integral_test_index")
})
public class IntegralTest extends AbstractInsertTimeEntity {

    private String agentCode;//员工编码
    private String paperId;//试卷ID
    private int integral;//积分

    @Column(name = "agent_code")
    public String getAgentCode() {
        return agentCode;
    }

    public void setAgentCode(String agentCode) {
        this.agentCode = agentCode;
    }

    @Column(name = "paper_id")
    public String getPaperId() {
        return paperId;
    }

    public void setPaperId(String paperId) {
        this.paperId = paperId;
    }

    @Column(name = "integral")
    public int getIntegral() {
        return integral;
    }

    public void setIntegral(int integral) {
        this.integral = integral;
    }
}
