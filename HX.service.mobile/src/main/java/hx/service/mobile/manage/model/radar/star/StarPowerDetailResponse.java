package hx.service.mobile.manage.model.radar.star;

import hx.base.core.dao.entity.common.BaseEntity;

import java.util.List;

/**
 * @ClassName StarPowerDetailResponse
 * @Description 星级人力详情Response
 * @Author HuoJiaJin
 * @Date 2020/6/27 16:06
 * @Version 1.0
 **/
public class StarPowerDetailResponse extends BaseEntity {

    private List<StarPowerDetailModel> result;

    public List<StarPowerDetailModel> getResult() {
        return result;
    }

    public void setResult(List<StarPowerDetailModel> result) {
        this.result = result;
    }
}
