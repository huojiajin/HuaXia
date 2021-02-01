package hx.service.mobile.model.structure;

import hx.base.core.dao.entity.common.BaseEntity;

import java.util.List;

/**
 * @name: StructureOrgListResponse
 * @description: 获取组织列表Response
 * @author: huojiajin
 * @time: 2020/7/7 1:19
 */
public class StructureOrgListResponse extends BaseEntity {

    private List<StructureOrgListModel> orgList;//组织集合

    public List<StructureOrgListModel> getOrgList() {
        return orgList;
    }

    public void setOrgList(List<StructureOrgListModel> orgList) {
        this.orgList = orgList;
    }
}
