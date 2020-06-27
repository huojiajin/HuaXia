package hx.service.mobile.manage.model.radar;

import hx.service.manage.dao.entity.common.BaseEntity;

import java.util.List;

/**
 * @ClassName GroupListResponse
 * @Description 获取组列表Response
 * @Author HuoJiaJin
 * @Date 2020/6/27 1:27
 * @Version 1.0
 **/
public class GroupListResponse extends BaseEntity {

    private List<GroupModel> groupList;//组集合

    public List<GroupModel> getGroupList() {
        return groupList;
    }

    public void setGroupList(List<GroupModel> groupList) {
        this.groupList = groupList;
    }
}
