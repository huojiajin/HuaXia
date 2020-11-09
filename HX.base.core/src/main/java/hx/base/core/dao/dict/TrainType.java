package hx.base.core.dao.dict;

/**
 * @ClassName TrainType
 * @Description 培训场次状态
 * @Author HuoJiaJin
 * @Date 2020/11/8 2:27
 * @Version 1.0
 **/
public enum TrainType {

    WDR("未导入", 1){},
    YDR("已导入", 2){},
    ;

    TrainType(String value, int code) {
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
