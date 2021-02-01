package hx.service.mobile.model.structure;

import hx.base.core.dao.entity.common.BaseEntity;

import java.util.List;

/**
 * @name: SructureListResponse
 * @description: 获取组员列表Response
 * @author: huojiajin
 * @time: 2020/7/10 0:02
 */
public class SructurePersonListResponse extends BaseEntity {

    private List<SructurePersonListModel> result;

    public List<SructurePersonListModel> getResult() {
        return result;
    }

    public void setResult(List<SructurePersonListModel> result) {
        this.result = result;
    }
}
