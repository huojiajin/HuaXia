package hx.base.core.dao.repo.jpa.test.papers;

import hx.base.core.dao.repo.jpa.common.AbstractJpaRepo;
import hx.base.core.dao.entity.test.papers.PapersOption;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 *@ClassName PapersOptionRepo
 *@Description 试卷题目Repo
 *@Author HuoJiaJin
 *@Date 2020/6/21 14:07
 *@Version 1.0
 **/
public interface PapersOptionRepo extends AbstractJpaRepo<PapersOption, String> {

    @Query(" from PapersOption where subjectId = ?1 order by list")
    List<PapersOption> listBySubjectId(String subjectId);
}
