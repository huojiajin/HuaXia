package hx.base.core.dao.dict.structure;

/**
 *@ClassName RadarStandardType
 *@Description 主管经营雷达图指标类型
 *@Author HuoJiaJin
 *@Date 2020/6/27 2:14
 *@Version 1.0
 **/
public enum RadarStandardType {
    STADPREM("月均标保"){},
    PERSONSTAR("个人星级"){},
    STARPOWER("团队星级人力"){},
    RATE("继续率"){},
    ATTENDPOWER("出勤人力"){},
    ;

    RadarStandardType(String value) {
        this.value = value;
    }

    private String value;

    public String getValue() {
        return value;
    }
}
