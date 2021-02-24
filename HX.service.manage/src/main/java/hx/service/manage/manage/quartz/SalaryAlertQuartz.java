package hx.service.manage.manage.quartz;

import com.google.common.collect.Maps;
import hx.base.core.dao.dict.acl.PositionsClass;
import hx.base.core.dao.dict.salary.PrizeInfluenceType;
import hx.base.core.dao.dict.salary.PrizeType;
import hx.base.core.dao.entity.hualife.ContinueRate;
import hx.base.core.dao.entity.hualife.MarketingManpower;
import hx.base.core.dao.entity.salary.SalaryAlert;
import hx.base.core.dao.entity.salary.SalaryAlertInfluence;
import hx.base.core.dao.repo.jpa.hualife.AttendanceRepo;
import hx.base.core.dao.repo.jpa.hualife.BusinessRepo;
import hx.base.core.dao.repo.jpa.hualife.ContinueRateRepo;
import hx.base.core.dao.repo.jpa.hualife.MarketingManpowerRepo;
import hx.base.core.dao.repo.jpa.salary.SalaryAlertInfluenceRepo;
import hx.base.core.dao.repo.jpa.salary.SalaryAlertRepo;
import hx.base.core.dao.repo.request.common.Pagination;
import hx.base.core.dao.repo.request.hualife.MarketingManpowerPageRequest;
import hx.base.core.manage.annotation.MyScheduler;
import hx.base.core.manage.tools.MyTimeTools;
import org.apache.commons.compress.utils.Lists;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: SalaryAlertQuartz
 * @Description: 计算上月薪资预警信息
 * @Author HuoJiaJin
 * @Date 2021/2/21 23:41
 * @Version 1.0
 **/
@Service
@MyScheduler(name = "SALARY_ALERT", cron = "0 0 1 1 * ?")
public class SalaryAlertQuartz extends CommonQuartz{

    @Autowired
    private MarketingManpowerRepo manpowerRepo;
    @Autowired
    private BusinessRepo businessRepo;
    @Autowired
    private AttendanceRepo attendanceRepo;
    @Autowired
    private ContinueRateRepo rateRepo;
    @Autowired
    private SalaryAlertRepo alertRepo;
    @Autowired
    private SalaryAlertInfluenceRepo influenceRepo;


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void run() {
        LocalDate now = LocalDate.now();
        deal(now);
    }

    /**
     * @Name deal
     * @Author HuoJiaJin
     * @Description 根据传入时间分页处理
     * @Date 2021/2/22 23:47
     * @Param [now]
     * @Return void
     **/
    public void deal(LocalDate now) {
        //初始化数据
        int pageIndex = 1;
        int count = 0;
        int dealCount = 0;
        LocalDate startDate = now.minusMonths(1).withDayOfMonth(1);
        LocalDate endDate = startDate.plusMonths(1);
        //处理上个月每个人的数据
        MarketingManpowerPageRequest pageRequest = new MarketingManpowerPageRequest();
        pageRequest.setPageSize(100);
        Pagination page;
        do {
            page = manpowerRepo.page(pageRequest);
            //处理具体过程
            dealCount += manpowerDeal(page.getResult(MarketingManpower.class), startDate, endDate);
            count += page.getResult().size();
            if (count % 1000 == 0){
                logger.info("======已处理" + count + "条人员数据，其中具有薪资项目的有" + dealCount + "人");
            }
            pageIndex ++;
            pageRequest.setPageNo(pageIndex);
        }while(page.getCurrentPage() < page.getPageCount());
        logger.info("======共处理" + count + "条人员数据，其中具有薪资项目的有" + dealCount + "人");
    }

    /**
     * @Name manpowerDeal
     * @Author HuoJiaJin
     * @Description 处理具体数据
     * @Date 2021/2/22 2:20
     * @Param [manpowerList, startDate, endDate]
     * @Return int
     **/
    private int manpowerDeal(List<MarketingManpower> manpowerList, LocalDate startDate, LocalDate endDate) {
        //处理数据
        int count = 0;
        List<SalaryAlert> alertList = Lists.newArrayList();
        List<SalaryAlertInfluence> influenceList = Lists.newArrayList();
        for (MarketingManpower manpower : manpowerList) {
            //处理异常错误
            if (!StringUtils.hasText(manpower.getAgentGrade())) continue;
            PositionsClass positionsClass = null;
            try {
                positionsClass = PositionsClass.valueOf(manpower.getAgentGrade());
            } catch (IllegalArgumentException e) {
                logger.error("======人员" + manpower.getName() + manpower.getAgentCode() + "的职级" + manpower.getAgentGrade() + "不存在");
                continue;
            }
            //如果计算月份不是6月或12月，则不计算个人半年奖，即非主管人员不处理
            if ((startDate.getMonthValue() != 6 && startDate.getMonthValue() != 12) && PositionsClass.isNotExecutive(positionsClass)) continue;
            //是否已有卓越部成就奖
            boolean hasExcellent = false;
            for (PrizeType prizeType : positionsClass.getPrizeTypes()) {
                try {
                    if (hasExcellent && prizeType == PrizeType.EXCELLENTGROUP) continue;//卓越组成就奖与卓越部成就奖不可兼得，就高发放。
                    //获取影响因素数据
                    Map<PrizeInfluenceType, Double> influenceMaps = getInfluencMap(startDate, endDate, manpower, prizeType);
                    //计算薪资项目
                    Double salary = prizeType.calculateSalary(influenceMaps, positionsClass);
                    //获得差距
                    Double gap = prizeType.getGap(influenceMaps);
                    //获得发放比例
                    Double allocations = prizeType.getAllocations(influenceMaps.get(PrizeInfluenceType.CONTINUERATE));
                    //拼装实体
                    SalaryAlert alert = new SalaryAlert();
                    alert.setMonth(startDate);
                    alert.setDirectorName(manpower.getDeptName2());
                    alert.setDirectorCode(manpower.getDeptCode2());
                    alert.setSectionName(manpower.getDeptName3());
                    alert.setSectionCode(manpower.getDeptCode3());
                    alert.setGroupName(manpower.getDeptName4());
                    alert.setGroupCode(manpower.getDeptCode4());
                    alert.setName(manpower.getName());
                    alert.setEmployeeCode(manpower.getAgentCode());
                    alert.setType(prizeType);
                    alert.setSalary(salary);
                    alert.setGap(gap == null ? 0 : gap);
                    if (allocations == null || allocations == 1){
                        alert.setFullPayment(true);
                        alert.setAllocations(1d);
                    }else{
                        alert.setFullPayment(false);
                        alert.setAllocations(allocations);
                    }
                    alertList.add(alert);
                    //卓越组成就奖与卓越部成就奖不可兼得，就高发放。
                    if (prizeType == PrizeType.EXCELLENTSECTION){
                        hasExcellent = true;
                    }
                    //拼装影响因素实体
                    for (Map.Entry<PrizeInfluenceType, Double> map : influenceMaps.entrySet()) {
                        SalaryAlertInfluence influence = new SalaryAlertInfluence();
                        influence.setAlertId(alert.getId());
                        influence.setType(map.getKey());
                        influence.setNum(map.getValue());
                        influenceList.add(influence);
                    }
                    logger.info("======人员" + manpower.getName() + manpower.getAgentCode() + "获得" + prizeType.getValue() + ",具体薪资为" + salary + "元");
                    count++;
                } catch (InterruptedException e) {
                    logger.error("======人员" + manpower.getName() + manpower.getAgentCode() + "无法评选" + prizeType.getValue() + "，具体原因为" + e.getMessage());
                    continue;
                }
            }

            //1月、7月时如果继续率成功补发前几个月达标数据
            if (startDate.getMonthValue() == 6 || startDate.getMonthValue() == 12){
                for (PrizeType completeType : PrizeType.getCompleteTypes()) {
                    BigDecimal continueRateBD = getContinueRate(startDate, manpower, completeType);
                    Double allocations = completeType.getAllocations(continueRateBD.doubleValue());
                    if (allocations == 1){//继续率达标才能补足
                        List<SalaryAlert> completeList = alertRepo.listNeedComplete(manpower.getAgentCode(), completeType, endDate.withMonth(1), endDate);
                        for (SalaryAlert alert : completeList) {
                            //拼装补发实体
                            SalaryAlert completeAlert = new SalaryAlert();
                            BeanUtils.copyProperties(alert, completeAlert, new String[]{"id"});
                            BigDecimal salaryBd = new BigDecimal(String.valueOf(alert.getSalary()))
                                    .divide(new BigDecimal(String.valueOf(alert.getAllocations())))
                                    .subtract(new BigDecimal(String.valueOf(alert.getSalary())))
                                    .setScale(2, BigDecimal.ROUND_HALF_UP);
                            completeAlert.setSalary(salaryBd.doubleValue());
                            completeAlert.setGap(0d);
                            completeAlert.setFullPayment(true);
                            completeAlert.setAllocations(1d);
                            completeAlert.setCompleteAlertId(alert.getId());
                            completeAlert.setRemark("补发" + MyTimeTools.dateToStr(alert.getMonth(), "yyyy年yy月") + alert.getType().getValue() + "薪资");
                            alertList.add(completeAlert);
                            //更新原薪资项目，补发完成
                            alertRepo.updateComplete(alert.getId());
                        }
                    }
                }
            }
        }
        //保存实体
        alertRepo.persistAll(alertList);
        influenceRepo.persistAll(influenceList);
        return count;
    }

    /**
     * @Name getInfluencMap
     * @Author HuoJiaJin
     * @Description 获得影响因素相关数据
     * @Date 2021/2/22 2:00
     * @Param [startDate, endDate, manpower, prizeType]
     * @Return java.util.Map<hx.base.core.dao.dict.salary.PrizeInfluenceType,java.lang.Double>
     **/
    private Map<PrizeInfluenceType, Double> getInfluencMap(LocalDate startDate, LocalDate endDate, MarketingManpower manpower, PrizeType prizeType) {
        Map<PrizeInfluenceType, Double> influenceMaps = Maps.newHashMap();
        for (PrizeInfluenceType influenceType : prizeType.getInfluenceTypes()) {
            if (influenceType == PrizeInfluenceType.FYC){//计算FYC
                Double FYC = getFyc(startDate, endDate, manpower, prizeType);
                influenceMaps.put(PrizeInfluenceType.FYC, FYC);
            }else if (influenceType == PrizeInfluenceType.CONTINUERATE){//计算继续率13J
                BigDecimal rate = getContinueRate(startDate, manpower, prizeType);
                influenceMaps.put(PrizeInfluenceType.CONTINUERATE, rate.doubleValue());
            }else if (influenceType == PrizeInfluenceType.ATTENDANCE){//计算出勤天数
                Double attendance = getAttendance(startDate, endDate, manpower, prizeType);
                influenceMaps.put(PrizeInfluenceType.ATTENDANCE, attendance);
            }else if (influenceType == PrizeInfluenceType.HEALTH){//计算健康人力
                Double health = getHealth(startDate, endDate, manpower, prizeType);
                influenceMaps.put(PrizeInfluenceType.HEALTH, health);
            }
        }
        return influenceMaps;
    }

    /**
     * @Name getFyc
     * @Author HuoJiaJin
     * @Description 获取FYC
     * @Date 2021/2/22 3:15
     * @Param [startDate, endDate, manpower, prizeType]
     * @Return java.lang.Double
     **/
    private Double getFyc(LocalDate startDate, LocalDate endDate, MarketingManpower manpower, PrizeType prizeType) {
        Double FYC = 0d;
        switch (prizeType.getGroupType()){
            case CAMP:
                FYC = businessRepo.sumFYCByDeptCode2(manpower.getDeptCode2(), startDate, endDate);
                break;
            case SECTION:
                FYC = businessRepo.sumFYCByDeptCode3(manpower.getDeptCode3(), startDate, endDate);
                break;
            case GROUP:
                FYC = businessRepo.sumFYCByDeptCode4(manpower.getDeptCode4(), startDate, endDate);
                break;
            case PERSON:
                if (startDate.getMonthValue() == 6 || startDate.getMonthValue() == 12) {
                    long betweenMonth = ChronoUnit.MONTHS.between(manpower.getEmployDate(), LocalDate.now());
                    if (betweenMonth >= 4) {//获取时自然半年需入司满4个月
                        if (startDate.getMonthValue() == 6) {
                            LocalDate personStartDate = startDate.withMonth(1);
                            FYC = businessRepo.sumFYCByAgentCode(manpower.getAgentCode(), personStartDate, endDate);
                        } else if (startDate.getMonthValue() == 12) {
                            LocalDate personStartDate = startDate.withMonth(7);
                            FYC = businessRepo.sumFYCByAgentCode(manpower.getAgentCode(), personStartDate, endDate);
                        }
                    }
                }
                break;
        }
        return FYC;
    }

    /**
     * @Name getContinueRate
     * @Author HuoJiaJin
     * @Description 获取13J继续率
     * @Date 2021/2/22 3:15
     * @Param [startDate, manpower, prizeType]
     * @Return java.math.BigDecimal
     **/
    private BigDecimal getContinueRate(LocalDate startDate, MarketingManpower manpower, PrizeType prizeType) {
        BigDecimal rate = new BigDecimal("0");
        switch (prizeType.getGroupType()){
            case CAMP:
                //实收保费
                Double paidPrem2 = rateRepo.sumPaidByDeptCode2(manpower.getDeptCode2(), MyTimeTools.dateToStr(startDate, "yyyyMM"));
                //应收保费
                Double writtenPrem2 = rateRepo.sumByDeptCode2(manpower.getDeptCode2(), MyTimeTools.dateToStr(startDate, "yyyyMM"));
                if (writtenPrem2 == null || writtenPrem2 == 0) {
                    rate = new BigDecimal("0");
                }else {
                    rate = new BigDecimal(String.valueOf(paidPrem2)).divide(new BigDecimal(String.valueOf(writtenPrem2)), 4, BigDecimal.ROUND_HALF_UP);
                }
                break;
            case SECTION:
                //实收保费
                Double paidPrem3 = rateRepo.sumPaidByDeptCode3(manpower.getDeptCode3(), MyTimeTools.dateToStr(startDate, "yyyyMM"));
                //应收保费
                Double writtenPrem3 = rateRepo.sumByDeptCode3(manpower.getDeptCode3(), MyTimeTools.dateToStr(startDate, "yyyyMM"));
                if (writtenPrem3 == null || writtenPrem3 == 0) {
                    rate = new BigDecimal("0");
                }else {
                    rate = new BigDecimal(String.valueOf(paidPrem3)).divide(new BigDecimal(String.valueOf(writtenPrem3)), 4, BigDecimal.ROUND_HALF_UP);
                }
                break;
            case GROUP:
                //实收保费
                Double paidPrem4 = rateRepo.sumPaidByDeptCode4(manpower.getDeptCode4(), MyTimeTools.dateToStr(startDate, "yyyyMM"));
                //应收保费
                Double writtenPrem4 = rateRepo.sumByDeptCode4(manpower.getDeptCode4(), MyTimeTools.dateToStr(startDate, "yyyyMM"));
                if (writtenPrem4 == null || writtenPrem4 == 0) {
                    rate = new BigDecimal("0");
                }else {
                    rate = new BigDecimal(String.valueOf(paidPrem4)).divide(new BigDecimal(String.valueOf(writtenPrem4)), 4, BigDecimal.ROUND_HALF_UP);
                }
                break;
            case PERSON:
                if (startDate.getMonthValue() == 6 || startDate.getMonthValue() == 12) {
                    long betweenMonth = ChronoUnit.MONTHS.between(manpower.getEmployDate(), LocalDate.now());
                    if (betweenMonth >= 4) {//获取时自然半年需入司满4个月
                        ContinueRate continueRate = rateRepo.findByAgentCode(manpower.getAgentCode(), MyTimeTools.dateToStr(startDate, "yyyyMM"));
                        if (continueRate == null) {
                            break;
                        }
                        Double writtenPrem = continueRate.getWrittenPrem();
                        if (writtenPrem == null || writtenPrem == 0) {
                            rate = new BigDecimal("0");
                        } else {
                            rate = new BigDecimal(String.valueOf(continueRate.getPaidPrem())).divide(new BigDecimal(String.valueOf(writtenPrem)), 4, BigDecimal.ROUND_HALF_UP);
                        }
                    }
                }
                break;
        }
        return rate;
    }

    /**
     * @Name getAttendance
     * @Author HuoJiaJin
     * @Description 获取出勤天数
     * @Date 2021/2/22 3:15
     * @Param [startDate, endDate, manpower, prizeType]
     * @Return java.lang.Double
     **/
    private Double getAttendance(LocalDate startDate, LocalDate endDate, MarketingManpower manpower, PrizeType prizeType) {
        Double attendance = 0d;
        switch (prizeType.getGroupType()){
            case GROUP:
                Long attendanceNum = attendanceRepo.countByAgentCode(manpower.getAgentCode(), startDate, endDate);
                if (attendanceNum != null){
                    attendance = attendanceNum.doubleValue();
                }
                break;
            case PERSON:
                if (startDate.getMonthValue() == 6 || startDate.getMonthValue() == 12) {
                    long betweenMonth = ChronoUnit.MONTHS.between(manpower.getEmployDate(), endDate);
                    if (betweenMonth >= 4) {//获取时自然半年需入司满4个月
                        LocalDate personStartDate = endDate.minusMonths(betweenMonth);
                        Long personAttendanceNum = attendanceRepo.countByAgentCode(manpower.getAgentCode(), personStartDate, endDate);
                        personAttendanceNum = personAttendanceNum == null ? 0 : personAttendanceNum;
                        BigDecimal attendanceBd = new BigDecimal(personAttendanceNum).divide(new BigDecimal("4"), 2, BigDecimal.ROUND_HALF_UP);
                        if (attendanceBd.compareTo(new BigDecimal("10")) >= 0) {//月均有效出勤不低于10天
                            attendance = attendanceBd.doubleValue();
                        } else {
                            logger.info("员工" + manpower.getName() + manpower.getAgentCode() + "月均有效出勤低于10天");
                        }
                    } else {
                        logger.info("员工" + manpower.getName() + manpower.getAgentCode() + "入职未满4个月");
                    }
                }
                break;
        }
        return attendance;
    }

    /**
     * @Name getHealth
     * @Author HuoJiaJin
     * @Description 获取健康人力
     * @Date 2021/2/22 3:14
     * @Param [startDate, endDate, manpower, prizeType]
     * @Return java.lang.Double
     **/
    private Double getHealth(LocalDate startDate, LocalDate endDate, MarketingManpower manpower, PrizeType prizeType) {
        Double health = 0d;
        switch (prizeType.getGroupType()){
            case CAMP:
                List<Object[]> healthByDeptCode2 = businessRepo.healthByDeptCode2(manpower.getDeptCode2(), startDate, endDate);
                health = Double.valueOf(String.valueOf(healthByDeptCode2.size()));
                break;
            case SECTION:
                List<Object[]> healthByDeptCode3 = businessRepo.healthByDeptCode3(manpower.getDeptCode2(), startDate, endDate);
                health = Double.valueOf(String.valueOf(healthByDeptCode3.size()));
                break;
            case GROUP:
                List<Object[]> healthByDeptCode4 = businessRepo.healthByDeptCode4(manpower.getDeptCode2(), startDate, endDate);
                health = Double.valueOf(String.valueOf(healthByDeptCode4.size()));
                break;
        }
        return health;
    }
}
