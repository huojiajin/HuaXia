package hx.base.core.dao.entity.message;

import hx.base.core.dao.dict.PushType;
import hx.base.core.dao.entity.common.StringUUIDEntity;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * @ClassName: MessageCustomLog
 * @Description: TODO
 * @Author HuoJiaJin
 * @Date 2021/1/31 21:05
 * @Version 1.0
 **/
@Entity
@Table(name = "hx_message_custom_log", indexes = {
        @Index(name = "hx_message_custom_log_index", columnList = "message_id")
})
public class MessageCustomLog extends StringUUIDEntity {

    private String messageId;//消息ID
    private PushType pushType;//推送类型
    private String pushCode;//推送职级/营服代码,用逗号分隔
    private String pushName;//推送职级/营服名称,用逗号分隔
    private LocalDateTime pushTime;//推送时间

    @Column(name = "message_id")
    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    @Enumerated(EnumType.STRING)
    @Column(name = "push_type")
    public PushType getPushType() {
        return pushType;
    }

    public void setPushType(PushType pushType) {
        this.pushType = pushType;
    }

    @Column(name = "push_code")
    public String getPushCode() {
        return pushCode;
    }

    public void setPushCode(String pushCode) {
        this.pushCode = pushCode;
    }

    @Column(name = "push_name")
    public String getPushName() {
        return pushName;
    }

    public void setPushName(String pushName) {
        this.pushName = pushName;
    }

    @Column(name = "push_time")
    public LocalDateTime getPushTime() {
        return pushTime;
    }

    public void setPushTime(LocalDateTime pushTime) {
        this.pushTime = pushTime;
    }
}
