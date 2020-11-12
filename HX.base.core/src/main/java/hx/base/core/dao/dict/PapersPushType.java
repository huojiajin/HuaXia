package hx.base.core.dao.dict;

/**
 * @name: PapersPushType
 * @description: 试卷推送方式
 * @author: huojiajin
 * @time: 2020/11/12 16:28
 */
public enum PapersPushType {

    RANK("按职级推送", 1){},
    CAMP("按营服推送", 2){},
    TRAIN("按培训场次推送", 3){},
    ;

    PapersPushType(String value, int code) {
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

    public static PapersPushType fromCode(int code) throws InterruptedException {
        for (PapersPushType type : PapersPushType.values()) {
            if (type.getCode() == code){
                return type;
            }
        }
        throw new InterruptedException("无此推送类型:" + code);
    }
}
