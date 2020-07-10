package hx.service.mobile.manage.model.structure;

import hx.base.core.dao.entity.common.BaseEntity;

import java.util.List;

/**
 * @name: SructureAnalysisResponse
 * @description: 组织架构分析Response
 * @author: huojiajin
 * @time: 2020/7/7 1:33
 */
public class SructureAnalysisResponse extends BaseEntity {

    private List<SructureAnalysisEmployeeModel> employeeList;//人员集合
    private String type;//类型
    private List<String> situation;//现状
    private List<String> advantage;//优势
    private List<String> inferiority;//劣势
    private List<String> advice;//建议

    public List<SructureAnalysisEmployeeModel> getEmployeeList() {
        return employeeList;
    }

    public void setEmployeeList(List<SructureAnalysisEmployeeModel> employeeList) {
        this.employeeList = employeeList;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<String> getSituation() {
        return situation;
    }

    public void setSituation(List<String> situation) {
        this.situation = situation;
    }

    public List<String> getAdvantage() {
        return advantage;
    }

    public void setAdvantage(List<String> advantage) {
        this.advantage = advantage;
    }

    public List<String> getInferiority() {
        return inferiority;
    }

    public void setInferiority(List<String> inferiority) {
        this.inferiority = inferiority;
    }

    public List<String> getAdvice() {
        return advice;
    }

    public void setAdvice(List<String> advice) {
        this.advice = advice;
    }
}
