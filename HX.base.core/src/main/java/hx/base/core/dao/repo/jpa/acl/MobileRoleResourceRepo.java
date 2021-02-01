package hx.base.core.dao.repo.jpa.acl;

import hx.base.core.dao.entity.acl.MobileRoleResource;
import hx.base.core.dao.repo.jpa.common.AbstractJpaRepo;
import hx.base.core.dao.dict.acl.PositionsType;
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
