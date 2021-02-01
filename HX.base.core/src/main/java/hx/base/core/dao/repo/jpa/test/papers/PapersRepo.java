package hx.base.core.dao.repo.jpa.test.papers;

import hx.base.core.dao.dict.test.PapersStatus;
import hx.base.core.dao.dict.test.PapersType;
import hx.base.core.dao.entity.test.papers.Papers;
import hx.base.core.dao.repo.jpa.common.AbstractJpaRepo;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
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
    @Query("update Papers set hasDelete = true, updateTime = ?2 where id = ?1")
    int updateDelete(String id, LocalDateTime updateTime);

    @Modifying
    @Transactional
    @Query("update Papers set status = ?2, updateTime = ?3 where id = ?1")
    int updateStatus(String id, PapersStatus status, LocalDateTime updateTime);

    @Query("from Papers where id in (?1) and hasDelete = false")
    List<Papers> listByIds(List<String> ids);

    @Modifying
    @Transactional
    @Query("update Papers set subjectNum = ?2, status = 'YDR' where id = ?1")
    int updateYDR(String id, int subjectNum);

    @Query("from Papers where id in (?1) and type in (?2) and hasDelete = false")
    List<Papers> listComplete(List<String> ids, List<PapersType> types);

    @Query("from Papers where name = ?1 and hasDelete = false")
    Papers findByName(String name);
}
