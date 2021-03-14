package hx.service.mobile.model.back.org;

import hx.base.core.dao.entity.common.BaseEntity;

import java.util.List;

/**
 * @name: RadarSectionListResponse
 * @description: 雷达图-获取相关部列表Response
 * @author: huojiajin
 * @time: 2021/2/25 11:52
 */
public class BackSectionListResponse extends BaseEntity {

    private List<BackSectionModel> sectionList;//部列表

    public List<BackSectionModel> getSectionList() {
        return sectionList;
    }

    public void setSectionList(List<BackSectionModel> sectionList) {
        this.sectionList = sectionList;
    }
}
