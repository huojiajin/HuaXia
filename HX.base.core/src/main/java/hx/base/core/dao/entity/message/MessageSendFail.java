package hx.base.core.dao.entity.message;

import hx.base.core.dao.entity.common.AbstractInsertTimeEntity;

import javax.persistence.*;

/**
 * @name: MessageSendFail
 * @description: 消息发送失败表实体
 * @author: huojiajin
 * @time: 2021/3/11 16:37
 */
@Entity
@Table(name = "hx_message_send_fail", indexes = {
        @Index(name = "hx_message_send_fail_index", columnList = "insert_time")
}, uniqueConstraints = {
        @UniqueConstraint(name = "hx_message_send_fail_index1", columnNames = {"agent_code", "custom_id"})
})
public class MessageSendFail extends AbstractInsertTimeEntity {

    private String agentCode;//员工工号
    private String customId;//自定义消息ID
    private String message;//失败日志

    @Column(name = "agent_code")
    public String getAgentCode() {
        return agentCode;
    }

    public void setAgentCode(String agentCode) {
        this.agentCode = agentCode;
    }

    @Column(name = "custom_id")
    public String getCustomId() {
        return customId;
    }

    public void setCustomId(String customId) {
        this.customId = customId;
    }

    @Column(name = "message")
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
