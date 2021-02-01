package hx.base.core.dao.dict.honor;

/**
 * @ClassName HonorStatus
 * @Description 荣誉状态
 * @Author HuoJiaJin
 * @Date 2020/6/21 13:51
 * @Version 1.0
 **/
public enum HonorStatus {
    WDR("未导入", 0),
    YDR("已导入", 1),
    ;

    HonorStatus(String value, int code) {
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
}
