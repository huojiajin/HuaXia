package hx.service.manage.manage.message;

import hx.service.manage.manage.model.message.*;

/**
 * @ClassName: MessageManager
 * @Description: 自定义消息Manager
 * @Author HuoJiaJin
 * @Date 2021/1/31 20:45
 * @Version 1.0
 **/
public interface MessageCustomManager {

    /**
     * @Name query
     * @Author HuoJiaJin
     * @Description 分页查询
     * @Date 2021/2/1 0:11
     * @Param [request]
     * @return java.lang.String
     **/
    String query(MessageQueryRequest request);

    /**
     * @Name add
     * @Author HuoJiaJin
     * @Description 添加
     * @Date 2021/2/1 0:11
     * @Param [request]
     * @return java.lang.String
     **/
    String add(MessageAddRequest request);

    /**
     * @Name delete
     * @Author HuoJiaJin
     * @Description 删除
     * @Date 2021/2/1 0:11
     * @Param [request]
     * @return java.lang.String
     **/
    String delete(MessageIdRequest request);

    /**
     * @Name queryLog
     * @Author HuoJiaJin
     * @Description 查询推送日志
     * @Date 2021/2/1 0:55
     * @Param [request]
     * @return java.lang.String
     **/
    String queryLog(MessageLogQueryRequest request);

    /**
     * @Name push
     * @Author HuoJiaJin
     * @Description 推送消息
     * @Date 2021/2/1 0:55
     * @Param [request]
     * @return java.lang.String
     **/
    String push(MessagePushRequest request);
}
