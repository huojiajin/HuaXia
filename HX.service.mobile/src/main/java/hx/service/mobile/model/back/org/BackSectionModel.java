package hx.service.mobile.model.back.org;

import hx.base.core.dao.entity.common.BaseEntity;

/**
 * @name: RadarSectionModel
 * @description: 雷达图-获取相关部列表Model
 * @author: huojiajin
 * @time: 2021/2/25 11:52
 */
public class BackSectionModel extends BaseEntity {

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
