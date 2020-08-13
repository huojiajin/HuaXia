package hx.service.mobile.manage.model.radar.attend;

import hx.base.core.dao.entity.common.BaseEntity;

/**
 * @ClassName SectionAttendModel
 * @Description 部出勤人力Model
 * @Author HuoJiaJin
 * @Date 2020/6/27 16:51
 * @Version 1.0
 **/
public class SectionAttendModel extends BaseEntity {

    private int month;//月份
    private Integer attendNum;//出勤人力

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public Integer getAttendNum() {
        return attendNum;
    }

    public void setAttendNum(Integer attendNum) {
        this.attendNum = attendNum;
    }
}
