package hx.service.manage.model.message.huaxia;

import hx.base.core.dao.entity.common.BaseEntity;

/**
 * @name: MessageTextSendRequest
 * @description: 发送文本信息Request
 * @author: huojiajin
 * @time: 2021/3/11 16:09
 */
public class MessageSendRequest extends BaseEntity {

    private String msg_id = "";//消息id；appid的流水号段(serial_no) + timestamp（精确到毫秒） + 6位随机数
    private String busi_msg_type = "";//业务消息类型
    private String msg_remarks = "";//消息备注
    private MessageSendModel busi_data;//业务数据

    public String getMsg_id() {
        return msg_id;
    }

    public void setMsg_id(String msg_id) {
        this.msg_id = msg_id;
    }

    public String getBusi_msg_type() {
        return busi_msg_type;
    }

    public void setBusi_msg_type(String busi_msg_type) {
        this.busi_msg_type = busi_msg_type;
    }

    public String getMsg_remarks() {
        return msg_remarks;
    }

    public void setMsg_remarks(String msg_remarks) {
        this.msg_remarks = msg_remarks;
    }

    public MessageSendModel getBusi_data() {
        return busi_data;
    }

    public void setBusi_data(MessageSendModel busi_data) {
        this.busi_data = busi_data;
    }
}
