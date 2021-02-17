package hx.base.core.dao.entity.hualife.composite;

import java.io.Serializable;
import java.util.Objects;

/**
 * @ClassName: RankPromotionTrackId
 * @Description: 职级晋升轨迹表联合主键
 * @Author HuoJiaJin
 * @Date 2021/2/6 2:06
 * @Version 1.0
 **/
public class RankPromotionTrackId implements Serializable {

    private static final long serialVersionUID = -3922634910063220212L;
    private String agentCode;
    private String edorno;

    public String getAgentCode() {
        return agentCode;
    }

    public void setAgentCode(String agentCode) {
        this.agentCode = agentCode;
    }

    public String getEdorno() {
        return edorno;
    }

    public void setEdorno(String edorno) {
        this.edorno = edorno;
    }

    @Override
    public int hashCode() {
        return Objects.hash(agentCode, edorno);
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof IncubationId){
            IncubationId key = (IncubationId)obj ;
            if(this.agentCode.equals(key.getAgentCode()) && this.edorno.equals(key.getRearAgentCode())){
                return true ;
            }
        }
        return false ;
    }
}
