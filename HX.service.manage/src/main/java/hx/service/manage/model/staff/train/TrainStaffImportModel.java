package hx.service.manage.model.staff.train;

import hx.base.core.dao.entity.common.BaseEntity;

/**
 * @name: TrainStaffImportModel
 * @description: 参训人员管理人员导入结果Model
 * @author: huojiajin
 * @time: 2020/11/12 15:03
 */
public class TrainStaffImportModel extends BaseEntity {

    private String agentCode;//营销员编码
    private String name;//姓名
    private String mobile;//手机号

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

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}
