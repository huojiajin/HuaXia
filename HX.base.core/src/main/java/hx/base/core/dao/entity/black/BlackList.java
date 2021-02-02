package hx.base.core.dao.entity.black;

import hx.base.core.dao.dict.black.BlackListType;
import hx.base.core.dao.entity.common.AbstractInsertTimeEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @ClassName: BlackList
 * @Description: 黑名单实体
 * @Author HuoJiaJin
 * @Date 2021/2/3 0:32
 * @Version 1.0
 **/
@Entity
@Table(name = "hx_black_list")
public class BlackList extends AbstractInsertTimeEntity {

    private String name;//姓名
    private String agentCode;//编码
    private BlackListType type;//黑名单类型
    private String sectionName;//部名称
    private String sectionCode;//部代码
    private String groupName;//组名称
    private String groupCode;//组代码

    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "agent_code")
    public String getAgentCode() {
        return agentCode;
    }

    public void setAgentCode(String agentCode) {
        this.agentCode = agentCode;
    }

    @Column(name = "type")
    public BlackListType getType() {
        return type;
    }

    public void setType(BlackListType type) {
        this.type = type;
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
