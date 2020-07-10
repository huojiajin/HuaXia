package hx.service.mobile.manage.model.structure;

import hx.base.core.dao.entity.common.BaseEntity;

/**
 * @name: StructureOrgListModel
 * @description: 获取组织列表Model
 * @author: huojiajin
 * @time: 2020/7/7 1:19
 */
public class StructureOrgListModel extends BaseEntity {

    private String orgCode;//组织代码
    private String orgName;//组织名称
    private Integer orgType;//组织类型

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public Integer getOrgType() {
        return orgType;
    }

    public void setOrgType(Integer orgType) {
        this.orgType = orgType;
    }
}
