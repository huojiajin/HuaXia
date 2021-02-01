package hx.base.core.dao.dict.staff;

/**
 * @ClassName TrainStatus
 * @Description 培训场次状态
 * @Author HuoJiaJin
 * @Date 2020/11/8 2:27
 * @Version 1.0
 **/
public enum TrainStatus {

    WDR("未导入", 1){},
    YDR("已导入", 2){},
    ;

    TrainStatus(String value, int code) {
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

    public static TrainStatus fromCode(int code) throws InterruptedException {
        for (TrainStatus status : TrainStatus.values()) {
            if (status.getCode() == code){
                return status;
            }
        }
        throw new InterruptedException("无此培训状态：" + code);
    }
}
