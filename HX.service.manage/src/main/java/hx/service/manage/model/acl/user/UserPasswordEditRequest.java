package hx.service.manage.model.acl.user;

import hx.service.manage.model.common.CommonRequest;

/**
 * @name: UserPasswordEditRequest
 * @description: 修改密码Request
 * @author: huojiajin
 * @time: 2020/7/12 14:55
 */
public class UserPasswordEditRequest extends CommonRequest {

    private String password;//密码
    private String newPassword;//新密码

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}
