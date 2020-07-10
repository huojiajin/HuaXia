package hx.service.mobile.manage.radar;

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
import hx.base.core.manage.model.CommonResponse;
import hx.base.core.manage.tools.MyTimeTools;
import hx.service.mobile.manage.AbstractMobileManager;
import hx.service.mobile.manage.model.common.MobileCommonRequest;
import hx.service.mobile.manage.model.login.MobileUserModel;
import hx.service.mobile.manage.model.radar.RadarRequest;
import hx.service.mobile.manage.model.radar.RadarResponse;
import hx.service.mobile.manage.model.radar.StadpremMonthModel;
import hx.service.mobile.manage.model.radar.StarOneselfResponse;
import org.apache.commons.compress.utils.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @ClassName RadarManagerImpl
 * @Description 主管经营雷达图相关ManagerImpl
 * @Author HuoJiaJin
 * @Date 2020/6/26 23:48
 * @Version 1.0
 **/
@Service
public class RadarManagerImpl extends AbstractMobileManager implements RadarManager {

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
    public String radar(RadarRequest request){
        CommonResponse response = new CommonResponse();
        RadarResponse data;
        MobileUserModel user = getUser(request.getToken());
        if (user == null) return response.setError(ErrorType.NOLOGIN);
        try {
            if (request.getGroupCode().equals("0")){//查询部相关
                data = handle(user, true, request.getSectionCode());
            }else {
                data = handle(user, false, request.getGroupCode());
            }
        } catch (Exception e) {
            logger.error("", e);
            return response.setError(ErrorType.CONVERT);
        }
        response.setData(data);
        return response.toJson();
    }

    private RadarResponse handle(MobileUserModel user, boolean isSection, String deptCode) throws InterruptedException {
        RadarResponse data = new RadarResponse();
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
        LocalDate now = LocalDate.now();
        LocalDate startDate = now.withDayOfMonth(1);
        LocalDate endDate = now.withDayOfMonth(1).plusMonths(1);
        boolean hasReduce = false;
        //处理月均标保
        Double stadpremNum = isSection ? businessRepo.sumByDeptCode3(deptCode, startDate, endDate) :
                businessRepo.sumByDeptCode4(deptCode, startDate, endDate);
        if (stadpremNum == null) stadpremNum = 0d;
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
        data.setStadpremNum(stadpremNumDouble);

        //处理个人星级
        Integer personStar = Integer.valueOf(user.getFhagent_grade().substring(2));
        RadarStandard psStandard = standardMap.get(RadarStandardType.PERSONSTAR).get(rateType);
        if (rateType != getRateType(rateType, personStar, psStandard, hasReduce)){
            rateType = getRateType(rateType, personStar, psStandard, hasReduce);
            hasReduce = true;
        }

        data.setPersonStarNum(personStar);

        //处理团队星级人力
        List<MarketingManpower> manpowers = isSection ? manpowerRepo.listByDeptCode3(deptCode) :
                manpowerRepo.listByDeptCode4(deptCode);
        List<String> agentCodes = manpowers.stream().map(MarketingManpower::getAgentCode).collect(Collectors.toList());
        List<StarRating> starRatings = starRatingRepo.listByAgentCodes(agentCodes);
        Long starPower = starRatings.stream().filter(s -> {
            Integer star = Integer.valueOf(s.getFhagentGrade().substring(2));
            return star >= 4;
        }).count();
        data.setStarPowerNum(starPower.intValue());
        RadarStandard spStandard = standardMap.get(RadarStandardType.STARPOWER).get(rateType);
        if (rateType != getRateType(rateType, starPower.intValue(), spStandard, hasReduce)){
            rateType = getRateType(rateType, starPower.intValue(), spStandard, hasReduce);
            hasReduce = true;
        }

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
        data.setRate(allRateBd.doubleValue());
        RadarStandard rateStandard = standardMap.get(RadarStandardType.RATE).get(rateType);
        Double allRate = allRateBd.doubleValue();
        rateType = getRateType(rateType, allRate, rateStandard, hasReduce);
        if (rateType != getRateType(rateType, allRate, rateStandard, hasReduce)){
            rateType = getRateType(rateType, allRate, rateStandard, hasReduce);
            hasReduce = true;
        }

        //处理出勤人力
        List<Attendance> attendanceList = attendanceRepo.listByAgentCodes(agentCodes, startDate, endDate);
        Map<String, List<Attendance>> agentCodeMaps = attendanceList.stream()
                .collect(Collectors.groupingBy(Attendance::getStaffCode));
        int attendanceNum = 0;
        for (List<Attendance> list : agentCodeMaps.values()) {
            if (list.size() >= 15){
                attendanceNum++;
            }
        }
        data.setAttendPowerNum(attendanceNum);
        RadarStandard attendStandard = standardMap.get(RadarStandardType.ATTENDPOWER).get(rateType);
        rateType = getRateType(rateType, attendanceNum, attendStandard, hasReduce);

        //最后拼装数据
        data.setGrade(rateType.getValue() + SectionType.SECTION.getName());

        //下一个等级
        RateType newRateType = RateType.fromCode(rateType.getCode() + 1);
        //上月达到等级
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM");
        String month = df.format(now);
        RadarGrade lastRadarGrade;
        RateType lastRateType;
        if (isSection){//获取部评级
            lastRadarGrade = radarGradeRepo.findByCode(deptCode, month, SectionType.SECTION);
        }else {//获取组评级
            lastRadarGrade = radarGradeRepo.findByCode(deptCode, month, SectionType.GROUP);
        }
        if (lastRadarGrade != null) {
            lastRateType = lastRadarGrade.getRateType();
        }else {
            lastRateType = RateType.LAGGINGBEHIND;
        }

        //月均标保
        RadarStandard newStadStandard = standardMap.get(RadarStandardType.STADPREM).get(newRateType);
        RadarStandard lastStadStandard = standardMap.get(RadarStandardType.STADPREM).get(lastRateType);
        data.setStadpremMax(Double.valueOf(newStadStandard.getMin()));
        data.setStadpremGap(stadpremNumDouble - newStadStandard.getMin());
        data.setStadpremLastGap(stadpremNumDouble - lastStadStandard.getMin());

        //个人星级
        RadarStandard newPsStandard = standardMap.get(RadarStandardType.PERSONSTAR).get(newRateType);
        RadarStandard lastPsStandard = standardMap.get(RadarStandardType.PERSONSTAR).get(lastRateType);
        data.setPersonStarMax(newPsStandard.getMin());
        data.setPersonStarGap(personStar - newPsStandard.getMin());
        data.setPersonStarLastGap(personStar - lastPsStandard.getMin());

        //团队星级人力
        RadarStandard newSpStandard = standardMap.get(RadarStandardType.STARPOWER).get(newRateType);
        RadarStandard lastSpStandard = standardMap.get(RadarStandardType.STARPOWER).get(lastRateType);
        data.setStarPowerMax(newSpStandard.getMin());
        data.setStarPowerGap(starPower.intValue() - newSpStandard.getMin());
        data.setStarPowerLastGap(starPower.intValue() - lastSpStandard.getMin());

        //继续率
        RadarStandard newRateStandard = standardMap.get(RadarStandardType.RATE).get(newRateType);
        RadarStandard lastRateStandard = standardMap.get(RadarStandardType.RATE).get(lastRateType);
        data.setRateMax(Double.valueOf(newRateStandard.getMin()));
        data.setRateGap(allRate - newRateStandard.getMin());
        data.setRateLastGap(allRate - lastRateStandard.getMin());

        //出勤人力
        RadarStandard newAttendStandard = standardMap.get(RadarStandardType.ATTENDPOWER).get(newRateType);
        RadarStandard lastAttendStandard = standardMap.get(RadarStandardType.ATTENDPOWER).get(lastRateType);
        data.setAttendPowerMax(newAttendStandard.getMin());
        data.setAttendPowerGap(attendanceNum - newAttendStandard.getMin());
        data.setAttendPowerLastGap(attendanceNum - lastAttendStandard.getMin());

        return data;
    }

    private RateType getRateType(RateType type, double num, RadarStandard standard, boolean hasReduce) throws InterruptedException {
        if (hasReduce) return type;
        if (standard.getMin() >= num){
            int typeCode = type.getCode() - 1;
            return type = typeCode > 0 ? RateType.fromCode(typeCode) : RateType.LAGGINGBEHIND;
        }
        return type;
    }

    @Override
    public String getOneselfStar(MobileCommonRequest request){
        CommonResponse response = new CommonResponse();
        StarOneselfResponse data = new StarOneselfResponse();
        MobileUserModel user = getUser(request.getToken());
        if (user == null) return response.setError(ErrorType.NOLOGIN);
        data.setName(user.getName());
        data.setStar(Integer.valueOf(user.getFhagent_grade().substring(2)));
        PositionsType positionsType;
        //获取类型
        try {
            PositionsClass positionsClass = PositionsClass. valueOf(user.getPosition_code());
            positionsType = PositionsType.fromClass(positionsClass);
        } catch (InterruptedException e) {
            logger.error("", e);
            return response.setError(ErrorType.CONVERT);
        }
        boolean isSection = positionsType == PositionsType.BM || positionsType == PositionsType.AS;
        data.setStar(isSection ? 0 : 1);
        //处理季度标保
        LocalDate now = LocalDate.now();
        int monthValue = now.getMonthValue();
        List<Integer> quarter = MyTimeTools.getQuarter(monthValue);
        List<StadpremMonthModel> stadpremList = Lists.newArrayList();
        for (Integer month : quarter) {
            StadpremMonthModel model = new StadpremMonthModel();
            model.setMonth(month);
            LocalDate startDate = now.withMonth(month).withDayOfMonth(1);
            LocalDate endDate = startDate.plusMonths(1);
            Double stadprem = isSection ? businessRepo.sumByDeptCode3(user.getEmployee_part_com(), startDate, endDate)
                    : businessRepo.sumByDeptCode4(user.getEmployee_group_com(), startDate, endDate);
            if (stadprem == null) stadprem = 0d;
            BigDecimal stadpremNumBd = new BigDecimal(String.valueOf(stadprem));
            stadpremNumBd = stadpremNumBd.divide(new BigDecimal("10000"), 0, RoundingMode.HALF_UP);
            int stadpremInt = stadpremNumBd.intValue();
            model.setStadprem(String.valueOf(stadpremInt));
            stadpremList.add(model);
        }
        data.setStadpremList(stadpremList);
        response.setData(data);
        return response.toJson();
    }
}
