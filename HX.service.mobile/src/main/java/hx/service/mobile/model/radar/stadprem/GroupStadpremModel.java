package hx.service.mobile.model.radar.stadprem;

import hx.base.core.dao.entity.common.BaseEntity;

/**
 * @ClassName GroupStadpremModel
 * @Description 组标保详情Model
 * @Author HuoJiaJin
 * @Date 2020/6/27 6:19
 * @Version 1.0
 **/
public class GroupStadpremModel extends BaseEntity {

    private String name;//组名称
    private String stadprem;//标保数额
    private String groupCode;//组代码

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStadprem() {
        return stadprem;
    }

    public void setStadprem(String stadprem) {
        this.stadprem = stadprem;
    }

    public String getGroupCode() {
        return groupCode;
    }

    public void setGroupCode(String groupCode) {
        this.groupCode = groupCode;
    }
}
