package hx.service.mobile.manage.test;

import hx.service.mobile.manage.model.common.MobileCommonRequest;

/**
 * @ClassName SignInManager
 * @Description 签到相关Manager
 * @Author HuoJiaJin
 * @Date 2020/6/27 20:09
 * @Version 1.0
 **/
public interface SignInManager {

    /**
     * @Name signIn
     * @Author HuoJiaJin
     * @Description 签到
     * @Date 2020/6/27 20:26
     * @Param [request]
     * @return java.lang.String
     **/
    String signIn(MobileCommonRequest request);

    String integralRank(MobileCommonRequest request);
}
