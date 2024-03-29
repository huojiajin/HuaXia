package hx.service.manage.model.login;

import hx.base.core.dao.entity.common.BaseEntity;

/**
 * @name: LoginRequest
 * @description: 登陆Request
 * @author: huojiajin
 * @time: 2020/5/27 15:45
 */
public class LoginRequest extends BaseEntity {

    private String loginName;//登陆名
    private String password;//密码
    private String verifyCode;//验证码
    private String verifyId;//验证码ID

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getVerifyCode() {
        return verifyCode;
    }

    public void setVerifyCode(String verifyCode) {
        this.verifyCode = verifyCode;
    }

    public String getVerifyId() {
        return verifyId;
    }

    public void setVerifyId(String verifyId) {
        this.verifyId = verifyId;
    }
}
