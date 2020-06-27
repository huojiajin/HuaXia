package hx.service.mobile.manage.model.radar;

import hx.base.core.dao.entity.common.BaseEntity;

import java.util.List;

/**
 * @ClassName GroupStadpremResponse
 * @Description 组标保详情Response
 * @Author HuoJiaJin
 * @Date 2020/6/27 6:18
 * @Version 1.0
 **/
public class GroupStadpremResponse extends BaseEntity {

    private List<GroupStadpremModel> result;

    public List<GroupStadpremModel> getResult() {
        return result;
    }

    public void setResult(List<GroupStadpremModel> result) {
        this.result = result;
    }
}
