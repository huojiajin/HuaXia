package hx.service.manage.dao.repo.jpa.test.course;

import hx.service.manage.dao.entity.test.course.CoursePush;
import hx.service.manage.dao.repo.jpa.common.AbstractJpaRepo;
import org.springframework.data.jpa.repository.Query;

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
}