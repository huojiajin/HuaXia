package hx.service.manage.manage.model.message;

import hx.service.manage.manage.model.CommonPageRequest;

/**
 * @ClassName: MessageLogQueryRequest
 * @Description: TODO
 * @Author HuoJiaJin
 * @Date 2021/1/31 23:16
 * @Version 1.0
 **/
public class MessageLogQueryRequest extends CommonPageRequest {

    private String messageId;//消息ID

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }
}
