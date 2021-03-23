package hx.service.manage.manage.quartz;

import hx.base.core.dao.dict.message.MessageType;
import hx.base.core.dao.entity.hualife.MarketingManpower;
import hx.base.core.dao.entity.message.MessageCustom;
import hx.base.core.dao.repo.jpa.hualife.MarketingManpowerRepo;
import hx.base.core.dao.repo.jpa.message.MessageCustomRepo;
import hx.base.core.dao.repo.request.common.Pagination;
import hx.base.core.dao.repo.request.hualife.MarketingManpowerPageRequest;
import hx.base.core.manage.annotation.MyScheduler;
import hx.service.manage.manage.message.MessageManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @ClassName: AnniversMessageQuartz
 * @Description: 入职周年及生日消息通知
 * @Author HuoJiaJin
 * @Date 2021/3/22 21:27
 * @Version 1.0
 **/
@Service
@MyScheduler(name = "ANNIVERS_MESSAGE", cron = "0 30 9 * * ?")
public class AnniversMessageQuartz extends CommonQuartz{

    @Autowired
    private MarketingManpowerRepo manpowerRepo;
    @Autowired
    private MessageManager messageManager;
    @Autowired
    private MessageCustomRepo customRepo;
    @Value("${sendMessage}")
    private boolean sendMessage;

    @Override
    public void run() {
        if (!sendMessage) return;
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
            deal(result);
            pageNo++;
            count += page.getPageCount();
        }while (count < page.getTotalCount());
    }

    /**
     * @Name deal
     * @Author HuoJiaJin
     * @Description 实际处理
     * @Date 2021/3/22 21:38
     * @Param [manpowerList]
     * @Return void
     **/
    private void deal(List<MarketingManpower> manpowerList){
        //生日消息
        LocalDate now = LocalDate.now();
        List<MarketingManpower> birthdayList = manpowerList.stream()
                .filter(m -> m.getBirthday().getMonthValue() == now.getMonthValue() && m.getBirthday().getDayOfMonth() == now.getDayOfMonth())
                .collect(Collectors.toList());
        sendMessage(birthdayList, MessageType.BIRTHDAY);

        //周年消息
        List<MarketingManpower> anniversList = manpowerList.stream()
                .filter(m -> m.getEmployDate().getMonthValue() == now.getMonthValue() && m.getEmployDate().getDayOfMonth() == now.getDayOfMonth())
                .collect(Collectors.toList());
        sendMessage(anniversList, MessageType.ANNIVERSARY);
    }

    /**
     * @Name sendMessage
     * @Author HuoJiaJin
     * @Description 发送消息
     * @Date 2021/3/22 21:38
     * @Param [manpowerList, messageType]
     * @Return void
     **/
    private void sendMessage(List<MarketingManpower> manpowerList, MessageType messageType) {
        List<MessageCustom> customList = customRepo.listByMessageType(messageType);
        BigDecimal bigDecimal = new BigDecimal(Math.random() * customList.size()).setScale(0, BigDecimal.ROUND_HALF_UP);
        MessageCustom custom = customList.get(bigDecimal.intValue() - 1);
        List<String> agentCodes = manpowerList.stream().map(MarketingManpower::getAgentCode).collect(Collectors.toList());
        messageManager.sendText(custom, agentCodes);
    }
}
