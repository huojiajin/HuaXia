package hx.base.core.dao.repo.jpa.message;

import hx.base.core.dao.dict.message.MessageType;
import hx.base.core.dao.entity.message.MessageCustom;
import hx.base.core.dao.repo.jpa.common.AbstractJpaRepo;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @ClassName MessageCustomRepo
 * @Description 自定义消息Repo
 * @Author HuoJiaJin
 * @Date 2020/6/27 5:05
 * @Version 1.0
 **/
public interface MessageCustomRepo extends AbstractJpaRepo<MessageCustom, String> {

    @Query(" from MessageCustom where messageType = ?1")
    List<MessageCustom> listByMessageType(MessageType messageType);

    @Modifying
    @Transactional
    @Query("update MessageCustom set stop = true, updateTime = ?2 where id = ?1")
    int updateDelete(String id, LocalDateTime updateTime);
}
