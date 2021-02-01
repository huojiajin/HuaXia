package hx.service.mobile.model.structure;

import hx.base.core.dao.entity.common.BaseEntity;

/**
 * @name: StructurePersonDetailResponse
 * @description: 获取人员详情Response
 * @author: huojiajin
 * @time: 2020/7/11 0:36
 */
public class StructurePersonDetailResponse extends BaseEntity {

    private String agentCode;//员工代码
    private String name;//员工姓名
    private String startDate;//起聘日期
    private String employeeDate;//入职日期
    private Integer attendNum;//出勤天数
    private Integer star;//凤凰社星级
    private Double stadprem;//当月承保标保

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

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEmployeeDate() {
        return employeeDate;
    }

    public void setEmployeeDate(String employeeDate) {
        this.employeeDate = employeeDate;
    }

    public Integer getAttendNum() {
        return attendNum;
    }

    public void setAttendNum(Integer attendNum) {
        this.attendNum = attendNum;
    }

    public Integer getStar() {
        return star;
    }

    public void setStar(Integer star) {
        this.star = star;
    }

    public Double getStadprem() {
        return stadprem;
    }

    public void setStadprem(Double stadprem) {
        this.stadprem = stadprem;
    }
}
