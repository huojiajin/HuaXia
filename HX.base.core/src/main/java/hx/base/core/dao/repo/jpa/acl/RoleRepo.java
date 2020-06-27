package hx.base.core.dao.repo.jpa.acl;

import hx.base.core.dao.entity.acl.Role;
import hx.base.core.dao.repo.jpa.common.AbstractJpaRepo;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

/**
 * @name: RoleRepo
 * @description: 角色表Repo
 * @author: huojiajin
 * @time: 2020/5/25 18:37
 */
public interface RoleRepo extends AbstractJpaRepo<Role, String> {

    @Modifying
    @Transactional
    @Query("update Role set stop = 1 where id = ?1")
    int updateDelete(String id);
}
