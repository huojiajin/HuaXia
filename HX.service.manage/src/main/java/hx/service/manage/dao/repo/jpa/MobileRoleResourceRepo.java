package hx.service.manage.dao.repo.jpa;

import hx.service.manage.dao.dict.PositionsClass;
import hx.service.manage.dao.dict.PositionsType;
import hx.service.manage.dao.entity.MobileRoleResource;
import hx.service.manage.dao.entity.Role;
import hx.service.manage.dao.repo.jpa.common.AbstractJpaRepo;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 *@ClassName MobileRoleResourceRepo
 *@Description 移动端资源配置Repo
 *@Author HuoJiaJin
 *@Date 2020/6/18 16:21
 *@Version 1.0
 **/
public interface MobileRoleResourceRepo extends AbstractJpaRepo<MobileRoleResource, String> {

    @Query("from MobileRoleResource where positionsType = ?1")
    List<MobileRoleResource> listByClass(PositionsType positionsType);

    @Modifying
    @Transactional
    @Query("delete from MobileRoleResource where positionsType = ?1")
    void deleteByClass(PositionsType positionsType);
}
