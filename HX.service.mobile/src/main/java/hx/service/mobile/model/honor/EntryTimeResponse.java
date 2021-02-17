package hx.service.mobile.model.honor;

import hx.base.core.dao.entity.common.BaseEntity;

/**
 * @name: EntryTimeResponse
 * @description: 入职时间Response
 * @author: huojiajin
 * @time: 2020/7/7 14:58
 */
public class EntryTimeResponse extends BaseEntity {

    private Integer year;//年
    private Integer month;//月
    private Integer day;//日
    private Integer hour;//小时

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public Integer getDay() {
        return day;
    }

    public void setDay(Integer day) {
        this.day = day;
    }

    public Integer getHour() {
        return hour;
    }

    public void setHour(Integer hour) {
        this.hour = hour;
    }
}
