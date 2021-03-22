package hx.service.manage.manage.quartz;

import hx.base.core.dao.dict.black.BlackListType;
import hx.base.core.dao.entity.black.BlackList;
import hx.base.core.dao.entity.hualife.ContinueRate;
import hx.base.core.dao.entity.hualife.MarketingManpower;
import hx.base.core.dao.repo.jpa.black.BlackListRepo;
import hx.base.core.dao.repo.jpa.hualife.AttendanceRepo;
import hx.base.core.dao.repo.jpa.hualife.BusinessRepo;
import hx.base.core.dao.repo.jpa.hualife.ContinueRateRepo;
import hx.base.core.dao.repo.jpa.hualife.MarketingManpowerRepo;
import hx.base.core.dao.repo.request.common.Pagination;
import hx.base.core.dao.repo.request.hualife.MarketingManpowerPageRequest;
import hx.base.core.manage.annotation.MyScheduler;
import hx.base.core.manage.tools.MyTimeTools;
import org.apache.commons.compress.utils.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

/**
 * @name: BlackListQuartz
 * @description: 黑名单定时处理
 * @author: huojiajin
 * @time: 2021/3/22 15:19
 */
@Service
@MyScheduler(name = "BLACK_LIST", cron = "0 0 4 1 * ?")
public class BlackListQuartz extends CommonQuartz{

    @Autowired
    private MarketingManpowerRepo manpowerRepo;
    @Autowired
    private BusinessRepo businessRepo;
    @Autowired
    private AttendanceRepo attendanceRepo;
    @Autowired
    private ContinueRateRepo rateRepo;
    @Autowired
    private BlackListRepo blackListRepo;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void run() {
        //删除之前的数据
        blackListRepo.deleteAll();
        //添加新的数据
        List<BlackList> blackLists = Lists.newArrayList();
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
            deal(result, blackLists);
            pageNo++;
            count += page.getPageCount();
        }while (count < page.getTotalCount());
        //保存黑名单数据
        blackListRepo.persistAll(blackLists);
    }

    private void deal(List<MarketingManpower> manpowerList, List<BlackList> blackLists){
        for (BlackListType type : BlackListType.values()) {
            switch (type) {
                case FYC:
                    dealFYC(manpowerList, blackLists);
                    break;
                case ATTENDANCE:
                    dealAttendance(manpowerList, blackLists);
                    break;
                case CONTINUE:
                    dealContinue(manpowerList, blackLists);
                    break;
            }
        }
    }

    /**
     * @Name dealFYC
     * @Author HuoJiaJin
     * @Description 处理FYC不达标人员
     * @Date 2021/3/22 16:59
     * @Param [manpowerList, blackLists]
     * @Return void
     **/
    private void dealFYC(List<MarketingManpower> manpowerList, List<BlackList> blackLists){
        LocalDate now = LocalDate.now();
//        if (now.getMonthValue() < 7){
//            return;
//        }
        LocalDate startTime = now.minusMonths(6);
        for (MarketingManpower manpower : manpowerList) {
            Double FYC = businessRepo.sumFYCByAgentCode(manpower.getAgentCode(), startTime, now);
            if (FYC == null || FYC == 0d){
                BlackList blackList = assembleEntity(manpower);
                blackLists.add(blackList);
            }
        }
    }

    /**
     * @Name dealAttendance
     * @Author HuoJiaJin
     * @Description 处理出勤不达标人员
     * @Date 2021/3/22 17:03
     * @Param [manpowerList, blackLists]
     * @Return void
     **/
    private void dealAttendance(List<MarketingManpower> manpowerList, List<BlackList> blackLists){
        LocalDate now = LocalDate.now();
//        if (now.getMonthValue() < 4){
//            return;
//        }
        LocalDate startTime = now.minusMonths(3);
        for (MarketingManpower manpower : manpowerList) {
            Long attendanceNum = attendanceRepo.countByAgentCode(manpower.getAgentCode(), startTime, now);
            if (attendanceNum == null || attendanceNum == 0l){
                BlackList blackList = assembleEntity(manpower);
                blackLists.add(blackList);
            }
        }
    }

    /**
     * @Name dealAttendance
     * @Author HuoJiaJin
     * @Description 处理出勤不达标人员
     * @Date 2021/3/22 17:03
     * @Param [manpowerList, blackLists]
     * @Return void
     **/
    private void dealContinue(List<MarketingManpower> manpowerList, List<BlackList> blackLists){
        LocalDate now = LocalDate.now();
        for (MarketingManpower manpower : manpowerList) {
            ContinueRate continueRate = rateRepo.findByAgentCode(manpower.getAgentCode(), MyTimeTools.dateToStr(now.minusMonths(1), "yyyyMM"));
            if (continueRate == null || continueRate.getAllRate() < 0.8){
                BlackList blackList = assembleEntity(manpower);
                blackLists.add(blackList);
            }
        }
    }

    /**
     * @Name assembleEntity
     * @Author HuoJiaJin
     * @Description 组装黑名单实体
     * @Date 2021/3/22 16:58
     * @Param [manpower]
     * @Return hx.base.core.dao.entity.black.BlackList
     **/
    private BlackList assembleEntity(MarketingManpower manpower) {
        BlackList blackList = new BlackList();
        blackList.setName(manpower.getName());
        blackList.setAgentCode(manpower.getAgentCode());
        blackList.setType(BlackListType.FYC);
        blackList.setSectionName(manpower.getDeptName3());
        blackList.setSectionCode(manpower.getDeptCode3());
        blackList.setGroupName(manpower.getDeptName4());
        blackList.setGroupCode(manpower.getDeptCode4());
        return blackList;
    }
}
