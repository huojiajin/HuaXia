package hx.service.mobile.manage.model.test.integral;

import hx.base.core.dao.entity.common.BaseEntity;

/**
 * @ClassName IntegralRankModel
 * @Description 积分排行榜Model
 * @Author HuoJiaJin
 * @Date 2020/6/27 21:25
 * @Version 1.0
 **/
public class IntegralRankModel extends BaseEntity {

    private String name;//姓名
    private int integral;//当前积分
    private int index;//排名
    private String avatar;//头像

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

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
