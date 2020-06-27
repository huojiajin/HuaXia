package hx.service.mobile.manage.model.radar;

import hx.base.core.dao.entity.common.BaseEntity;

import java.util.List;

/**
 * @ClassName SectionListResponse
 * @Description 部列表Response
 * @Author HuoJiaJin
 * @Date 2020/6/27 0:55
 * @Version 1.0
 **/
public class SectionListResponse extends BaseEntity {

    private List<SectionModel> sectionList;//部集合

    public List<SectionModel> getSectionList() {
        return sectionList;
    }

    public void setSectionList(List<SectionModel> sectionList) {
        this.sectionList = sectionList;
    }
}
