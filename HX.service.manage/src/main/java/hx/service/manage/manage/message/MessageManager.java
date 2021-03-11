package hx.service.manage.manage.message;

import hx.base.core.dao.entity.message.MessageCustom;

import java.util.List;

/**
 * @ClassName: MessageManager
 * @Description: 总部消息推送相关接口
 * @Author HuoJiaJin
 * @Date 2021/2/1 0:51
 * @Version 1.0
 **/
public interface MessageManager {

    /**
     * @Name sendText
     * @Author HuoJiaJin
     * @Description 批量发送文本消息
     * @Date 2021/3/11 16:47
     * @Param [messageCustom, agentCodes]
     * @Return void
     **/
    void sendText(MessageCustom messageCustom, List<String> agentCodes);

    /**
     * @Name sendTextOne
     * @Author HuoJiaJin
     * @Description 发送文本消息
     * @Date 2021/3/11 18:41
     * @Param [messageCustom, agentCode]
     * @Return boolean
     **/
    boolean sendTextOne(MessageCustom messageCustom, String agentCode);
}
