package hx.service.manage.dao.repo.jpa;

import hx.service.manage.dao.entity.User;
import hx.service.manage.dao.repo.jpa.common.AbstractJpaRepo;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

/**
 * @name: UserRepo
 * @description: 用户表Repo
 * @author: huojiajin
 * @time: 2020/5/25 18:37
 */
public interface UserRepo extends AbstractJpaRepo<User, String> {

    User findByLoginName(String loginName);

    @Modifying
    @Transactional
    @Query("update User set status = 'INVALID' where id = ?1")
    int updateDelete(String id);
}
