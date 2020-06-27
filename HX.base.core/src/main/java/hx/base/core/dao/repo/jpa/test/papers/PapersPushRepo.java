package hx.base.core.dao.repo.jpa.test.papers;

import hx.base.core.dao.repo.jpa.common.AbstractJpaRepo;
import hx.base.core.dao.entity.test.papers.PapersPush;
import org.springframework.data.jpa.repository.Query;

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
}
