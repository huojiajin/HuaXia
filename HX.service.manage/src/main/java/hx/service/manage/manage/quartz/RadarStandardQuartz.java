package hx.service.manage.manage.quartz;

import hx.base.core.dao.dict.*;
import hx.base.core.dao.entity.Attendance;
import hx.base.core.dao.entity.ContinueRate;
import hx.base.core.dao.entity.MarketingManpower;
import hx.base.core.dao.entity.StarRating;
import hx.base.core.dao.entity.radar.RadarGrade;
import hx.base.core.dao.entity.radar.RadarStandard;
import hx.base.core.dao.repo.jpa.*;
import hx.base.core.dao.repo.jpa.radar.RadarGradeRepo;
import hx.base.core.dao.repo.jpa.radar.RadarStandardRepo;
import hx.base.core.manage.annotation.MyScheduler;
import hx.base.core.manage.tools.MyTimeTools;
import org.apache.commons.compress.utils.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @name: RadarStandardQuartz
 * @description: 生成上月各员工主管经营雷达图达成标准
 * @author: huojiajin
 * @time: 2020/7/16 15:03
 */
@Service
@MyScheduler(name = "RADAR_STANDARD", cron = "0 0 0 1 1,4,7,10 ?")
public class RadarStandardQuartz extends CommonQuartz {

    @Autowired
    private MarketingManpowerRepo manpowerRepo;
    @Autowired
    private RadarStandardRepo standardRepo;
    @Autowired
    private BusinessRepo businessRepo;
    @Autowired
    private StarRatingRepo starRatingRepo;
    @Autowired
    private ContinueRateRepo continueRateRepo;
    @Autowired
    private AttendanceRepo attendanceRepo;
    @Autowired
    private RadarGradeRepo radarGradeRepo;

    @Override
    public void run() {
        List<MarketingManpower> manpowerList = manpowerRepo.findAll();
        manpowerList = manpowerList.stream().filter(m -> m.getOutworkDate() == null).collect(Collectors.toList());
        //根据部代码区分人员
        Map<String, List<MarketingManpower>> manPowerMaps = manpowerList.parallelStream()
                .collect(Collectors.groupingBy(MarketingManpower::getDeptCode3));
        List<RadarGrade> radarGradeList = Lists.newArrayList();
        for (Map.Entry<String, List<MarketingManpower>> map : manPowerMaps.entrySet()) {
            String sectionCode = map.getKey();
            List<MarketingManpower> collect = map.getValue().stream().filter(m -> {
                if (!StringUtils.hasText(m.getAgentGrade())) return false;
                PositionsClass positionsClass = null;
                try {
                    positionsClass = PositionsClass.valueOf(m.getAgentGrade());
                } catch (Exception e) {
                    logger.error("======无此职级代码：" + m.getAgentGrade());
                }
                if (positionsClass == null) return false;
                PositionsType positionsType = null;
                try {
                    positionsType = PositionsType.fromClass(positionsClass);
                    return positionsType == PositionsType.AS || positionsType == PositionsType.BM;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return false;
            }).collect(Collectors.toList());
            if (CollectionUtils.isEmpty(collect)){
                logger.info("=======部" + sectionCode + "======未找到部经理，不进行结算");
            }else {
                RadarGrade radarGrade = null;
                try {
                    radarGrade = handle(true, sectionCode, collect.get(0));
                } catch (InterruptedException e) {
                    logger.error("", e);
                    logger.info("======组" + sectionCode + "处理失败，不进行结算");
                    continue;
                }
                radarGradeList.add(radarGrade);
                logger.info("=======部" + sectionCode + "======结算完成，评级为:" + radarGrade.getRateType().getValue() + radarGrade.getSectionType().getName());
            }
        }

        //根据组代码区分人员
        Map<String, List<MarketingManpower>> manPowerMaps2 = manpowerList.parallelStream()
                .collect(Collectors.groupingBy(MarketingManpower::getDeptCode4));
        for (Map.Entry<String, List<MarketingManpower>> map : manPowerMaps2.entrySet()) {
            String groupCode = map.getKey();
            List<MarketingManpower> collect = map.getValue().stream().filter(m -> {
                if (!StringUtils.hasText(m.getAgentGrade())) return false;
                PositionsClass positionsClass = null;
                try {
                    positionsClass = PositionsClass.valueOf(m.getAgentGrade());
                } catch (Exception e) {
                    logger.error("======无此职级代码：" + m.getAgentGrade());
                }
                if (positionsClass == null) return false;
                PositionsType positionsType = null;
                try {
                    positionsType = PositionsType.fromClass(positionsClass);
                    return positionsType == PositionsType.AS || positionsType == PositionsType.BM || positionsType == PositionsType.BC;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return false;
            }).collect(Collectors.toList());
            if (CollectionUtils.isEmpty(collect)){
                logger.info("=======组" + groupCode + "======未找到组经理，不进行结算");
            }else {
                RadarGrade radarGrade = null;
                try {
                    radarGrade = handle(false, groupCode, collect.get(0));
                } catch (InterruptedException e) {
                    logger.error("", e);
                    logger.info("======组" + groupCode + "处理失败，不进行结算");
                    continue;
                }
                radarGradeList.add(radarGrade);
                logger.info("=======部" + groupCode + "======结算完成，评级为:" + radarGrade.getRateType().getValue() + radarGrade.getSectionType().getName());
            }
        }
        radarGradeRepo.persistAll(radarGradeList);
    }

    private RadarGrade handle(boolean isSection, String deptCode, MarketingManpower manage) throws InterruptedException {
        System.out.println("======" + (isSection ? "部" : "组") + "详细信息");
        List<RadarStandard> standards = standardRepo.findAll();
        RateType rateType = null;
        Map<RadarStandardType, Map<RateType, RadarStandard>> standardMap = standards.stream()
                .filter(s -> {
                    if (isSection) {
                        return s.getSectionType() == SectionType.SECTION;
                    }else {
                        return s.getSectionType() == SectionType.GROUP;
                    }
                }).collect(Collectors.groupingBy(RadarStandard::getStandardType,
                        Collectors.toMap(RadarStandard::getRateType, Function.identity())));
        LocalDate lastMonth = LocalDate.now().minusMonths(1);
        LocalDate startDate = lastMonth.withDayOfMonth(1);
        LocalDate endDate = lastMonth.withDayOfMonth(1).plusMonths(1);
        //获取该季度第一个月份值
        int firstMonth = MyTimeTools.getQuarter(lastMonth.getMonthValue()).get(0);
        boolean hasReduce = false;
        //处理月均标保
        LocalDate avgStartDate = startDate.withMonth(firstMonth);
        Double stadpremNum = isSection ? businessRepo.sumByDeptCode3(deptCode, avgStartDate, endDate) :
                businessRepo.sumByDeptCode4(deptCode, avgStartDate, endDate);
        if (stadpremNum == null) stadpremNum = 0d;
        //月均，例： 8月组月均标保=（7月标保+8月标保）/2
        stadpremNum = new BigDecimal(stadpremNum)
                .divide(new BigDecimal(lastMonth.getMonthValue() - firstMonth + 1), 2, RoundingMode.HALF_UP).doubleValue();
        Map<RateType, RadarStandard> stadpremStandards = standardMap.get(RadarStandardType.STADPREM);
        int scopMin = 0;
        for (Map.Entry<RateType, RadarStandard> map : stadpremStandards.entrySet()) {
            int min = map.getValue().getMin();
            if (stadpremNum >= min * 10000){
                if (scopMin <= min) {
                    rateType = map.getKey();
                    scopMin = min;
                }
            }
        }
        BigDecimal stadpremNumBd = new BigDecimal(String.valueOf(stadpremNum));
        stadpremNumBd = stadpremNumBd.divide(new BigDecimal("10000"), 2, RoundingMode.HALF_UP);
        double stadpremNumDouble = stadpremNumBd.doubleValue();
        System.out.println("======" + (isSection ? "部" : "组") + "月标保为：" + stadpremNumDouble);

        //处理团队星级人力
        List<MarketingManpower> manpowers = isSection ? manpowerRepo.listByDeptCode3(deptCode) :
                manpowerRepo.listByDeptCode4(deptCode);
        List<String> agentCodes = manpowers.stream().map(MarketingManpower::getAgentCode).collect(Collectors.toList());
        List<StarRating> starRatings = starRatingRepo.listByAgentCodes(agentCodes);
        Long starPower = starRatings.stream().filter(s -> {
            Integer star = Integer.valueOf(s.getFhagentGrade().substring(2));
            return star >= 4;
        }).count();
        RadarStandard spStandard = standardMap.get(RadarStandardType.STARPOWER).get(rateType);
        if (rateType != getRateType(rateType, starPower.intValue(), spStandard, hasReduce)){
            rateType = getRateType(rateType, starPower.intValue(), spStandard, hasReduce);
            hasReduce = true;
        }
        System.out.println("======" + (isSection ? "部" : "组") + "团队星级人力为：" + starPower);

        //处理个人星级
        StarRating manageStar = starRatingRepo.findByAgentCode(manage.getAgentCode());
        Integer personStar = 0;
        if (manageStar != null) {
            personStar = Integer.valueOf(manageStar.getFhagentGrade().substring(2));
            RadarStandard psStandard = standardMap.get(RadarStandardType.PERSONSTAR).get(rateType);
            if (rateType != getRateType(rateType, personStar, psStandard, hasReduce)) {
                rateType = getRateType(rateType, personStar, psStandard, hasReduce);
                hasReduce = true;
            }
        }else{
            System.out.println("======员工" + manage.getAgentCode() + "无凤凰社评级");
        }
        System.out.println("======" + (isSection ? "部" : "组") + "个人星级人力为：" + personStar);


        //处理继续率
        List<ContinueRate> continueRates = continueRateRepo.listByAgentCodes(agentCodes);
        Double writtenPremSum = continueRates.parallelStream().mapToDouble(ContinueRate::getWrittenPrem).sum();
        Double paidPremSum = continueRates.parallelStream().mapToDouble(ContinueRate::getPaidPrem).sum();
        BigDecimal writtenPremSumBd = new BigDecimal(writtenPremSum.toString());
        BigDecimal paidPremSumBd = new BigDecimal(paidPremSum.toString());
        BigDecimal allRateBd;
        if (writtenPremSumBd.compareTo(BigDecimal.ZERO) == 0) {
            allRateBd = BigDecimal.ZERO;
        }else {
            allRateBd = paidPremSumBd.divide(writtenPremSumBd, 2, RoundingMode.HALF_UP)
                    .multiply(new BigDecimal("100"));
        }
        RadarStandard rateStandard = standardMap.get(RadarStandardType.RATE).get(rateType);
        Double allRate = allRateBd.doubleValue();
        rateType = getRateType(rateType, allRate, rateStandard, hasReduce);
        if (rateType != getRateType(rateType, allRate, rateStandard, hasReduce)){
            rateType = getRateType(rateType, allRate, rateStandard, hasReduce);
            hasReduce = true;
        }
        System.out.println("======" + (isSection ? "部" : "组") + "继续率为：" + allRate);

        //处理出勤人力
        List<Attendance> attendanceList = attendanceRepo.listByAgentCodes(agentCodes, avgStartDate, endDate);
        Map<String, List<Attendance>> agentCodeMaps = attendanceList.stream()
                .collect(Collectors.groupingBy(Attendance::getStaffCode));
        int attendanceNum = 0;
        for (List<Attendance> list : agentCodeMaps.values()) {
            if (list.size() >= 15){
                attendanceNum++;
            }
        }
        //月均，例： 8月组出勤人力=（7月人力+8月人力）/2
        attendanceNum = new BigDecimal(attendanceNum)
                .divide(new BigDecimal(lastMonth.getMonthValue() - firstMonth + 1), 0, RoundingMode.HALF_UP).intValue();
        RadarStandard attendStandard = standardMap.get(RadarStandardType.ATTENDPOWER).get(rateType);
        rateType = getRateType(rateType, attendanceNum, attendStandard, hasReduce);
        System.out.println("======" + (isSection ? "部" : "组") + "出勤人力为：" + attendanceNum);

        //处理上月评级
        RadarGrade radarGrade = new RadarGrade();
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM");
        radarGrade.setMonth(df.format(lastMonth));
        radarGrade.setSectionType(isSection ? SectionType.SECTION : SectionType.GROUP);
        radarGrade.setRateType(rateType);
        radarGrade.setCode(deptCode);
        radarGrade.setInsertTime(LocalDateTime.now());
        return radarGrade;
    }

    private RateType getRateType(RateType type, double num, RadarStandard standard, boolean hasReduce) throws InterruptedException {
        if (hasReduce) return type;
        if (standard.getMin() >= num){
            int typeCode = type.getCode() - 1;
            return type = typeCode > 0 ? RateType.fromCode(typeCode) : RateType.LAGGINGBEHIND;
        }
        return type;
    }
}
