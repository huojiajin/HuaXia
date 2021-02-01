package hx.service.manage.manage.model.honor;

import hx.base.core.dao.entity.common.BaseEntity;

/**
 * @name: HonorStaffImportModel
 * @description: 荣誉人员导入结果Model
 * @author: huojiajin
 * @time: 2020/11/12 15:03
 */
public class HonorStaffImportModel extends BaseEntity {

    private String agentCode;//营销员编码
    private String name;//姓名

    public String getAgentCode() {
        return agentCode;
    }

    public void setAgentCode(String agentCode) {
        this.agentCode = agentCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
