package hx.service.mobile.manage.model.radar.attend;

import hx.base.core.dao.entity.common.BaseEntity;

/**
 * @ClassName PersonAttendModel
 * @Description 个人出勤人力Model
 * @Author HuoJiaJin
 * @Date 2020/6/27 17:55
 * @Version 1.0
 **/
public class PersonAttendModel extends BaseEntity {

    private String name;//员工名称
    private String entryTime;//入职时间
    private int attendDay;//出勤天数
    private boolean freAttend;//是否常勤人力

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEntryTime() {
        return entryTime;
    }

    public void setEntryTime(String entryTime) {
        this.entryTime = entryTime;
    }

    public int getAttendDay() {
        return attendDay;
    }

    public void setAttendDay(int attendDay) {
        this.attendDay = attendDay;
    }

    public boolean isFreAttend() {
        return freAttend;
    }

    public void setFreAttend(boolean freAttend) {
        this.freAttend = freAttend;
    }
}
