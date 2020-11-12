package hx.base.core.dao.repo.jpa.staff;

import hx.base.core.dao.entity.staff.Train;
import hx.base.core.dao.repo.jpa.common.AbstractJpaRepo;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

/**
 * @ClassName TrainRepo
 * @Description 培训场次表Repo
 * @Author HuoJiaJin
 * @Date 2020/11/8 2:38
 * @Version 1.0
 **/
public interface TrainRepo extends AbstractJpaRepo<Train, String> {

    @Modifying
    @Transactional
    @Query("update Train set hasDelete = true where id = ?1")
    int updateDelete(String id);

    @Modifying
    @Transactional
    @Query("update Train set status = 'YDR', peopleNum = ?1, updateTime = ?2 where id = ?3")
    int updateImport(int peopleNum, LocalDateTime updateTime, String id);
}
