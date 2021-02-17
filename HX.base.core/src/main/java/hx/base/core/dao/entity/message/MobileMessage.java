package hx.base.core.dao.entity.message;

import hx.base.core.dao.entity.common.AbstractInsertTimeEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;
import java.time.LocalDateTime;

/**
 * @ClassName: MobileMessage
 * @Description: 移动端消息实体
 * @Author HuoJiaJin
 * @Date 2021/2/6 0:34
 * @Version 1.0
 **/
@Entity
@Table(name = "hx_mobile_message", indexes = {
        @Index(name = "hx_mobile_message_index", columnList = "agent_code")
})
public class MobileMessage extends AbstractInsertTimeEntity {

    private String agentCode;//人员编码
    private String content;//消息内容
    private LocalDateTime sendTime;//发送时间
    private boolean hasRead;//是否已读
    private LocalDateTime readTime;//阅读时间

    @Column(name = "agent_code")
    public String getAgentCode() {
        return agentCode;
    }

    public void setAgentCode(String agentCode) {
        this.agentCode = agentCode;
    }

    @Column(name = "content")
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Column(name = "send_time")
    public LocalDateTime getSendTime() {
        return sendTime;
    }

    public void setSendTime(LocalDateTime sendTime) {
        this.sendTime = sendTime;
    }

    @Column(name = "has_read")
    public boolean isHasRead() {
        return hasRead;
    }

    public void setHasRead(boolean read) {
        this.hasRead = read;
    }

    @Column(name = "read_time")
    public LocalDateTime getReadTime() {
        return readTime;
    }

    public void setReadTime(LocalDateTime readTime) {
        this.readTime = readTime;
    }
}
