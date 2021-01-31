package hx.base.core.dao.repo.request.message;

import hx.base.core.dao.entity.message.MessageCustomLog;
import hx.base.core.dao.repo.request.common.HqlBuilder;
import hx.base.core.dao.repo.request.common.JpaPageableDataRequest;

/**
 * @name: MessageCustomLogPageRequest
 * @description: 自定义消息日志分页查询
 * @author: huojiajin
 * @time: 2020/7/3 11:42
 */
public class MessageCustomLogPageRequest extends JpaPageableDataRequest<MessageCustomLog> {

    private String messageId;//消息ID

    public MessageCustomLogPageRequest() {
        this.orderBy = "pushTime";
        this.desc = true;
    }

    @Override
    public HqlBuilder toSelectHql() {
        HqlBuilder hql = new HqlBuilder("from " + clazz.getName() + " where 1=1");
        hql.append(" and messageId = :messageId", messageId);
        return hql;
    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }
}
