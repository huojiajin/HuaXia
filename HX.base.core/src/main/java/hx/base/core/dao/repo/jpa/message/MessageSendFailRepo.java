package hx.base.core.dao.repo.jpa.message;

import hx.base.core.dao.entity.message.MessageSendFail;
import hx.base.core.dao.repo.jpa.common.AbstractJpaRepo;

/**
 *@ClassName MessageSendFailRepo.java
 *@Description 消息发送失败表Repo
 *@Author HuoJiaJin
 *@Date 2021/3/11 16:40
 *@Version 1.0
 **/
public interface MessageSendFailRepo extends AbstractJpaRepo<MessageSendFail, String> {

    MessageSendFail findByCustomIdAndAgentCode(String customId, String agentCode);
}
