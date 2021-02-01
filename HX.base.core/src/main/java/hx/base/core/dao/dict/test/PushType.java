package hx.base.core.dao.dict.test;

/**
 * @name: PushType
 * @description: 试卷及学习资料推送方式
 * @author: huojiajin
 * @time: 2020/11/12 16:28
 */
public enum PushType {

    RANK("按职级推送", 1){},
    CAMP("按营服推送", 2){},
    TRAIN("按培训场次推送", 3){},
    ;

    PushType(String value, int code) {
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

    public static PushType fromCode(int code) throws InterruptedException {
        for (PushType type : PushType.values()) {
            if (type.getCode() == code){
                return type;
            }
        }
        throw new InterruptedException("无此推送类型:" + code);
    }
}
