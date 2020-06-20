package hx.service.manage.dao.entity.composite;

import java.io.Serializable;
import java.util.Objects;

/**
 * @name: ContinueRateId
 * @description: 继续率表联合主键
 * @author: huojiajin
 * @time: 2020/6/17 16:14
 */
public class ContinueRateId implements Serializable {

    private static final long serialVersionUID = 4093840920537400507L;
    private String statMonth;//统计月
    private String agentCode;//业务员编码
    private Long calType;//统计类型

    public String getStatMonth() {
        return statMonth;
    }

    public void setStatMonth(String statMonth) {
        this.statMonth = statMonth;
    }

    public String getAgentCode() {
        return agentCode;
    }

    public void setAgentCode(String agentCode) {
        this.agentCode = agentCode;
    }

    public Long getCalType() {
        return calType;
    }

    public void setCalType(Long calType) {
        this.calType = calType;
    }

    @Override
    public boolean equals(Object o) {
        if(o instanceof ContinueRateId){
            ContinueRateId key = (ContinueRateId)o ;
            if(this.statMonth.equals(key.getStatMonth()) && this.agentCode.equals(key.getAgentCode())
                    && this.calType == key.getCalType()){
                return true ;
            }
        }
        return false ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(statMonth, agentCode, calType);
    }
}
