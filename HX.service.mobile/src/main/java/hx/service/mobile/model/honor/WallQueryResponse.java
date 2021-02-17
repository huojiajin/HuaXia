package hx.service.mobile.model.honor;

import hx.base.core.dao.entity.common.BaseEntity;

import java.util.List;

/**
 * @ClassName: WallQueryResponse
 * @Description: 荣誉墙查询Response
 * @Author HuoJiaJin
 * @Date 2021/2/6 3:22
 * @Version 1.0
 **/
public class WallQueryResponse extends BaseEntity {

    private List<WallQueryModel> result;

    public List<WallQueryModel> getResult() {
        return result;
    }

    public void setResult(List<WallQueryModel> result) {
        this.result = result;
    }
}
