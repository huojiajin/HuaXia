package hx.base.core.dao.dict.salary;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * @ClassName: PrizeType.java
 * @Description: 奖项类别
 * @Author HuoJiaJin
 * @Date 2021/2/7 2:57
 * @Version 1.0
**/
public enum PrizeType {
    CHIEFBUSINESS("区创业发展奖"){
        @Override
        public List<PrizeInfluenceType> getInfluenceTypes() {
            return Lists.newArrayList(PrizeInfluenceType.FYC, PrizeInfluenceType.CONTINUERATE);
        }
    },
    SECTIONBUSINESS("部创业发展奖"){
        @Override
        public List<PrizeInfluenceType> getInfluenceTypes() {
            return Lists.newArrayList(PrizeInfluenceType.FYC, PrizeInfluenceType.CONTINUERATE);
        }
    },
    GROUPBUSINESS("组创业发展奖"){
        @Override
        public List<PrizeInfluenceType> getInfluenceTypes() {
            return Lists.newArrayList(PrizeInfluenceType.FYC, PrizeInfluenceType.CONTINUERATE, PrizeInfluenceType.ATTENDANCE);
        }
    },
    ;

    PrizeType(String value) {
        this.value = value;
    }

    private String value;

    public String getValue() {
        return value;
    }

    public abstract List<PrizeInfluenceType> getInfluenceTypes();
}
