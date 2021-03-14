package hx.service.mobile.model.back.org;

import hx.base.core.dao.entity.common.BaseEntity;

import java.util.List;

/**
 * @name: RadarGroupListResponse
 * @description: 雷达图-获取相关组列表Response
 * @author: huojiajin
 * @time: 2021/2/25 11:52
 */
public class BackGroupListResponse extends BaseEntity {

    private List<BackGroupModel> groupList;//组列表

    public List<BackGroupModel> getGroupList() {
        return groupList;
    }

    public void setGroupList(List<BackGroupModel> groupList) {
        this.groupList = groupList;
    }
}
