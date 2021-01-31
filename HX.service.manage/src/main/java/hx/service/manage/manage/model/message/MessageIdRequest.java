package hx.service.manage.manage.model.message;

import hx.service.manage.manage.model.CommonRequest;

/**
 * @ClassName: MessageIdRequest
 * @Description: 自定义消息ID通用Request
 * @Author HuoJiaJin
 * @Date 2021/1/31 23:08
 * @Version 1.0
 **/
public class MessageIdRequest extends CommonRequest {

    private String messageId;//消息ID

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }
}
