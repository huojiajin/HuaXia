package hx.base.core.dao.repo.jpa.acl;

import hx.base.core.dao.entity.acl.User;
import hx.base.core.dao.repo.jpa.common.AbstractJpaRepo;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @name: UserRepo
 * @description: 用户表Repo
 * @author: huojiajin
 * @time: 2020/5/25 18:37
 */
public interface UserRepo extends AbstractJpaRepo<User, String> {

    @Query("from User where loginName = ?1")
    User findByLoginName(String loginName);

    @Query("from User where roleId = ?1")
    List<User> listByRoleId(String roleId);

    @Modifying
    @Transactional
    @Query("update User set status = ?2, updateTime = ?3 where id = ?1")
    int updateStop(String id, User.UserStatus status, LocalDateTime updateTime);

    @Modifying
    @Transactional
    @Query("update User set password = ?2, updateTime = ?3 where id = ?1")
    int updatePassword(String id, String password, LocalDateTime updateTime);
}
