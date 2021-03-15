package hx.base.core.dao.entity.quit;

import hx.base.core.dao.dict.quit.QuitAssignType;
import hx.base.core.dao.entity.common.AbstractInsertTimeEntity;

import javax.persistence.*;

/**
 * @name: QuitAssign
 * @description: 离职指派实体
 * @author: huojiajin
 * @time: 2021/2/1 16:36
 */
@Entity
@Table(name = "hx_quit_assign")
public class QuitAssign extends AbstractInsertTimeEntity {

    private String agentCode;//内勤人员编码
    private String name;//人员名称
    private String campCode;//营服代码
    private String campName;//营服名称
    private QuitAssignType type;//指派人员类型

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

    @Column(name = "camp_code")
    public String getCampCode() {
        return campCode;
    }

    public void setCampCode(String campCode) {
        this.campCode = campCode;
    }

    @Column(name = "camp_name")
    public String getCampName() {
        return campName;
    }

    public void setCampName(String campName) {
        this.campName = campName;
    }

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    public QuitAssignType getType() {
        return type;
    }

    public void setType(QuitAssignType type) {
        this.type = type;
    }
}
