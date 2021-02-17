package hx.base.core.dao.dict.salary;

/**
 * @ClassName: PrizeInfluenceType.java
 * @Description: 奖项影响因素类别
 * @Author HuoJiaJin
 * @Date 2021/2/7 2:57
 * @Version 1.0
**/
public enum PrizeInfluenceType {
    FYC("FYC"),
    CONTINUERATE("继续率13J"),
    ATTENDANCE("出勤天数"),
    HEALTH("健康人力"),
    ;

    PrizeInfluenceType(String value) {
        this.value = value;
    }

    private String value;

    public String getValue() {
        return value;
    }
}
