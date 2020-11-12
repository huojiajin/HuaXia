package hx.service.manage.manage.model.test.papers;

import hx.base.core.dao.entity.common.BaseEntity;

/**
 * @name: CampModel
 * @description: 营服Model
 * @author: huojiajin
 * @time: 2020/11/12 16:20
 */
public class CampModel extends BaseEntity {

    private String campName;//营服名称
    private String campCode;//营服代码

    public String getCampName() {
        return campName;
    }

    public void setCampName(String campName) {
        this.campName = campName;
    }

    public String getCampCode() {
        return campCode;
    }

    public void setCampCode(String campCode) {
        this.campCode = campCode;
    }
}
