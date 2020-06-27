package hx.service.mobile.manage.radar;

import hx.base.core.dao.dict.RadarStandardType;
import hx.base.core.dao.dict.RateType;
import hx.base.core.dao.dict.SectionType;
import hx.base.core.dao.entity.Attendance;
import hx.base.core.dao.entity.ContinueRate;
import hx.base.core.dao.entity.MarketingManpower;
import hx.base.core.dao.entity.StarRating;
import hx.base.core.dao.entity.radar.RadarStandard;
import hx.base.core.dao.repo.jpa.*;
import hx.base.core.dao.repo.jpa.radar.RadarStandardRepo;
import hx.base.core.manage.model.CommonResponse;
import hx.service.mobile.manage.AbstractMobileManager;
import hx.service.mobile.manage.model.login.MobileUserModel;
import hx.service.mobile.manage.model.radar.RadarRequest;
import hx.service.mobile.manage.model.radar.RadarResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
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

    @Override
    public String radar(RadarRequest request){
        CommonResponse response = new CommonResponse();
        RadarResponse data = new RadarResponse();
        MobileUserModel user = getUser(request.getToken());
        if (request.getGroupCode().equals("0")){//查询部相关
            handle(data, user, true, request.getSectionCode());
        }else {
            handle(data, user, false, request.getGroupCode());
        }
        response.setData(data);
        return response.toJson();
    }

    private void handle(RadarResponse data, MobileUserModel user, boolean isSection, String deptCode) {
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
        double stadpremNum = isSection ? businessRepo.sumByDeptCode3(deptCode, startDate, endDate) :
                businessRepo.sumByDeptCode4(deptCode, startDate, endDate);
        Map<RateType, RadarStandard> stadpremStandards = standardMap.get(RadarStandardType.STADPREM);
        int scopMin = 0;
        for (Map.Entry<RateType, RadarStandard> map : stadpremStandards.entrySet()) {
            int min = map.getValue().getMin();
            if (scopMin < min && stadpremNum > min * 10000){
                rateType = map.getKey();
            }
            scopMin = scopMin < min ? min : scopMin;
        }
        BigDecimal stadpremNumBd = new BigDecimal(String.valueOf(stadpremNum));
        stadpremNumBd = stadpremNumBd.divide(new BigDecimal("10000"), 0, RoundingMode.HALF_UP);
        int stadpremNumInt = stadpremNumBd.intValue();
        data.setStadpremNum(stadpremNumInt);

        //处理个人星级
        Integer personStar = Integer.valueOf(user.getFhagent_grade().substring(2));
        RadarStandard psStandard = standardMap.get(RadarStandardType.PERSONSTAR).get(rateType);
        rateType = getRateType(rateType, personStar, psStandard, hasReduce);
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
        rateType = getRateType(rateType, starPower.intValue(), spStandard, hasReduce);

        //处理继续率 TODO 直辖部继续率算平均值吗？
        List<ContinueRate> continueRates = continueRateRepo.listByAgentCodes(agentCodes);
        Double allRateDouble = continueRates.stream().mapToDouble(c -> c.getAllRate()).average().orElse(0) * 100;
        BigDecimal allRateBd = new BigDecimal(allRateDouble.toString());
        allRateBd = allRateBd.setScale(0, RoundingMode.HALF_UP);
        int allRate = allRateBd.intValue();
        data.setRate(allRate);
        RadarStandard rateStandard = standardMap.get(RadarStandardType.RATE).get(rateType);
        rateType = getRateType(rateType, allRate, rateStandard, hasReduce);

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
        //月均标保
        RadarStandard newStadStandard = standardMap.get(RadarStandardType.STADPREM).get(rateType);
        data.setStadpremMax(newStadStandard.getMin());
        data.setStadpremGap(stadpremNumInt - newStadStandard.getMin());

        //个人星级
        RadarStandard newPsStandard = standardMap.get(RadarStandardType.PERSONSTAR).get(rateType);
        data.setPersonStarMax(newPsStandard.getMin());
        data.setPersonStarGap(personStar - newPsStandard.getMin());

        //团队星级人力
        RadarStandard newSpStandard = standardMap.get(RadarStandardType.STARPOWER).get(rateType);
        data.setStarPowerMax(newSpStandard.getMin());
        data.setStarPowerGap(starPower.intValue() - newSpStandard.getMin());

        //继续率
        RadarStandard newRateStandard = standardMap.get(RadarStandardType.RATE).get(rateType);
        data.setRateMax(newRateStandard.getMin());
        data.setRateGap(allRate - newRateStandard.getMin());

        //出勤人力
        RadarStandard newAttendStandard = standardMap.get(RadarStandardType.ATTENDPOWER).get(rateType);
        data.setAttendPowerMax(newAttendStandard.getMin());
        data.setAttendPowerGap(attendanceNum - newAttendStandard.getMin());
    }

    private RateType getRateType(RateType type, int num, RadarStandard standard, boolean hasReduce) {
        if (hasReduce) return type;
        if (standard.getMin() > num){
            int typeCode = type.getCode() - 1;
            hasReduce = true;
            return type = typeCode > 0 ? RateType.fromCode(typeCode) : RateType.LAGGINGBEHIND;
        }
        return type;
    }
}
