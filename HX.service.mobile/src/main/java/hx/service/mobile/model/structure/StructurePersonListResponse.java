package hx.service.mobile.model.structure;

import hx.base.core.dao.entity.common.BaseEntity;

import java.util.List;

/**
 * @name: SructureListResponse
 * @description: 获取组员列表Response
 * @author: huojiajin
 * @time: 2020/7/10 0:02
 */
public class StructurePersonListResponse extends BaseEntity {

    private List<StructurePersonListModel> result;

    public List<StructurePersonListModel> getResult() {
        return result;
    }

    public void setResult(List<StructurePersonListModel> result) {
        this.result = result;
    }
}
