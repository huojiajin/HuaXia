package hx.service.mobile.manage.model.radar.attend;

import hx.base.core.dao.entity.common.BaseEntity;

/**
 * @ClassName GroupAttendModel
 * @Description 组出勤人力Model
 * @Author HuoJiaJin
 * @Date 2020/6/27 17:35
 * @Version 1.0
 **/
public class GroupAttendModel extends BaseEntity {

    private String name;//组名称
    private String groupCode;//组代码
    private int attendNum;//出勤人力
    private int freAttendNum;//常勤人力

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGroupCode() {
        return groupCode;
    }

    public void setGroupCode(String groupCode) {
        this.groupCode = groupCode;
    }

    public int getAttendNum() {
        return attendNum;
    }

    public void setAttendNum(int attendNum) {
        this.attendNum = attendNum;
    }

    public int getFreAttendNum() {
        return freAttendNum;
    }

    public void setFreAttendNum(int freAttendNum) {
        this.freAttendNum = freAttendNum;
    }
}
