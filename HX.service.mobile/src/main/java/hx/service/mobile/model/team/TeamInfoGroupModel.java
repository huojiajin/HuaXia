package hx.service.mobile.model.team;

import hx.base.core.dao.entity.common.BaseEntity;

/**
 * @ClassName: TeamInfoGroupModel
 * @Description: 团队信息-相关组列表Model
 * @Author HuoJiaJin
 * @Date 2021/2/25 0:46
 * @Version 1.0
 **/
public class TeamInfoGroupModel extends BaseEntity {

    private String groupCode;//组代码
    private String groupName;//组名称

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
