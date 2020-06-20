package hx.service.manage.dao.repo.jpa;

import hx.service.manage.dao.entity.User;
import hx.service.manage.dao.repo.jpa.common.AbstractJpaRepo;
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

    User findByLoginName(String loginName);

    @Query("from User where roleId = ?1")
    List<User> listByRoleId(String roleId);

    @Modifying
    @Transactional
    @Query("update User set status = ?2, updateTime = ?3 where id = ?1")
    int updateStop(String id, User.UserStatus status, LocalDateTime updateTime);
}
