package hx.service.mobile.model.structure;

import hx.base.core.dao.entity.common.BaseEntity;

/**
 * @name: SructureAnalysisEmployeeModel
 * @description: 组织架构分析-员工Model
 * @author: huojiajin
 * @time: 2020/7/7 1:34
 */
public class StructureAnalysisEmployeeModel extends BaseEntity {

    private String name;//员工名称
    private String agentCode;//员工编号
    private String groupCode;//组代码
    private Integer personNum;//组员数量

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAgentCode() {
        return agentCode;
    }

    public void setAgentCode(String agentCode) {
        this.agentCode = agentCode;
    }

    public String getGroupCode() {
        return groupCode;
    }

    public void setGroupCode(String groupCode) {
        this.groupCode = groupCode;
    }

    public Integer getPersonNum() {
        return personNum;
    }

    public void setPersonNum(Integer personNum) {
        this.personNum = personNum;
    }
}
