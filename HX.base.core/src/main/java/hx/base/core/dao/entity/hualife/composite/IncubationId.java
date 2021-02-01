package hx.base.core.dao.entity.hualife.composite;

import java.io.Serializable;
import java.util.Objects;

/**
 * @name: IncubationId
 * @description: 育成关系表联合主键
 * @author: huojiajin
 * @time: 2020/6/17 17:25
 */
public class IncubationId implements Serializable {

    private static final long serialVersionUID = -5126672321508545718L;
    private String agentCode;
    private String rearAgentCode;

    public String getAgentCode() {
        return agentCode;
    }

    public void setAgentCode(String agentCode) {
        this.agentCode = agentCode;
    }

    public String getRearAgentCode() {
        return rearAgentCode;
    }

    public void setRearAgentCode(String rearAgentCode) {
        this.rearAgentCode = rearAgentCode;
    }

    @Override
    public int hashCode() {
        return Objects.hash(agentCode, rearAgentCode);
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof IncubationId){
            IncubationId key = (IncubationId)obj ;
            if(this.agentCode.equals(key.getAgentCode()) && this.rearAgentCode.equals(key.getRearAgentCode())){
                return true ;
            }
        }
        return false ;
    }
}
