package hx.service.manage.manage.quartz;

import hx.base.core.dao.dict.acl.PositionsClass;
import hx.base.core.dao.entity.hualife.RankPromotionTrack;
import hx.base.core.dao.repo.jpa.hualife.RankPromotionTrackRepo;
import hx.base.core.manage.annotation.MyScheduler;
import hx.service.manage.manage.message.MessageManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.LocalDate;
import java.util.List;

/**
 * @ClassName: RankPromotionQuartz
 * @Description: 晋升通知定时处理
 * @Author HuoJiaJin
 * @Date 2021/3/22 21:41
 * @Version 1.0
 **/
@Service
@MyScheduler(name = "PROMOTION_MESSAGE", cron = "0 40 9 * * ?")
public class PromotionMessageQuartz extends CommonQuartz{

    @Autowired
    private RankPromotionTrackRepo trackRepo;
    @Autowired
    private MessageManager messageManager;
    @Value("${sendMessage}")
    private boolean sendMessage;

    @Override
    public void run() {
        if (!sendMessage) return;
        List<RankPromotionTrack> trackList = trackRepo.listByStartDate(LocalDate.now().minusDays(1).atStartOfDay());
        if (!CollectionUtils.isEmpty(trackList)){
            for (RankPromotionTrack track : trackList) {
                PositionsClass positionsClass = PositionsClass.valueOf(track.getAgentGrade());
                String text = "恭喜您晋升为" + positionsClass.getValue();
                messageManager.sendTextOri(text, track.getAgentCode());
            }
        }
    }
}
