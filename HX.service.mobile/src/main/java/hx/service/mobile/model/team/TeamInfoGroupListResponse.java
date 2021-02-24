package hx.service.mobile.model.team;

import hx.base.core.dao.entity.common.BaseEntity;

import java.util.List;

/**
 * @ClassName: TeamInfoGroupListResponse
 * @Description: 团队信息获取相关组列表Response
 * @Author HuoJiaJin
 * @Date 2021/2/25 0:45
 * @Version 1.0
 **/
public class TeamInfoGroupListResponse extends BaseEntity {

    private List<TeamInfoGroupModel> groupList;//组集合

    public List<TeamInfoGroupModel> getGroupList() {
        return groupList;
    }

    public void setGroupList(List<TeamInfoGroupModel> groupList) {
        this.groupList = groupList;
    }
}
