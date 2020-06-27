package hx.service.mobile.manage.model.radar.attend;

import hx.base.core.dao.entity.common.BaseEntity;

import java.util.List;

/**
 * @ClassName SectionAttendResponse
 * @Description 部出勤人力Response
 * @Author HuoJiaJin
 * @Date 2020/6/27 16:51
 * @Version 1.0
 **/
public class SectionAttendResponse extends BaseEntity {

    private List<SectionAttendModel> result;
    private int type;//类别

    public List<SectionAttendModel> getResult() {
        return result;
    }

    public void setResult(List<SectionAttendModel> result) {
        this.result = result;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
