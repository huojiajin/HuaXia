package hx.service.mobile.manage.model.radar.attend;

import hx.base.core.dao.entity.common.BaseEntity;

import java.util.List;

/**
 * @ClassName GroupAttendResponse
 * @Description 组出勤人力Response
 * @Author HuoJiaJin
 * @Date 2020/6/27 17:37
 * @Version 1.0
 **/
public class GroupAttendResponse extends BaseEntity {

    private List<GroupAttendModel> result;

    public List<GroupAttendModel> getResult() {
        return result;
    }

    public void setResult(List<GroupAttendModel> result) {
        this.result = result;
    }
}
