package hx.service.manage.model.message.huaxia;

import hx.base.core.dao.entity.common.BaseEntity;

/**
 * @name: MessageTextSendModel
 * @description: 发送文本信息-业务数据Model
 * @author: huojiajin
 * @time: 2021/3/11 16:11
 */
public class MessageSendTextModel extends MessageSendModel {


    private String safe = "0";//是否是保密消息，0表示否，1表示是，默认0
    private MessageTextModel text;//消息内容

    public String getSafe() {
        return safe;
    }

    public void setSafe(String safe) {
        this.safe = safe;
    }

    public MessageTextModel getText() {
        return text;
    }

    public void setText(MessageTextModel text) {
        this.text = text;
    }
}
