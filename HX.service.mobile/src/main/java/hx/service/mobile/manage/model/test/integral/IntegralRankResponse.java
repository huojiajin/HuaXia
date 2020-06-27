package hx.service.mobile.manage.model.test.integral;

import hx.base.core.dao.entity.common.BaseEntity;

import java.util.List;

/**
 * @ClassName IntegralRankResponse
 * @Description 积分排行榜Response
 * @Author HuoJiaJin
 * @Date 2020/6/27 21:24
 * @Version 1.0
 **/
public class IntegralRankResponse extends BaseEntity {

    private String name;//姓名
    private int integral;//积分
    private int index;//当前排名
    private List<IntegralRankModel> integralList;//积分排行

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIntegral() {
        return integral;
    }

    public void setIntegral(int integral) {
        this.integral = integral;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public List<IntegralRankModel> getIntegralList() {
        return integralList;
    }

    public void setIntegralList(List<IntegralRankModel> integralList) {
        this.integralList = integralList;
    }
}
