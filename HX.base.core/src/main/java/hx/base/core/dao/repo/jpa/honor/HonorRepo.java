package hx.base.core.dao.repo.jpa.honor;

import hx.base.core.dao.entity.honor.Honor;
import hx.base.core.dao.repo.jpa.common.AbstractJpaRepo;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

/**
 * @ClassName HonorRepo
 * @Description 荣誉Repo
 * @Author HuoJiaJin
 * @Date 2020/6/27 5:05
 * @Version 1.0
 **/
public interface HonorRepo extends AbstractJpaRepo<Honor, String> {

    @Modifying
    @Transactional
    @Query("update Honor set stop = true, updateTime = ?2 where id = ?1")
    int updateDelete(String id, LocalDateTime updateTime);

    @Modifying
    @org.springframework.transaction.annotation.Transactional
    @Query("update Train set status = 'YDR', updateTime = ?1 where id = ?2")
    int updateImport(LocalDateTime updateTime, String id);
}
