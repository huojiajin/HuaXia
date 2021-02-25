package hx.service.mobile.model.structure.internal;

import hx.base.core.dao.entity.common.BaseEntity;

import java.util.List;

/**
 * @name: StructureDirectorListResponse
 * @description: 组织架构-获取相关总监区列表Response
 * @author: huojiajin
 * @time: 2021/2/25 11:52
 */
public class StructureDirectorListResponse extends BaseEntity {

    private List<StructureDirectorModel> directorList;//总监区列表

    public List<StructureDirectorModel> getDirectorList() {
        return directorList;
    }

    public void setDirectorList(List<StructureDirectorModel> directorList) {
        this.directorList = directorList;
    }
}
