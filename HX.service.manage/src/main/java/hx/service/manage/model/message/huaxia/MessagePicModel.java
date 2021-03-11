package hx.service.manage.model.message.huaxia;

import hx.base.core.dao.entity.common.BaseEntity;

import java.util.List;

/**
 * @name: MessagePicModel
 * @description: 发送文本信息-图片Model
 * @author: huojiajin
 * @time: 2021/3/11 18:07
 */
public class MessagePicModel extends BaseEntity {

    private List<MessagePicContentModel> articles;//图文消息，一个图文消息支持1到8条图文，不能为空。（内容必须为数组类型）

    public List<MessagePicContentModel> getArticles() {
        return articles;
    }

    public void setArticles(List<MessagePicContentModel> articles) {
        this.articles = articles;
    }
}
