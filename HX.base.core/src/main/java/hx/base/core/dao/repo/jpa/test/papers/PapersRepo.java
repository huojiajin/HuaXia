package hx.base.core.dao.repo.jpa.test.papers;

import hx.base.core.dao.repo.jpa.common.AbstractJpaRepo;
import hx.base.core.dao.dict.PapersStatus;
import hx.base.core.dao.entity.test.papers.Papers;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

/**
 *@ClassName PapersRepo
 *@Description 试卷Repo
 *@Author HuoJiaJin
 *@Date 2020/6/21 14:07
 *@Version 1.0
 **/
public interface PapersRepo extends AbstractJpaRepo<Papers, String> {

    @Modifying
    @Transactional
    @Query("update Papers set hasDelete = true where id = ?1")
    int updateDelete(String id);

    @Modifying
    @Transactional
    @Query("update Papers set status = ?2 where id = ?1")
    int updateStatus(String id, PapersStatus status);
}
