package hx.service.mobile.model.structure.internal;

import hx.base.core.dao.entity.common.BaseEntity;

/**
 * @name: StructureGroupModel
 * @description: 组织架构-获取相关组列表Model
 * @author: huojiajin
 * @time: 2021/2/25 11:52
 */
public class StructureGroupModel extends BaseEntity {

    private String groupCode;//部代码
    private String groupName;//部名称

    public String getGroupCode() {
        return groupCode;
    }

    public void setGroupCode(String groupCode) {
        this.groupCode = groupCode;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }
}
