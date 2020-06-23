package hx.service.manage.dao.repo.jpa.test.course;

import hx.service.manage.dao.dict.CourseStatus;
import hx.service.manage.dao.entity.test.course.Course;
import hx.service.manage.dao.repo.jpa.common.AbstractJpaRepo;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

/**
 * @name: CourseRepo
 * @description: 学习资料表Repo
 * @author: huojiajin
 * @time: 2020/6/22 17:08
 */
public interface CourseRepo extends AbstractJpaRepo<Course, String> {

    @Modifying
    @Transactional
    @Query("update Course set hasDelete = true where id = ?1")
    int updateDelete(String id);

    @Modifying
    @Transactional
    @Query("update Course set status = ?2 where id = ?1")
    int updateStatus(String id, CourseStatus status);
}
