package hx.service.mobile.model.person.team;

import hx.base.core.dao.entity.common.BaseEntity;

/**
 * @ClassName: TeamInfoQueryMonthModel
 * @Description: 团队信息查询结果月份Model
 * @Author HuoJiaJin
 * @Date 2021/2/24 23:37
 * @Version 1.0
 **/
public class TeamInfoQueryMonthModel extends BaseEntity {

    private int month;//月份
    private double data;//数据

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public double getData() {
        return data;
    }

    public void setData(double data) {
        this.data = data;
    }
}
