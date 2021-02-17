package hx.service.mobile.model.honor;

import hx.base.core.dao.entity.common.BaseEntity;

import java.util.List;

/**
 * @ClassName: RankPromontionTrackResponse
 * @Description: 职级晋升轨迹返回Response
 * @Author HuoJiaJin
 * @Date 2021/2/6 3:14
 * @Version 1.0
 **/
public class RankPromontionTrackResponse extends BaseEntity {

    private List<RankPromontionTrackModel> result;//结果集合

    public List<RankPromontionTrackModel> getResult() {
        return result;
    }

    public void setResult(List<RankPromontionTrackModel> result) {
        this.result = result;
    }
}
