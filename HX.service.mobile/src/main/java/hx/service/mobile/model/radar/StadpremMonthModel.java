package hx.service.mobile.model.radar;

import hx.base.core.dao.entity.common.BaseEntity;

/**
 * @ClassName StadpremMonthModel
 * @Description 标保月份通用Model
 * @Author HuoJiaJin
 * @Date 2020/6/27 16:08
 * @Version 1.0
 **/
public class StadpremMonthModel extends BaseEntity {

    private int Month;
    private String stadprem;

    public int getMonth() {
        return Month;
    }

    public void setMonth(int month) {
        Month = month;
    }

    public String getStadprem() {
        return stadprem;
    }

    public void setStadprem(String stadprem) {
        this.stadprem = stadprem;
    }
}
