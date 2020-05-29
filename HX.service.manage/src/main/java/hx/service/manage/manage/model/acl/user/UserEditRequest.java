package hx.service.manage.manage.model.acl.user;

import hx.service.manage.manage.model.CommonRequest;

/**
 * @name: UserEditRequest
 * @description: 用户修改Request
 * @author: huojiajin
 * @time: 2020/5/28 15:10
 */
public class UserEditRequest extends CommonRequest {

    private String userId;//用户ID
    private String name;// 用户名
    private String mobile;// 手机号
    private String roleId;// 用户所属角色Id

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }
}
