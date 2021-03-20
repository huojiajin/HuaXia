package hx.service.manage.model.message.huaxia;

/**
 * @name: MessageSendPicModel
 * @description: 消息发送-图片发送Model
 * @author: huojiajin
 * @time: 2021/3/20 20:14
 */
public class MessageSendPicModel extends MessageSendModel{

    private MessagePicModel news;//消息信息参数

    public MessagePicModel getNews() {
        return news;
    }

    public void setNews(MessagePicModel news) {
        this.news = news;
    }
}
