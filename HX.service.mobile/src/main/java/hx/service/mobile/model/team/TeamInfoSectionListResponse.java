package hx.service.mobile.model.team;

import hx.base.core.dao.entity.common.BaseEntity;

import java.util.List;

/**
 * @ClassName: TeamInfoSectionListResponse
 * @Description: 团队信息获取相关部集合
 * @Author HuoJiaJin
 * @Date 2021/2/25 0:28
 * @Version 1.0
 **/
public class TeamInfoSectionListResponse extends BaseEntity {

    private List<TeamInfoSectionModel> sectionList;//部集合

    public List<TeamInfoSectionModel> getSectionList() {
        return sectionList;
    }

    public void setSectionList(List<TeamInfoSectionModel> sectionList) {
        this.sectionList = sectionList;
    }
}
