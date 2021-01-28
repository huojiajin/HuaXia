package hx.service.manage.manage.model.staff.situation;

import hx.base.core.dao.entity.common.BaseEntity;

/**
 * @ClassName: SituationStadpremModel
 * @Description: 人员情况统计查询返回用业绩Model
 * @Author huojiajin
 * @Date 2021/1/29 1:05
 * @Version 1.0
 **/
public class SituationStadpremModel extends BaseEntity {

    private int month;//月份
    private String stadprem;//承保标保数额

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
