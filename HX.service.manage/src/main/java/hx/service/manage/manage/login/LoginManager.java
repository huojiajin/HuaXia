package hx.service.manage.manage.login;

import hx.service.manage.model.login.LoginRequest;

/**
 * @name: LoginManager
 * @description: 登陆Manager
 * @author: huojiajin
 * @time: 2020/5/27 15:51
 */
public interface LoginManager {

    String getVerifyImage();

    String login(LoginRequest loginRequest);
}
