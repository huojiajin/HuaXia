package hx.service.manage.dao.entity;

import hx.service.manage.dao.dict.PositionsClass;
import hx.service.manage.dao.dict.PositionsType;
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
@Table(name = "hx_mobile_role_resource")
public class MobileRoleResource extends StringUUIDEntity {

    private PositionsType positionsType;
    private String resource;//菜单ID

    @Column(name = "positions_type", nullable = false)
    public PositionsType getPositionsType() {
        return positionsType;
    }

    public void setPositionsType(PositionsType positionsType) {
        this.positionsType = positionsType;
    }

    @Column(name = "resource_id", nullable = false)
    public String getResource() {
        return resource;
    }

    public void setResource(String resource) {
        this.resource = resource;
    }
}
