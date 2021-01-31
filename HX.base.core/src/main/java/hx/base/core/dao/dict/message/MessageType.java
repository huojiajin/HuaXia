package hx.base.core.dao.dict.message;

/**  
 * @ClassName: MessageType
 * @Description: 消息类别
 * @Author HuoJiaJin
 * @Date 2021/1/31 20:55
 * @Version 1.0
**/
public enum MessageType {
    CUSTOM("自定义消息", 1){},
    DAILY_RETURN("日常通知-回访提醒", 21){},
    DAILY_UP("日常通知-上号提醒", 22){},
    DAILY_OUTWORK("日常通知-离职提醒", 23){},
    ;

    MessageType(String value, int code) {
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

    public static MessageType fromCode(int code) throws InterruptedException {
        for (MessageType type : MessageType.values()) {
            if (type.getCode() == code){
                return type;
            }
        }
        throw new InterruptedException("此类型不存在" + code);
    }

    /**
     * @Name isDaily
     * @Author HuoJiaJin
     * @Description 判断是否日常通知
     * @Date 2021/1/31 23:45
     * @Param [type]
     * @return boolean
     **/
    public static boolean isDaily(MessageType type){
        if (type != CUSTOM){
            return true;
        }
        return false;
    }
}
