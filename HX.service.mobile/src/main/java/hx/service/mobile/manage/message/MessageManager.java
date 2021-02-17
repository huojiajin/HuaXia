package hx.service.mobile.manage.message;

import hx.service.mobile.model.common.MobileCommonPageRequest;
import hx.service.mobile.model.common.MobileCommonRequest;

/**
 * @ClassName: MessageManager
 * @Description: 消息通知Manager
 * @Author HuoJiaJin
 * @Date 2021/2/6 1:08
 * @Version 1.0
 **/
public interface MessageManager {
    /**
     * @Name query
     * @Author HuoJiaJin
     * @Description 查询
     * @Date 2021/2/6 1:21
     * @Param [request]
     * @Return java.lang.String
     **/
    String query(MobileCommonPageRequest request);

    /**
     * @Name read
     * @Author HuoJiaJin
     * @Description 清除未读消息
     * @Date 2021/2/6 1:21
     * @Param [request]
     * @Return java.lang.String
     **/
    String read(MobileCommonRequest request);
}
