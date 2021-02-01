package hx.base.core.dao.entity.message;

import hx.base.core.dao.dict.message.ContentType;
import hx.base.core.dao.dict.message.MessageType;
import hx.base.core.dao.entity.common.AbstractInsertTimeEntity;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * @ClassName: MessageCustom
 * @Description: 自定义消息实体
 * @Author HuoJiaJin
 * @Date 2021/1/31 20:49
 * @Version 1.0
 **/
@Entity
@Table(name = "hx_message_custom")
public class MessageCustom extends AbstractInsertTimeEntity {

    private String name;//消息名称
    private ContentType contentType;//消息类型
    private MessageType messageType;//消息类别
    private String content;//消息内容
    private byte[] image;//图片
    private LocalDate deadline;//截止时间
    private boolean stop = false;

    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Enumerated(EnumType.STRING)
    @Column(name = "content_type")
    public ContentType getContentType() {
        return contentType;
    }

    public void setContentType(ContentType contentType) {
        this.contentType = contentType;
    }

    @Enumerated(EnumType.STRING)
    @Column(name = "message_type")
    public MessageType getMessageType() {
        return messageType;
    }

    public void setMessageType(MessageType messageType) {
        this.messageType = messageType;
    }

    @Column(name = "content")
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(name = "image")
    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    @Column(name = "dead_line")
    public LocalDate getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDate deadline) {
        this.deadline = deadline;
    }

    @Column(name = "is_stop")
    public boolean isStop() {
        return stop;
    }

    public void setStop(boolean stop) {
        this.stop = stop;
    }
}
