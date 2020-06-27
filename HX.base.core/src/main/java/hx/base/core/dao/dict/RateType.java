package hx.base.core.dao.dict;

/**
 *@ClassName RateType
 *@Description 评级分类表
 *@Author HuoJiaJin
 *@Date 2020/6/26 17:37
 *@Version 1.0
 **/
public enum RateType {
    EXCELLENT("卓越", 4),
    OUTSTANDING("优秀", 3),
    HIGHPOTENTIAL("高潜", 2),
    LAGGINGBEHIND("后进", 1),
    ;


    RateType(String value, int code) {
        this.value = value;
        this.code = code;
    }

    private String value;
    private int code;

    public String getValue() {
        return value;
    }

    public int getCode() {
        return code;
    }

    public static RateType fromCode(int code){
        for (RateType type : RateType.values()) {
            if (type.getCode() == code){
                return type;
            }
        }
        return null;
    }
}
