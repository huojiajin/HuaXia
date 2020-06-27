package hx.base.core.dao.repo.jpa.test.papers;

import hx.base.core.dao.dict.PapersStatus;
import hx.base.core.dao.entity.test.papers.Papers;
import hx.base.core.dao.repo.jpa.common.AbstractJpaRepo;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

    @Query("from Papers where id in (?1) and hasDelete = false")
    List<Papers> listByIds(List<String> ids);

    @Modifying
    @Transactional
    @Query("update Papers set subjectNum = ?2, status = 'YDR' where id = ?1")
    int updateYDR(String id, int subjectNum);
}
