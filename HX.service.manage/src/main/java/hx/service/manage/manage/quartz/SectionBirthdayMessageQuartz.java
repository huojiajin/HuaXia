package hx.service.manage.manage.quartz;

import hx.base.core.dao.dict.acl.PositionsClass;
import hx.base.core.dao.dict.acl.PositionsType;
import hx.base.core.dao.entity.hualife.MarketingManpower;
import hx.base.core.dao.repo.jpa.hualife.MarketingManpowerRepo;
import hx.base.core.manage.annotation.MyScheduler;
import hx.service.manage.manage.message.MessageManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: SectionBirthdayMessageQuartz
 * @Description: 每月初，将直辖部内当月生日的组员名单推送至部经理微信
 * @Author HuoJiaJin
 * @Date 2021/3/24 2:40
 * @Version 1.0
 **/
@Service
@MyScheduler(name = "ANNIVERS_MESSAGE", cron = "0 40 9 1 * ?")
public class SectionBirthdayMessageQuartz extends CommonQuartz{

    @Autowired
    private MarketingManpowerRepo manpowerRepo;
    @Autowired
    private MessageManager messageManager;
    @Value("${sendMessage}")
    private boolean sendMessage;

    @Override
    public void run() {
        if (!sendMessage) return;
        LocalDate now = LocalDate.now();
        List<Map<String, String>> maps = manpowerRepo.groupBySectionAll();
        for (Map<String, String> map : maps) {
            String sectionCode = map.get("sectionCode");
            List<MarketingManpower> manpowerList = manpowerRepo.listByDeptCode3(sectionCode);
            String text = "您好，本月您部内有以下人员生日:\n";
            MarketingManpower executive = null;
            for (MarketingManpower manpower : manpowerList) {
                //解析职级,确定主管
                PositionsType positionsType;
                try {
                    PositionsClass positionsClass = PositionsClass.valueOf(manpower.getAgentGrade());
                    positionsType = PositionsType.fromClass(positionsClass);
                } catch (Exception e) {
                    logger.error("", e);
                    continue;
                }
                if (positionsType == PositionsType.AS || positionsType == PositionsType.BM){
                    executive = manpower;
                }
                //拼装生日人员信息
                LocalDate birthday = manpower.getBirthday();
                if (birthday.getMonthValue() == now.getMonthValue() && birthday.getDayOfMonth() == now.getDayOfMonth()){
                    text += manpower.getName() + "\n";
                }
            }
            if (executive != null) {
                messageManager.sendTextOri(text, executive.getAgentCode());
            }else {
                logger.error("{}未找到主管人员", map.get("sectionName"));
            }
        }
    }
}
