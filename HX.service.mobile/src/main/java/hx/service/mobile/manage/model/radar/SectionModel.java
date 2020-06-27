package hx.service.mobile.manage.model.radar;

import hx.base.core.dao.entity.common.BaseEntity;

/**
 * @ClassName SectionModel
 * @Description 部Model
 * @Author HuoJiaJin
 * @Date 2020/6/27 0:56
 * @Version 1.0
 **/
public class SectionModel extends BaseEntity {

    private String sectionCode;//部代码
    private String sectionName;//部名称

    public SectionModel(String sectionCode, String sectionName) {
        this.sectionCode = sectionCode;
        this.sectionName = sectionName;
    }

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
