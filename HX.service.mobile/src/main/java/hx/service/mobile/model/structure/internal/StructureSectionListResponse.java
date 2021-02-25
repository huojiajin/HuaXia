package hx.service.mobile.model.structure.internal;

import hx.base.core.dao.entity.common.BaseEntity;

import java.util.List;

/**
 * @name: StructureSectionListResponse
 * @description: 组织架构-获取相关部列表Response
 * @author: huojiajin
 * @time: 2021/2/25 11:52
 */
public class StructureSectionListResponse extends BaseEntity {

    private List<StructureSectionModel> sectionList;//部列表

    public List<StructureSectionModel> getSectionList() {
        return sectionList;
    }

    public void setSectionList(List<StructureSectionModel> sectionList) {
        this.sectionList = sectionList;
    }
}
