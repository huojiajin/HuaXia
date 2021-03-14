package hx.service.mobile.model.back.org;

import hx.base.core.dao.entity.common.BaseEntity;

/**
 * @name: RadarCampModel
 * @description: 雷达图-获取相关营服列表Model
 * @author: huojiajin
 * @time: 2021/2/25 11:52
 */
public class BackCampModel extends BaseEntity {

    private String campCode;//营业区代码
    private String campName;//营业区名称

    public String getCampCode() {
        return campCode;
    }

    public void setCampCode(String campCode) {
        this.campCode = campCode;
    }

    public String getCampName() {
        return campName;
    }

    public void setCampName(String campName) {
        this.campName = campName;
    }
}
