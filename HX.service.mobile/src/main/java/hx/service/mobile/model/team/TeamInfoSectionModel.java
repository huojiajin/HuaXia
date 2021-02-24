package hx.service.mobile.model.team;

import hx.base.core.dao.entity.common.BaseEntity;

/**
 * @ClassName: TeamInfoSectionModel
 * @Description: 团队信息-相关部列表Model
 * @Author HuoJiaJin
 * @Date 2021/2/25 0:29
 * @Version 1.0
 **/
public class TeamInfoSectionModel extends BaseEntity {

    private String sectionCode;//部代码
    private String sectionName;//部名称

    public String getSectionCode() {
        return sectionCode;
    }

    public void setSectionCode(String sectionCode) {
        this.sectionCode = sectionCode;
    }

    public String getSectionName() {
        return sectionName;
    }

    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }
}
