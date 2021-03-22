package hx.service.manage.manage.quartz;

import hx.base.core.dao.dict.message.MessageType;
import hx.base.core.dao.entity.black.BlackList;
import hx.base.core.dao.entity.message.MessageCustom;
import hx.base.core.dao.repo.jpa.black.BlackListRepo;
import hx.base.core.dao.repo.jpa.message.MessageCustomRepo;
import hx.base.core.manage.annotation.MyScheduler;
import hx.service.manage.manage.message.MessageManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.LocalDate;
import java.util.List;

/**
 * @name: BlackListMessageQuartz
 * @description: 黑名单消息
 * @author: huojiajin
 * @time: 2021/3/22 17:27
 */
@Service
@MyScheduler(name = "BLACK_LIST_MESSAGE", cron = "0 0 9 * * ?")
public class BlackListMessageQuartz extends CommonQuartz{

    @Autowired
    private MessageManager messageManager;
    @Autowired
    private BlackListRepo blackListRepo;
    @Autowired
    private MessageCustomRepo customRepo;

    @Override
    public void run() {
        LocalDate now = LocalDate.now();
        List<BlackList> blackLists = blackListRepo.findAll();
        for (BlackList blackList : blackLists) {
            switch (blackList.getType()) {
                case FYC:
                    if (now.getDayOfMonth() <= 25) {//每日推送一次消息，直至25号
                        deal(blackList, MessageType.BLACKLIST_FYC);
                    }
                    break;
                case ATTENDANCE:
                    if (now.getDayOfMonth() == 1) {//每月1号推送消息
                        deal(blackList, MessageType.BLACKLIST_ATTENDANCE);
                    }
                    break;
                case CONTINUE:
                    deal(blackList, MessageType.BLACKLIST_CONTINUE);
                    break;
            }
        }
    }

    private void deal(BlackList blackList, MessageType type) {
        List<MessageCustom> messageCustoms = customRepo.listByMessageType(type);
        if (!CollectionUtils.isEmpty(messageCustoms)){
            messageManager.sendTextOne(messageCustoms.get(0), blackList.getAgentCode());
        }
    }
}
