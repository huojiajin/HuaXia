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
}
