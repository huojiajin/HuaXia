package hx.service.manage.model.message;

import hx.base.core.dao.entity.common.BaseEntity;

/**
 * @ClassName: MessageQueryResponse
 * @Description: 自定义消息查询Response
 * @Author HuoJiaJin
 * @Date 2021/1/31 23:09
 * @Version 1.0
 **/
public class MessageQueryResponse extends BaseEntity {

    private String id;//消息id
    private String name;//消息名称
    private int contentType;//内容类型
    private int messageType;//消息类别
    private String content;//消息内容
    private String deadline;//截止时间
    private String title;//标题
    private String description;//描述

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
