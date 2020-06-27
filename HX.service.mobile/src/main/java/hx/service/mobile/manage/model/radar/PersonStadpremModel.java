package hx.service.mobile.manage.model.radar;

import hx.base.core.dao.entity.common.BaseEntity;

/**
 * @ClassName PersonStadpremModel
 * @Description 个人标保返回Model
 * @Author HuoJiaJin
 * @Date 2020/6/27 9:35
 * @Version 1.0
 **/
public class PersonStadpremModel extends BaseEntity {

    private String name;//员工姓名
    private String agentCode;//员工编码
    private String preStadprem;//预收标保数额
    private String stadprem;//承保标保数额
    private int type;//实动

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

    public String getPreStadprem() {
        return preStadprem;
    }

    public void setPreStadprem(String preStadprem) {
        this.preStadprem = preStadprem;
    }

    public String getStadprem() {
        return stadprem;
    }

    public void setStadprem(String stadprem) {
        this.stadprem = stadprem;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
