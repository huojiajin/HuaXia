package hx.service.manage.dao.repo.jpa.test.papers;

import hx.service.manage.dao.entity.test.papers.PapersPushAnswer;
import hx.service.manage.dao.repo.jpa.common.AbstractJpaRepo;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 *@ClassName PapersPushAnswerRepo
 *@Description 试卷推送答题结果Repo
 *@Author HuoJiaJin
 *@Date 2020/6/22 1:25
 *@Version 1.0
 **/
public interface PapersPushAnswerRepo extends AbstractJpaRepo<PapersPushAnswer, String> {

    @Query(" from PapersPushAnswer where pushId = ?1")
    List<PapersPushAnswer> listByPushId(String pushId);

    @Query(" from PapersPushAnswer where pushId in (?1)")
    List<PapersPushAnswer> listByPushIds(List<String> pushId);
}
