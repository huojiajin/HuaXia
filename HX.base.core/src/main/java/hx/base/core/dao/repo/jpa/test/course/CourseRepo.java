package hx.base.core.dao.repo.jpa.test.course;

import hx.base.core.dao.dict.CourseType;
import hx.base.core.dao.repo.jpa.common.AbstractJpaRepo;
import hx.base.core.dao.dict.CourseStatus;
import hx.base.core.dao.entity.test.course.Course;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

    @Query("from Course where id in (?1) and type = ?2")
    List<Course> listByIds(List<String> ids, CourseType type);

    @Query("from Course where name = ?1 and hasDelete = 0")
    Course findByName(String name);
}
