package hx.service.mobile.manage.login;

import hx.service.mobile.manage.model.login.LoginRequest;

/**
 * @ClassName LoginManager
 * @Description 登录Manager
 * @Author HuoJiaJin
 * @Date 2020/6/23 21:44
 * @Version 1.0
 **/
public interface LoginManager {

    /**
     * @Name loginInfo
     * @Author HuoJiaJin
     * @Description 获取登录信息
     * @Date 2020/6/26
     * @Param []
     * @return java.lang.String
     **/
    String loginInfo();

    String login(LoginRequest request);
}
