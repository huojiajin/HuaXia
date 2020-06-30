package hx.base.core.dao.repo.jpa.test.course;

import hx.base.core.dao.repo.jpa.common.AbstractJpaRepo;
import hx.base.core.dao.entity.test.course.CoursePush;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @name: CoursePushRepo
 * @description: 学习资料推送表Repo
 * @author: huojiajin
 * @time: 2020/6/22 17:08
 */
public interface CoursePushRepo extends AbstractJpaRepo<CoursePush, String> {

    @Query(" from CoursePush where courseId = ?1")
    List<CoursePush> listByCourseId(String courseId);

    @Query(" from CoursePush where agentCode = ?1")
    List<CoursePush> listByAgentCode(String agentCode);

    @Query(" from CoursePush where agentCode = ?1 and courseId = ?2")
    CoursePush findOne(String agentCode, String courseId);

    @Modifying
    @Transactional
    @Query("update CoursePush set hasLearn = true, learnTime = ?3 where agentCode = ?1 and courseId = ?2")
    int updateLearned(String agentCode, String courseId, LocalDateTime learnTime);
}
