package hx.service.manage.model.acl.role;

import hx.service.manage.model.common.CommonRequest;

/**
 * @name: RoleIdRequest
 * @description: 角色ID通用类
 * @author: huojiajin
 * @time: 2020/5/28 13:47
 */
public class RoleIdRequest extends CommonRequest {

    private String roleId;

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }
}
