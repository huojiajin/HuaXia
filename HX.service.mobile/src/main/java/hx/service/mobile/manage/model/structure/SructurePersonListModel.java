package hx.service.mobile.manage.model.structure;

import hx.base.core.dao.entity.common.BaseEntity;

/**
 * @name: SructureListModel
 * @description: 获取组员列表Model
 * @author: huojiajin
 * @time: 2020/7/10 9:54
 */
public class SructurePersonListModel extends BaseEntity {

    private String name;//员工名称
    private String agentCode;//员工编号
    private String gradeName;//制剂名称
    private boolean mdrt;//是否MDRT
    private String recommName;//推荐人名称
    private String employeeDate;//入职日期

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

    public String getGradeName() {
        return gradeName;
    }

    public void setGradeName(String gradeName) {
        this.gradeName = gradeName;
    }

    public boolean isMdrt() {
        return mdrt;
    }

    public void setMdrt(boolean mdrt) {
        this.mdrt = mdrt;
    }

    public String getRecommName() {
        return recommName;
    }

    public void setRecommName(String recommName) {
        this.recommName = recommName;
    }

    public String getEmployeeDate() {
        return employeeDate;
    }

    public void setEmployeeDate(String employeeDate) {
        this.employeeDate = employeeDate;
    }
}
