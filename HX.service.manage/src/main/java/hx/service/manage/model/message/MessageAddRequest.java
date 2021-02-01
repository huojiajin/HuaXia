package hx.service.manage.model.message;

import hx.service.manage.model.common.CommonRequest;

/**
 * @ClassName: MessageAddRequest
 * @Description: 自定义消息添加Request
 * @Author HuoJiaJin
 * @Date 2021/1/31 23:10
 * @Version 1.0
 **/
public class MessageAddRequest extends CommonRequest {

    private String name;//消息名称
    private int contentType;//内容类型
    private int messageType;//消息类别
    private String content;//消息内容
    private String deadline;//截止时间

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getContentType() {
        return contentType;
    }

    public void setContentType(int contentType) {
        this.contentType = contentType;
    }

    public int getMessageType() {
        return messageType;
    }

    public void setMessageType(int messageType) {
        this.messageType = messageType;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }
}
