package hx.base.core.dao.entity.staff;

import hx.base.core.dao.entity.common.AbstractInsertTimeEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;

/**
 * @ClassName TrainPeople
 * @Description 培训场次参会人员表
 * @Author HuoJiaJin
 * @Date 2020/11/8 2:30
 * @Version 1.0
 **/
@Entity
@Table(name = "hx_train_people", indexes = {
        @Index(name = "hx_train_people_index", columnList = "train_id")
})
public class TrainPeople extends AbstractInsertTimeEntity {

    private String trainId;//培训场次ID
    private String agentCode;//营销员编码
    private String name;//营销员姓名
    private String mobile;//营销员手机号
    private String campName;//营服名称
    private String campCode;//营服代码
    private String sectionName;//部名称
    private String sectionCode;//部名称
    private String groupName;//组名称
    private String groupCode;//组名称

    @Column(name = "train_id")
    public String getTrainId() {
        return trainId;
    }

    public void setTrainId(String trainId) {
        this.trainId = trainId;
    }

    @Column(name = "agent_code")
    public String getAgentCode() {
        return agentCode;
    }

    public void setAgentCode(String agentCode) {
        this.agentCode = agentCode;
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "mobile")
    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    @Column(name = "camp_name")
    public String getCampName() {
        return campName;
    }

    public void setCampName(String campName) {
        this.campName = campName;
    }

    @Column(name = "camp_code")
    public String getCampCode() {
        return campCode;
    }

    public void setCampCode(String campCode) {
        this.campCode = campCode;
    }

    @Column(name = "section_name")
    public String getSectionName() {
        return sectionName;
    }

    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }

    @Column(name = "section_code")
    public String getSectionCode() {
        return sectionCode;
    }

    public void setSectionCode(String sectionCode) {
        this.sectionCode = sectionCode;
    }

    @Column(name = "group_name")
    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    @Column(name = "group_code")
    public String getGroupCode() {
        return groupCode;
    }

    public void setGroupCode(String groupCode) {
        this.groupCode = groupCode;
    }
}
