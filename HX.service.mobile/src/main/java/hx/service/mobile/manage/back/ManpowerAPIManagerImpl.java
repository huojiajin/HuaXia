package hx.service.mobile.manage.back;

import com.google.common.collect.Lists;
import hx.base.core.dao.dict.acl.PositionsClass;
import hx.base.core.dao.dict.acl.PositionsType;
import hx.base.core.dao.dict.acl.SectionType;
import hx.base.core.dao.dict.structure.RateType;
import hx.base.core.dao.entity.hualife.StarRating;
import hx.base.core.dao.repo.jpa.hualife.BusinessRepo;
import hx.base.core.dao.repo.jpa.hualife.MarketingManpowerRepo;
import hx.base.core.dao.repo.jpa.hualife.StarRatingRepo;
import hx.base.core.dao.repo.jpa.radar.RadarGradeRepo;
import hx.base.core.manage.model.CommonResponse;
import hx.base.core.manage.tools.MyTimeTools;
import hx.service.mobile.manage.common.AbstractMobileManager;
import hx.service.mobile.model.kpi.ManpowerAPIRequest;
import hx.service.mobile.model.kpi.ManpowerAPIResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @ClassName: ManpowerAPIManagerImpl
 * @Description: 人力KPIManagerImpl
 * @Author HuoJiaJin
 * @Date 2021/2/6 23:55
 * @Version 1.0
 **/
@Service
public class ManpowerAPIManagerImpl extends AbstractMobileManager implements ManpowerAPIManager {

    @Autowired
    private MarketingManpowerRepo manpowerRepo;
    @Autowired
    private BusinessRepo businessRepo;
    @Autowired
    private StarRatingRepo starRatingRepo;
    @Autowired
    private RadarGradeRepo gradeRepo;

    @Value("${MDRT}")
    private Long MDRT;

    @Override
    public String api(ManpowerAPIRequest request){
        CommonResponse response = new CommonResponse();
        ManpowerAPIResponse apiResponse = new ManpowerAPIResponse();
        String campCode = request.getCampCode();
        LocalDate now = LocalDate.now();
        //当月时间
        LocalDate monthStartDate = now.withDayOfMonth(1);
        LocalDate monthEndDate = monthStartDate.plusMonths(1);
        //当季度时间
        List<Integer> quarter = MyTimeTools.getQuarter(now.getMonthValue());
        LocalDate quarterStartDate = now.withMonth(quarter.get(0)).withDayOfMonth(1);
        LocalDate quarterEndDate = quarterStartDate.plusMonths(3);

        //总体情况
        //在册人力
        Integer manpower = manpowerRepo.countByDeptCode1(campCode);
        apiResponse.setManpower(manpower.toString());
        //当月预收
        Double advanceMonth = businessRepo.sumPreByDeptCode1(campCode, monthStartDate, monthEndDate);
        BigDecimal advanceMonthBd = new BigDecimal(String.valueOf(advanceMonth))
                .divide(new BigDecimal(10000), 2, BigDecimal.ROUND_HALF_UP);
        apiResponse.setAdvanceMonth(advanceMonthBd.toString());
        //当月承保
        Double insuranceMonth = businessRepo.sumByDeptCode1(campCode, monthStartDate, monthEndDate);
        BigDecimal insuranceMonthBd = new BigDecimal(String.valueOf(insuranceMonth))
                .divide(new BigDecimal(10000), 2, BigDecimal.ROUND_HALF_UP);
        apiResponse.setInsuranceMonth(insuranceMonthBd.toString());
        //当月实动
        Integer realActionMonth = businessRepo.campRealAction(campCode, monthStartDate, monthEndDate, 5000L);
        apiResponse.setRealActionMonth(realActionMonth.toString());
        //当月万元
        Integer moreWanMonth = businessRepo.campRealAction(campCode, monthStartDate, monthEndDate, 10000L);
        apiResponse.setMoreWanMonth(moreWanMonth.toString());

        //新人情况
        //当月新增
        Integer increasedMonth = manpowerRepo.campMonthNew(campCode, monthStartDate, monthEndDate);
        apiResponse.setIncreasedMonth(increasedMonth.toString());
        //当季度新增
        Integer increasedQuarter = manpowerRepo.campMonthNew(campCode, quarterStartDate, quarterEndDate);
        apiResponse.setIncreasedQuarter(increasedQuarter.toString());
        //新增实动
        Integer increasedRealAction = businessRepo.newRealAction(campCode, monthStartDate, monthEndDate, 5000L);
        apiResponse.setIncreasedRealAction(increasedRealAction.toString());
        //新增万元
        Integer increasedWan = businessRepo.newRealAction(campCode, monthStartDate, monthEndDate, 10000L);
        apiResponse.setIncreasedWan(increasedWan.toString());
        //新增保费贡献
        Double increasedContribution = businessRepo.sumByDeptCode1AndEmployeeDate(campCode, monthStartDate, monthEndDate);
        BigDecimal increasedContributionBd = new BigDecimal(String.valueOf(increasedContribution))
                .divide(new BigDecimal(10000), 2, BigDecimal.ROUND_HALF_UP);
        apiResponse.setIncreasedContribution(increasedContributionBd.toString());

        //主管情况
        //总监数
        Integer chiefNum = manpowerRepo.countByGrades(campCode, Lists.newArrayList(PositionsClass.AS.name()));
        apiResponse.setChiefNum(chiefNum.toString());
        //部数
        Integer sectionNum = manpowerRepo.countSection(campCode);
        apiResponse.setSectionNum(sectionNum.toString());
        //组数
        Integer groupNum = manpowerRepo.countGroup(campCode);
        apiResponse.setGroupNum(groupNum.toString());
        //主管在册人数
        List<PositionsClass> positionsClassList = PositionsType.BC.getPositionsClass();
        positionsClassList.addAll(PositionsType.BM.getPositionsClass());
        positionsClassList.addAll(PositionsType.AS.getPositionsClass());
        List<String> gradeList = positionsClassList.stream().map(PositionsClass::name).collect(Collectors.toList());
        Integer executiveNum = manpowerRepo.countByGrades(campCode, gradeList);
        apiResponse.setExecutiveNum(executiveNum.toString());
        //主管实动
        Integer executiveRealAction = businessRepo.executiveRealAction(campCode, monthStartDate, monthEndDate, gradeList);
        apiResponse.setExecutiveRealAction(executiveRealAction.toString());
        //主管实动率
        BigDecimal executiveRealActionRateBd = null;
        if (realActionMonth == 0 || realActionMonth == null){
            executiveRealActionRateBd = new BigDecimal("0.00");
        }else {
            executiveRealActionRateBd = new BigDecimal(executiveRealAction)
                    .divide(new BigDecimal(realActionMonth), 4, BigDecimal.ROUND_HALF_UP)
                    .multiply(new BigDecimal(100));
        }
        apiResponse.setExecutiveRealActionRate(executiveRealActionRateBd.toString());

        //绩优情况
        //MDRT人数
        Integer MDRTNum = businessRepo.campRealAction(campCode, now.withDayOfYear(1), now.plusDays(1), MDRT);
        apiResponse.setMDRTNum(MDRTNum.toString());
        //4星以上人数
        List<StarRating> starRatings = starRatingRepo.listByDeptCode1(campCode);
        Long fourStarNum = starRatings.stream().filter(s -> {
            Integer star = Integer.valueOf(s.getFhagentGrade().substring(2));
            return star >= 4;
        }).count();
        apiResponse.setFourStarNum(fourStarNum.toString());
        //4星-5星人数
        Long fiveStarNum = starRatings.stream().filter(s -> {
            Integer star = Integer.valueOf(s.getFhagentGrade().substring(2));
            return star >= 4 && star <= 5;
        }).count();
        apiResponse.setFiveStarNum(fiveStarNum.toString());
        //6星-9星人数
        Long sixStarNum = starRatings.stream().filter(s -> {
            Integer star = Integer.valueOf(s.getFhagentGrade().substring(2));
            return star >= 6 && star <= 9;
        }).count();
        apiResponse.setSixStarNum(sixStarNum.toString());
        //10星以上人数
        Long tenStarNum = starRatings.stream().filter(s -> {
            Integer star = Integer.valueOf(s.getFhagentGrade().substring(2));
            return star >= 10;
        }).count();
        apiResponse.setSixStarNum(tenStarNum.toString());
        //星级人数占比
        BigDecimal starRateBd = new BigDecimal(fourStarNum)
                .divide(new BigDecimal(manpower), 4, BigDecimal.ROUND_HALF_UP)
                .multiply(new BigDecimal(100));
        apiResponse.setStarRate(starRateBd.toString());
        //星级主管人数
        Integer starExecutive = starRatingRepo.countExecutive(campCode, gradeList);
        apiResponse.setStarExecutive(starExecutive.toString());
        //星级非主管人数
        Long starNotExecutive = fourStarNum - starExecutive;
        apiResponse.setStarNotExecutive(starNotExecutive.toString());
        //星级保费贡献
        Double starContribution = businessRepo.starContribution(campCode, monthStartDate, monthEndDate);
        apiResponse.setStarContribution(starContribution.toString());
        //星级保费占比
        BigDecimal starContributionRateBd = null;
        if (insuranceMonth == 0){
            starContributionRateBd = new BigDecimal("0.00");
        }else {
            starContributionRateBd = new BigDecimal(starContribution).divide(new BigDecimal(insuranceMonth), 4, BigDecimal.ROUND_HALF_UP)
                    .multiply(new BigDecimal("100"));
        }
        apiResponse.setStarContributionRate(starContributionRateBd.toString());
        //主管星级化
        BigDecimal starExecutiveRateBd = new BigDecimal(fourStarNum).divide(new BigDecimal(executiveNum), 2, BigDecimal.ROUND_HALF_UP)
                .multiply(new BigDecimal("100"));
        apiResponse.setStarExecutiveRate(starExecutiveRateBd.toString());

        //架构情况-部
        String month = MyTimeTools.dateToStr(quarterStartDate, "yyyy-MM");
        //部卓越数
        Integer excellentSectionNum = gradeRepo.countByMonth(month, SectionType.SECTION, RateType.EXCELLENT);
        apiResponse.setExcellentSectionNum(excellentSectionNum.toString());
        //部优秀数
        Integer outstandingSectionNum = gradeRepo.countByMonth(month, SectionType.SECTION, RateType.OUTSTANDING);
        apiResponse.setOutstandingSectionNum(outstandingSectionNum.toString());
        //部高潜数
        Integer highpotentialSectionNum = gradeRepo.countByMonth(month, SectionType.SECTION, RateType.HIGHPOTENTIAL);
        apiResponse.setHighpotentialSectionNum(highpotentialSectionNum.toString());
        //部后进数
        Integer laggingbehindSectionNum = gradeRepo.countByMonth(month, SectionType.SECTION, RateType.LAGGINGBEHIND);
        apiResponse.setLaggingbehindSectionNum(laggingbehindSectionNum.toString());

        //架构情况-组
        //组卓越数
        Integer excellentGroupNum = gradeRepo.countByMonth(month, SectionType.GROUP, RateType.EXCELLENT);
        apiResponse.setExcellentGroupNum(excellentGroupNum.toString());
        //组优秀数
        Integer outstandingGroupNum = gradeRepo.countByMonth(month, SectionType.GROUP, RateType.OUTSTANDING);
        apiResponse.setOutstandingGroupNum(outstandingGroupNum.toString());
        //组高潜数
        Integer highpotentialGroupNum = gradeRepo.countByMonth(month, SectionType.GROUP, RateType.HIGHPOTENTIAL);
        apiResponse.setHighpotentialGroupNum(highpotentialGroupNum.toString());
        //组后进数
        Integer laggingbehindGroupNum = gradeRepo.countByMonth(month, SectionType.GROUP, RateType.LAGGINGBEHIND);
        apiResponse.setLaggingbehindGroupNum(laggingbehindGroupNum.toString());

        response.setData(apiResponse);
        return response.toJson();
    }
}
