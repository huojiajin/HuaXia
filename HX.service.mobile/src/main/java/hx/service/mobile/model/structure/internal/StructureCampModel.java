package hx.service.mobile.model.structure.internal;

import hx.base.core.dao.entity.common.BaseEntity;

/**
 * @name: StructureCampModel
 * @description: 组织架构-获取相关营服列表Model
 * @author: huojiajin
 * @time: 2021/2/25 11:52
 */
public class StructureCampModel extends BaseEntity {

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
