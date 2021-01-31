package hx.base.core.dao.dict.message;

/**
 * @ClassName: ContentType
 * @Description: 消息内容类型
 * @Author HuoJiaJin
 * @Date 2021/1/31 20:52
 * @Version 1.0
 **/
public enum ContentType {
    TEXT("文字", 1){},
    IMAGE("图片", 2){}
    ;

    ContentType(String value, int code) {
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

    public static ContentType fromCode(int code) throws InterruptedException {
        for (ContentType type : ContentType.values()) {
            if (type.getCode() == code){
                return type;
            }
        }
        throw new InterruptedException("此类型不存在" + code);
    }
}
