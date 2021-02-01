package hx.service.mobile.model.index;

import hx.base.core.dao.entity.common.BaseEntity;

/**
 * @ClassName GroupModel
 * @Description 组Model
 * @Author HuoJiaJin
 * @Date 2020/6/27 1:27
 * @Version 1.0
 **/
public class GroupModel extends BaseEntity {

    private String groupCode;//组代码
    private String groupName;//组名称

    public GroupModel(String groupCode, String groupName) {
        this.groupCode = groupCode;
        this.groupName = groupName;
    }

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
