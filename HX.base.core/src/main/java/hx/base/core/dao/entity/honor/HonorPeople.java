package hx.base.core.dao.entity.honor;

import hx.base.core.dao.entity.common.AbstractInsertTimeEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;

/**
 * @ClassName: HonorPeople
 * @Description: 荣誉获得人员实体
 * @Author HuoJiaJin
 * @Date 2021/2/1 1:12
 * @Version 1.0
 **/
@Entity
@Table(name = "hx_honor_people", indexes = {
        @Index(name = "hx_honor_people_index", columnList = "agent_code"),
        @Index(name = "hx_honor_people_index1", columnList = "honor_id"),
})
public class HonorPeople extends AbstractInsertTimeEntity {

    private String honorId;//荣誉ID
    private String agentCode;//营销员编码
    private String name;//姓名
    private String campName;//营服名称
    private String campCode;//营服代码
    private String sectionName;//部名称
    private String sectionCode;//部名称
    private String groupName;//组名称
    private String groupCode;//组名称

    @Column(name = "honor_id")
    public String getHonorId() {
        return honorId;
    }

    public void setHonorId(String honorId) {
        this.honorId = honorId;
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
