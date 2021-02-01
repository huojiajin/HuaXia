package hx.service.manage.model.staff.situation;

import hx.base.core.dao.entity.common.BaseEntity;

import java.util.List;

/**
 * @ClassName: SituationQueryResponse
 * @Description: 人员情况统计查询返回Response
 * @Author huojiajin
 * @Date 2021/1/29 1:02
 * @Version 1.0
 **/
public class SituationQueryResponse extends BaseEntity {

    private String employeeNum;//工号
    private String name;//人员名称
    private String campName;//营服名称
    private String chiefName;//总监名称
    private String sectionName;//部名称
    private String groupName;//组名称
    private String star;//星级
    private String gradeName;//职级名称
    private String entryTime;//入职时间
    private List<SituationStadpremModel> stadpremList;//业绩
    private List<SituationAttendModel> attendList;//出勤

    public String getEmployeeNum() {
        return employeeNum;
    }

    public void setEmployeeNum(String employeeNum) {
        this.employeeNum = employeeNum;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCampName() {
        return campName;
    }

    public void setCampName(String campName) {
        this.campName = campName;
    }

    public String getChiefName() {
        return chiefName;
    }

    public void setChiefName(String chiefName) {
        this.chiefName = chiefName;
    }

    public String getSectionName() {
        return sectionName;
    }

    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getStar() {
        return star;
    }

    public void setStar(String star) {
        this.star = star;
    }

    public String getGradeName() {
        return gradeName;
    }

    public void setGradeName(String gradeName) {
        this.gradeName = gradeName;
    }

    public String getEntryTime() {
        return entryTime;
    }

    public void setEntryTime(String entryTime) {
        this.entryTime = entryTime;
    }

    public List<SituationStadpremModel> getStadpremList() {
        return stadpremList;
    }

    public void setStadpremList(List<SituationStadpremModel> stadpremList) {
        this.stadpremList = stadpremList;
    }

    public List<SituationAttendModel> getAttendList() {
        return attendList;
    }

    public void setAttendList(List<SituationAttendModel> attendList) {
        this.attendList = attendList;
    }
}
