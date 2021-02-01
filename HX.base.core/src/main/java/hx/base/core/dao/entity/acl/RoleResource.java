package hx.base.core.dao.entity.acl;

import hx.base.core.dao.dict.acl.ResourceType;
import hx.base.core.dao.entity.common.StringUUIDEntity;

import javax.persistence.*;

/**
 * @name: RoleResource
 * @description: 角色资源表
 * @author: huojiajin
 * @time: 2020/5/25 17:08
 */
@Entity
@Table(name = "hx_role_resource", indexes = {
        @Index(columnList = "role_id", name = "hx_role_resource_index")
})
public class RoleResource extends StringUUIDEntity {

    private String roleId;//角色ID
    private ResourceType resourceType;//菜单枚举

    @Column(name = "role_id", nullable = false)
    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    @Enumerated(EnumType.STRING)
    @Column(name = "resource_type", nullable = false)
    public ResourceType getResourceType() {
        return resourceType;
    }

    public void setResourceType(ResourceType resourceType) {
        this.resourceType = resourceType;
    }
}
