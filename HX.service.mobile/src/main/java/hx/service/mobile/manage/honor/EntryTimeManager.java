package hx.service.mobile.manage.honor;

import hx.service.mobile.manage.model.common.MobileCommonRequest;

/**
 * @name: EntryTimeManager
 * @description: 入职计时Manager
 * @author: huojiajin
 * @time: 2020/7/7 14:59
 */
public interface EntryTimeManager {
    /**
     * @Name entryTime
     * @Author HuoJiaJin
     * @Description 获取员工入职计时
     * @Date 2020/7/7 15:10
     * @Param [request]
     * @return java.lang.String
     **/
    String entryTime(MobileCommonRequest request);
}
