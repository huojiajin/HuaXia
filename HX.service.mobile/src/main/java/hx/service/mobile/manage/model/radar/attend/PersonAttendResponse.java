package hx.service.mobile.manage.model.radar.attend;

import hx.base.core.dao.entity.common.BaseEntity;

import java.util.List;

/**
 * @ClassName PersonAttendResponse
 * @Description 个人出勤人力Response
 * @Author HuoJiaJin
 * @Date 2020/6/27 17:55
 * @Version 1.0
 **/
public class PersonAttendResponse extends BaseEntity {

    private List<PersonAttendModel> result;

    public List<PersonAttendModel> getResult() {
        return result;
    }

    public void setResult(List<PersonAttendModel> result) {
        this.result = result;
    }
}
