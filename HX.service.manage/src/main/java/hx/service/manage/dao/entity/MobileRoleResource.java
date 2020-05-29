package hx.service.manage.dao.entity;

import hx.service.manage.dao.entity.common.StringUUIDEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;

/**
 * @name: MobileRoleResource
 * @description: 手机角色资源表（按层级配置）
 * @author: huojiajin
 * @time: 2020/5/25 17:08
 */
@Entity
@Table(name = "hx_mobile_role_resource", indexes = {
        @Index(columnList = "role_id", name = "hx_role_resource_index")
})
public class MobileRoleResource extends StringUUIDEntity {

    private String roleId;//角色ID
    private String resource;//菜单ID

    @Column(name = "role_id", nullable = false)
    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    @Column(name = "resource_id", nullable = false)
    public String getResource() {
        return resource;
    }

    public void setResource(String resource) {
        this.resource = resource;
    }
}