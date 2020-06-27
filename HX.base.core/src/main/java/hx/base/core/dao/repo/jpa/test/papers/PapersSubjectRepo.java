package hx.base.core.dao.repo.jpa.test.papers;

import hx.base.core.dao.repo.jpa.common.AbstractJpaRepo;
import hx.base.core.dao.entity.test.papers.PapersSubject;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 *@ClassName PapersSubjectRepo
 *@Description 试卷题目Repo
 *@Author HuoJiaJin
 *@Date 2020/6/21 14:07
 *@Version 1.0
 **/
public interface PapersSubjectRepo extends AbstractJpaRepo<PapersSubject, String> {

    @Query(" from PapersSubject where papersId = ?1 order by list")
    List<PapersSubject> listByPapersId(String papersId);
}
