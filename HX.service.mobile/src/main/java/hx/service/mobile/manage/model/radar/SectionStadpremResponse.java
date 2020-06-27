package hx.service.mobile.manage.model.radar;

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
    private String type;//类别

    public List<SectionStadpremModel> getResult() {
        return result;
    }

    public void setResult(List<SectionStadpremModel> result) {
        this.result = result;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
