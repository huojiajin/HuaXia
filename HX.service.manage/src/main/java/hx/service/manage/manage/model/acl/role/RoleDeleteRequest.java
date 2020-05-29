package hx.service.manage.manage.model.acl.role;

import hx.service.manage.manage.model.CommonRequest;

/**
 * @name: RoleDeleteRequest
 * @description: 角色删除请求
 * @author: huojiajin
 * @time: 2020/5/28 13:47
 */
public class RoleDeleteRequest extends CommonRequest {

    private String roleId;

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }
}
