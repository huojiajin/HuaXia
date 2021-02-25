package hx.service.mobile.model.structure.internal;

import hx.base.core.dao.entity.common.BaseEntity;

import java.util.List;

/**
 * @name: StructureGroupListResponse
 * @description: 组织架构-获取相关组列表Response
 * @author: huojiajin
 * @time: 2021/2/25 11:52
 */
public class StructureGroupListResponse extends BaseEntity {

    private List<StructureGroupModel> groupList;//组列表

    public List<StructureGroupModel> getGroupList() {
        return groupList;
    }

    public void setGroupList(List<StructureGroupModel> groupList) {
        this.groupList = groupList;
    }
}
