package hx.service.manage.manage.model.acl.role;

import hx.base.core.dao.entity.acl.Role;
import hx.base.core.dao.entity.common.BaseEntity;

import java.util.List;

/**
 * @name: RoleListResponse
 * @description: 获取角色列表Response
 * @author: huojiajin
 * @time: 2020/6/16 14:00
 */
public class RoleListResponse extends BaseEntity {

    private List<Role> roleList;

    public List<Role> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<Role> roleList) {
        this.roleList = roleList;
    }
}
