package hx.base.core.dao.repo.jpa.acl;

import hx.base.core.dao.entity.acl.RoleResource;
import hx.base.core.dao.repo.jpa.common.AbstractJpaRepo;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @name: RoleResourceRepo
 * @description: 角色资源表Repo
 * @author: huojiajin
 * @time: 2020/5/25 18:37
 */
public interface RoleResourceRepo extends AbstractJpaRepo<RoleResource, String> {

    @Query("from RoleResource where roleId = ?1")
    List<RoleResource> listByRoleId(String roleId);

    @Modifying
    @Transactional
    @Query("delete from RoleResource where roleId = ?1")
    void deleteByRoleId(String roleId);
}
