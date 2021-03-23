package hx.service.manage.manage.quartz;

import hx.base.core.dao.dict.acl.PositionsClass;
import hx.base.core.dao.dict.acl.PositionsType;
import hx.base.core.dao.dict.message.MessageType;
import hx.base.core.dao.entity.hualife.MarketingManpower;
import hx.base.core.dao.entity.message.MessageCustom;
import hx.base.core.dao.repo.jpa.hualife.MarketingManpowerRepo;
import hx.base.core.dao.repo.jpa.message.MessageCustomRepo;
import hx.base.core.dao.repo.request.common.Pagination;
import hx.base.core.dao.repo.request.hualife.MarketingManpowerPageRequest;
import hx.base.core.manage.annotation.MyScheduler;
import hx.service.manage.manage.message.MessageManager;
import org.apache.commons.compress.utils.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.LocalDate;
import java.util.List;

/**
 * @name: DaliyMessageQuartz
 * @description: 日常消息发送
 * @author: huojiajin
 * @time: 2021/3/22 17:38
 */
@Service
@MyScheduler(name = "DALIY_MESSAGE", cron = "0 10 9 * * ?")
public class DaliyMessageQuartz extends CommonQuartz{

    @Autowired
    private MessageManager messageManager;
    @Autowired
    private MessageCustomRepo customRepo;
    @Autowired
    private MarketingManpowerRepo manpowerRepo;
    @Value("${sendMessage}")
    private boolean sendMessage;

    @Override
    public void run() {
        if (!sendMessage) return;
        //获取需要发送的消息
        List<MessageCustom> needSendList = Lists.newArrayList();
        addCustom(needSendList, MessageType.DAILY_OUTWORK);
        addCustom(needSendList, MessageType.DAILY_RETURN);
        addCustom(needSendList, MessageType.DAILY_UP);
        //查找人员
        MarketingManpowerPageRequest pageRequest = new MarketingManpowerPageRequest();
        pageRequest.setPageSize(100);
        Pagination page;
        int pageNo = 1;
        int count = 0;
        do {
            pageRequest.setPageNo(pageNo);
            page = manpowerRepo.page(pageRequest);
            List<MarketingManpower> result = page.getResult(MarketingManpower.class);
            //处理数据
            for (MarketingManpower manpower : result) {
                for (MessageCustom custom : needSendList) {
                    if (custom.getMessageType() == MessageType.DAILY_OUTWORK) {
                        PositionsType positionsType = null;
                        try {
                            PositionsClass positionsClass = PositionsClass.valueOf(manpower.getAgentGrade());
                            positionsType = PositionsType.fromClass(positionsClass);
                        } catch (InterruptedException e) {
                            logger.error("", e);
                            return;
                        }
                        if (positionsType != PositionsType.FZG) {//离职提醒针对组经理以上人员
                            messageManager.sendTextOne(custom, manpower.getAgentCode());
                        }
                    }else{
                        messageManager.sendTextOne(custom, manpower.getAgentCode());
                    }
                }
            }

            pageNo++;
            count += page.getPageCount();
        }while (count < page.getTotalCount());
    }

    /**
     * @Name addCustom
     * @Author HuoJiaJin
     * @Description 拼入需要发送的消息
     * @Date 2021/3/22 21:20
     * @Param [needSendList, messageType]
     * @Return void
     **/
    private void addCustom(List<MessageCustom> needSendList, MessageType messageType) {
        List<MessageCustom> customs = customRepo.listByMessageType(messageType);
        if (!CollectionUtils.isEmpty(customs)){
            MessageCustom custom = customs.get(0);
            int maxDay = custom.getDeadline().getDayOfMonth();
            int minDay = custom.getDeadline().minusDays(2).getDayOfMonth();
            if (minDay <= LocalDate.now().getDayOfMonth() && maxDay >= LocalDate.now().getDayOfMonth()){
                needSendList.add(custom);
            }
        }
    }
}
