package hx.service.mobile.manage.model.test.integral;

import hx.base.core.dao.entity.common.BaseEntity;

/**
 * @ClassName SignInResponse
 * @Description 签到Response
 * @Author HuoJiaJin
 * @Date 2020/6/27 20:14
 * @Version 1.0
 **/
public class SignInResponse extends BaseEntity {

    private int integral;//积分
    private String weekSign;//周签到情况
    private String weekIntegral;//周获取积分情况

    public int getIntegral() {
        return integral;
    }

    public void setIntegral(int integral) {
        this.integral = integral;
    }

    public String getWeekSign() {
        return weekSign;
    }

    public void setWeekSign(String weekSign) {
        this.weekSign = weekSign;
    }

    public String getWeekIntegral() {
        return weekIntegral;
    }

    public void setWeekIntegral(String weekIntegral) {
        this.weekIntegral = weekIntegral;
    }
}
