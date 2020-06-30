package hx.base.core.dao.repo.jpa.test.papers;

import hx.base.core.dao.repo.jpa.common.AbstractJpaRepo;
import hx.base.core.dao.entity.test.papers.PapersPush;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 *@ClassName PapersPushRepo
 *@Description 试卷推送Repo
 *@Author HuoJiaJin
 *@Date 2020/6/22 0:20
 *@Version 1.0
 **/
public interface PapersPushRepo extends AbstractJpaRepo<PapersPush, String> {

    @Query(" from PapersPush where papersId = ?1 and answerType = 'YDT'")
    List<PapersPush> listByPapersId(String papersId);

    @Query(" from PapersPush where agentCode = ?1")
    List<PapersPush> listByAgentCode(String papersId);

    @Query(" from PapersPush where agentCode = ?1 and answerType = 'YDT'")
    List<PapersPush> listComplete(String papersId);

    @Query(" from PapersPush where papersId = ?1 and agentCode = ?2")
    PapersPush findByPapersId(String papersId, String agentCode);

    @Modifying
    @Transactional
    @Query("update PapersPush set answerType = 'YDT', score = ?3, integral = ?4, completeTime = ?5 where agentCode = ?1 and papersId = ?2")
    int UpdateAnswer(String agentCode, String papersId, Integer score, Integer integral, LocalDateTime completeTime);
}
