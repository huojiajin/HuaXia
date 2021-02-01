package hx.service.mobile.model.radar.stadprem;

import hx.base.core.dao.entity.common.BaseEntity;

import java.util.List;

/**
 * @ClassName SectionStadpremResponse
 * @Description 部标保详情Response
 * @Author HuoJiaJin
 * @Date 2020/6/27 5:57
 * @Version 1.0
 **/
public class SectionStadpremResponse extends BaseEntity {

    private List<SectionStadpremModel> result;
    private int type;//类别

    public List<SectionStadpremModel> getResult() {
        return result;
    }

    public void setResult(List<SectionStadpremModel> result) {
        this.result = result;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
