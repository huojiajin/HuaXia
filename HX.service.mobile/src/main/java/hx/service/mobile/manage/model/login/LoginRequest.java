package hx.service.mobile.manage.model.login;

import hx.base.core.dao.entity.common.BaseEntity;

/**
 * @ClassName LoginRequest
 * @Description 登录Request
 * @Author HuoJiaJin
 * @Date 2020/6/26 13:47
 * @Version 1.0
 **/
public class LoginRequest extends BaseEntity {

    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
