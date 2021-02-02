package hx.base.core.dao.dict.black;

/**
 * @ClassName: BlackListType.java
 * @Description: 黑名单类型
 * @Author HuoJiaJin
 * @Date 2021/2/3 0:34
 * @Version 1.0
**/
public enum BlackListType {
    FYC("连续6个月FYC为0", 1){},
    ATTENDANCE("连续3个月出勤天数为0", 2){},
    CONTINUE("个人13J继续率<80%", 3){},
    ;

    BlackListType(String value, int code) {
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

    public static BlackListType fromCode(int code) throws InterruptedException {
        for (BlackListType type : BlackListType.values()) {
            if (type.getCode() == code){
                return type;
            }
        }
        throw new InterruptedException("无此类型代码" + code);
    }
}
