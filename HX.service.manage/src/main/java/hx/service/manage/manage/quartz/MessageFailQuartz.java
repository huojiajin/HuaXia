package hx.service.manage.manage.quartz;

import hx.base.core.dao.entity.message.MessageCustom;
import hx.base.core.dao.entity.message.MessageSendFail;
import hx.base.core.dao.repo.jpa.message.MessageCustomRepo;
import hx.base.core.dao.repo.jpa.message.MessageSendFailRepo;
import hx.base.core.dao.repo.request.common.Pagination;
import hx.base.core.dao.repo.request.message.MessageSendFailPageRequest;
import hx.base.core.manage.annotation.MyScheduler;
import hx.service.manage.manage.message.MessageManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @name: MessageFailQuartz
 * @description: 消息推送失败重试定时任务
 * @author: huojiajin
 * @time: 2021/3/11 18:30
 */
@Service
@MyScheduler(name = "MESSAGE_FAIL", cron = "0 10 * * * ?")
public class MessageFailQuartz extends CommonQuartz{

    @Autowired
    private MessageManager manager;
    @Autowired
    private MessageSendFailRepo sendFailRepo;
    @Autowired
    private MessageCustomRepo customRepo;

    @Override
    public void run() {
        //分页查询推送失败数据
        MessageSendFailPageRequest pageRequest = new MessageSendFailPageRequest();
        pageRequest.setPageSize(100);
        Pagination page;
        int pageNo = 1;
        int count = 0;
        do {
            page = sendFailRepo.page(pageRequest);
            //推送数据
            List<MessageSendFail> failList = page.getResult(MessageSendFail.class);
            for (MessageSendFail fail : failList) {
                MessageCustom custom = customRepo.findById(fail.getCustomId()).orElse(null);
                if (custom != null) {//推送消息
                    boolean hasSend = manager.sendTextOne(custom, fail.getAgentCode());
                    if (hasSend){//如果推送成功，则删除失败记录
                        sendFailRepo.deleteById(fail.getId());
                    }
                }
            }
            pageNo++;
            pageRequest.setPageNo(pageNo);
            count += failList.size();
            logger.info("======已重新推送{}条失败数据", count);
        }while (page.getCurrentPage() < page.getPageCount());
    }
}
