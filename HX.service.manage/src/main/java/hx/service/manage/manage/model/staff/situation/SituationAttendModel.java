package hx.service.manage.manage.model.staff.situation;

import hx.base.core.dao.entity.common.BaseEntity;

/**
 * @ClassName: SituationAttendModel
 * @Description: 人员情况统计查询返回用出勤Model
 * @Author huojiajin
 * @Date 2021/1/29 1:07
 * @Version 1.0
 **/
public class SituationAttendModel extends BaseEntity {

    private int month;//月份
    private String attendNum;//出勤天数

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public String getAttendNum() {
        return attendNum;
    }

    public void setAttendNum(String attendNum) {
        this.attendNum = attendNum;
    }
}
