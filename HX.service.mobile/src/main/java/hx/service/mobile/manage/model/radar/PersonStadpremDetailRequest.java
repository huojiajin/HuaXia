package hx.service.mobile.manage.model.radar;

import hx.service.manage.manage.model.CommonRequest;

/**
 * @ClassName PersonStadpremDetailRequest
 * @Description 个人标保详情
 * @Author HuoJiaJin
 * @Date 2020/6/27 9:50
 * @Version 1.0
 **/
public class PersonStadpremDetailRequest extends CommonRequest {

    private int month;//月份
    private String agentCode;//员工编码

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public String getAgentCode() {
        return agentCode;
    }

    public void setAgentCode(String agentCode) {
        this.agentCode = agentCode;
    }
}
