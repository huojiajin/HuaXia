package hx.service.mobile.manage.model.radar;

import hx.base.core.dao.entity.common.BaseEntity;

/**
 * @ClassName SectionStadpremModel
 * @Description 部标保详情Model
 * @Author HuoJiaJin
 * @Date 2020/6/27 5:58
 * @Version 1.0
 **/
public class SectionStadpremModel extends BaseEntity {

    private int month;//月份
    private String stadprem;//标保数额

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public String getStadprem() {
        return stadprem;
    }

    public void setStadprem(String stadprem) {
        this.stadprem = stadprem;
    }
}
