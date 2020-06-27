package hx.service.mobile.manage.model.radar.stadprem;

import hx.base.core.dao.entity.common.BaseEntity;

import java.util.List;

/**
 * @ClassName PersonStadpremDetailResponse
 * @Description 个人标保详情Response
 * @Author HuoJiaJin
 * @Date 2020/6/27 9:54
 * @Version 1.0
 **/
public class PersonStadpremDetailResponse extends BaseEntity {

    private List<PersonStadpremDetailModel> result;

    public List<PersonStadpremDetailModel> getResult() {
        return result;
    }

    public void setResult(List<PersonStadpremDetailModel> result) {
        this.result = result;
    }
}
