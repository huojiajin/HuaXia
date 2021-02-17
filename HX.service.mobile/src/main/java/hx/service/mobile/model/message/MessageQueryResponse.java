package hx.service.mobile.model.message;

import hx.base.core.dao.entity.common.BaseEntity;

/**
 * @ClassName: MessageQueryResponse
 * @Description: 消息通知查询Request
 * @Author HuoJiaJin
 * @Date 2021/2/6 1:07
 * @Version 1.0
 **/
public class MessageQueryResponse extends BaseEntity {

    private String content;//消息内容
    private String sendTime;//发送时间
    private boolean unread;//是否未读

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSendTime() {
        return sendTime;
    }

    public void setSendTime(String sendTime) {
        this.sendTime = sendTime;
    }

    public boolean isUnread() {
        return unread;
    }

    public void setUnread(boolean unread) {
        this.unread = unread;
    }
}
