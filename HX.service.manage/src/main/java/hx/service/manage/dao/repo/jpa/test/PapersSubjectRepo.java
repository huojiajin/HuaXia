package hx.service.manage.dao.repo.jpa.test;

import hx.service.manage.dao.entity.test.PapersSubject;
import hx.service.manage.dao.repo.jpa.common.AbstractJpaRepo;
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
