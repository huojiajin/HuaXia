package hx.service.manage.model.message.huaxia;

import hx.base.core.dao.entity.common.BaseEntity;

/**
 * @name: MessageTextContentModel
 * @description: 发送文本信息-文本内容Model
 * @author: huojiajin
 * @time: 2021/3/11 16:14
 */
public class MessageTextModel extends BaseEntity {

    private String content = "";//消息内容

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
