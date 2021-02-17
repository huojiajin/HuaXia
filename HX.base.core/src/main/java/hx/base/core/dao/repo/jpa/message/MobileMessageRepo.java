package hx.base.core.dao.repo.jpa.message;

import hx.base.core.dao.entity.message.MobileMessage;
import hx.base.core.dao.repo.jpa.common.AbstractJpaRepo;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

/**
 * @ClassName MobileMessageRepo
 * @Description 移动端消息日志Repo
 * @Author HuoJiaJin
 * @Date 2020/6/27 5:05
 * @Version 1.0
 **/
public interface MobileMessageRepo extends AbstractJpaRepo<MobileMessage, String> {

    @Modifying
    @Transactional
    @Query("update MobileMessage set hasRead = true, sendTime = ?2 where agentCode = ?1")
    int updateRead(String agetnCode, LocalDateTime readTime);
}
