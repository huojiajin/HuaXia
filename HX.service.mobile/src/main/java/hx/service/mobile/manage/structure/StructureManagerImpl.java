package hx.service.mobile.manage.structure;

import com.google.common.collect.Maps;
import hx.base.core.dao.dict.*;
import hx.base.core.dao.entity.*;
import hx.base.core.dao.repo.jpa.*;
import hx.base.core.manage.model.CommonResponse;
import hx.service.mobile.manage.AbstractMobileManager;
import hx.service.mobile.manage.model.common.MobileCommonRequest;
import hx.service.mobile.manage.model.login.MobileUserModel;
import hx.service.mobile.manage.model.structure.*;
import org.apache.commons.compress.utils.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @name: StructureManagerImpl
 * @description: 组织架构相关ManagerImpl
 * @author: huojiajin
 * @time: 2020/7/7 1:15
 */
@Service
public class StructureManagerImpl extends AbstractMobileManager implements StructureManager {

    @Autowired
    private StructureStandardRepo standardRepo;
    @Autowired
    private MarketingManpowerRepo manpowerRepo;
    @Autowired
    private BusinessRepo businessRepo;
    @Autowired
    private AttendanceRepo attendanceRepo;
    @Autowired
    private StarRatingRepo starRatingRepo;
    @Autowired
    private IncubationRepo incubationRepo;

    @Value("${MDRT}")
    private Long MDRT;

    @Override
    public String getOrgList(MobileCommonRequest request){
        CommonResponse response = new CommonResponse();
        StructureOrgListResponse data = new StructureOrgListResponse();
        MobileUserModel user = getUser(request.getToken());
        if (user == null) return response.setError(ErrorType.NOLOGIN);
        //解析职级
        PositionsType positionsType;
        try {
            PositionsClass positionsClass = PositionsClass. valueOf(user.getPosition_code());
            positionsType = PositionsType.fromClass(positionsClass);
        } catch (Exception e) {
            logger.error("", e);
            return response.setError(ErrorType.CONVERT);
        }
        List<StructureOrgListModel> orgList = Lists.newArrayList();
        if (positionsType != PositionsType.FZG) {
            if (positionsType == PositionsType.BM || positionsType == PositionsType.AS) {
                StructureOrgListModel groupModel = new StructureOrgListModel();
                groupModel.setOrgCode(user.getEmployee_part_com());
                groupModel.setOrgName("直辖部");
                groupModel.setOrgType(0);
                orgList.add(groupModel);
            }
            StructureOrgListModel groupModel = new StructureOrgListModel();
            groupModel.setOrgCode(user.getEmployee_group_com());
            groupModel.setOrgName("直辖组");
            groupModel.setOrgType(1);
            orgList.add(groupModel);
        }
        data.setOrgList(orgList);
        response.setData(data);
        return response.toJson();
    }

    @Override
    public String structureAnalysis(SructureAnalysisRequest request){
        CommonResponse response = new CommonResponse();
        MobileUserModel user = getUser(request.getToken());
        SructureAnalysisResponse data = new SructureAnalysisResponse();
        SectionType sectionType;
        try {
            sectionType = SectionType.fromCode(request.getOrgType());
        } catch (InterruptedException e) {
            return response.setError(ErrorType.CONVERT);
        }
        List<StructureStandard> standardList = standardRepo.findAll();
        Map<SectionType, List<StructureStandard>> standardMap = standardList.stream()
                .collect(Collectors.groupingBy(StructureStandard::getSectionType));
        if (sectionType == SectionType.SECTION){
            handleSection(standardMap.get(SectionType.SECTION), user, request.getOrgCode(), data);
        }else {
            handleGroup(standardMap.get(SectionType.GROUP), user, request.getOrgCode(), data);
        }
        response.setData(data);
        return response.toJson();
    }

    private void handleSection(List<StructureStandard> standardList, MobileUserModel user,
                               String orgCode, SructureAnalysisResponse data){
        List<String> situation = Lists.newArrayList();//现状
        List<String> advantage = Lists.newArrayList();//优势
        List<String> inferiority = Lists.newArrayList();//劣势
        List<String> advice = Lists.newArrayList();//建议

        //根据部门类型及标准类型分组
        Map<StructureType, List<StructureStandard>> standardMaps = standardList.stream()
                .collect(Collectors.groupingBy(StructureStandard::getStructureType));
        //获取人员集合
        List<SructureAnalysisEmployeeModel> employeeList = Lists.newArrayList();
        List<MarketingManpower> manpowerList = manpowerRepo.listByDeptCode3(orgCode);
        Map<String, List<MarketingManpower>> maps = manpowerList.stream()
                .collect(Collectors.groupingBy(m -> m.getDeptCode4() + "|" + m.getDeptName4() + "|"
                        + m.getName1() + "|" + m.getAgentCode1()));
        for (Map.Entry<String, List<MarketingManpower>> map : maps.entrySet()) {
            SructureAnalysisEmployeeModel employee = new SructureAnalysisEmployeeModel();
            String[] arr = map.getKey().split("\\|");
            employee.setGroupCode(arr[0]);
            employee.setName(arr[2]);
            employee.setAgentCode(arr[3]);
            employee.setPersonNum(map.getValue().size());
            employeeList.add(employee);
        }
        data.setEmployeeList(employeeList);

        //查询直辖部人员数量
        List<MarketingManpower> sectionManpower = manpowerRepo.listByDeptCode3(orgCode);
        //查询直辖组人员数量
        List<MarketingManpower> groupManpower = manpowerRepo.listByDeptCode4(user.getEmployee_group_com());

        //获取人员编码集合，为后续查询做准备
        List<String> agentCodes = sectionManpower.stream().map(MarketingManpower::getAgentCode).collect(Collectors.toList());
        //获取查询时间范围
        LocalDate now = LocalDate.now();
        LocalDate startDate = now.withDayOfMonth(1);
        LocalDate endDate = startDate.plusDays(1);

        //处理在册人力
        int sectionPowerNum = isEmpty(sectionManpower) ? 0 : sectionManpower.size();
        int groupPowerNum = isEmpty(groupManpower) ? 0 : groupManpower.size();
        situation.add(toLogString(StructureType.ZCRL.getSectionSituation(), sectionPowerNum, groupPowerNum));
        judgeRateType(standardMaps.get(StructureType.ZCRL), sectionManpower.size(), advantage, inferiority, advice);

        //处理出勤人力
        List<Attendance> attendanceList = attendanceRepo.listByAgentCodes(agentCodes, startDate, endDate);
        int frequent = 0;
        int attendNum = 0;
        if (!isEmpty(attendanceList)) {
            Map<String, List<Attendance>> attendanceMaps = attendanceList.stream().collect(Collectors.groupingBy(Attendance::getStaffCode));
            attendNum = attendanceMaps.size();
            for (Map.Entry<String, List<Attendance>> map : attendanceMaps.entrySet()) {
                if (map.getValue().size() >= 15) {
                    frequent++;
                }
            }
        }
        situation.add(toLogString(StructureType.CQRL.getSectionSituation(), attendNum, frequent));
        judgeRateType(standardMaps.get(StructureType.CQRL), attendNum, advantage, inferiority, advice);

        //处理绩优人力
        List<StarRating> starRatings = starRatingRepo.listByAgentCodes(agentCodes);
        Long fourStarCount = starRatings.stream().filter(s -> Integer.valueOf(s.getFhagentGrade().substring(2)) >= 4).count();
        //获取本年度业务清单
        LocalDate MDRTStartDate = now.withDayOfYear(1);
        LocalDate MDRTEndDate = MDRTStartDate.plusYears(1);
        List<Object[]> businessList = businessRepo.listSumBySection(orgCode, MDRTStartDate, MDRTEndDate);
        //计算MDRT人数
        List<BigDecimal> MDRTList = businessList.stream().map(o -> new BigDecimal(String.valueOf(o[1])))
                .filter(b -> b.compareTo(new BigDecimal(MDRT)) > -1).collect(Collectors.toList());
        situation.add(toLogString(StructureType.JYRL.getSectionSituation(), fourStarCount, MDRTList.size()));
        judgeRateType(standardMaps.get(StructureType.JYRL), fourStarCount.intValue(), advantage, inferiority, advice);

        //处理直育组数
        List<Incubation> incubationList = incubationRepo.listByRearAgentCode(user.getEmployee_code());
        //直接育成数
        Long direct = incubationList.stream().filter(i -> i.getRearedgens() == 1).count();
        //间接育成数
        long indirect = incubationList.size() - direct;
        situation.add(toLogString(StructureType.ZYZS.getSectionSituation(), direct, indirect));
        judgeRateType(standardMaps.get(StructureType.ZYZS), direct.intValue(), advantage, inferiority, advice);

        //处理实动人力
        //部实动人力
        List<BigDecimal> realActionList = businessList.stream().map(o -> new BigDecimal(String.valueOf(o[1])))
                .filter(b -> b.compareTo(new BigDecimal(5000)) > -1).collect(Collectors.toList());
        Integer realActionNum = realActionList.size();
        //组实动人力
        List<Object[]> listSumByGroup = businessRepo.listSumByGroup(user.getEmployee_group_com(), MDRTStartDate, MDRTEndDate);
        List<BigDecimal> groupRealActionList = listSumByGroup.stream().map(o -> new BigDecimal(String.valueOf(o[1])))
                .filter(b -> b.compareTo(new BigDecimal(5000)) > -1).collect(Collectors.toList());
        situation.add(toLogString(StructureType.SDRL.getSectionSituation(), realActionNum, groupRealActionList.size()));
        judgeRateType(standardMaps.get(StructureType.SDRL), realActionNum, advantage, inferiority, advice);

        //月新增数
        Integer sectionMonthNew = manpowerRepo.sectionMonthNew(orgCode, startDate, endDate);
        Integer groupMonthNew = manpowerRepo.groupMonthNew(user.getEmployee_group_com(), startDate, endDate);
        situation.add(toLogString(StructureType.YXZS.getSectionSituation(), sectionMonthNew, groupMonthNew));
        judgeRateType(standardMaps.get(StructureType.YXZS), sectionMonthNew, advantage, inferiority, advice);

        //主管绩优
        StarRating oneselfStar = starRatingRepo.findByAgentCode(user.getEmployee_code());
        Integer star = Integer.valueOf(oneselfStar.getFhagentGrade().substring(2));
        Double stadprem = businessRepo.sumByAgentCode(user.getEmployee_code(), startDate, endDate);
        boolean isMDRT = stadprem >= MDRT;
        situation.add(toLogString(StructureType.ZGJY.getSectionSituation(), star, isMDRT ? "已" : "未"));
        judgeRateType(standardMaps.get(StructureType.YXZS), star, advantage, inferiority, advice);

        data.setAdvantage(advantage);
        data.setAdvice(advice);
        data.setInferiority(inferiority);
        data.setSituation(situation);

        //判断类型
        String type = "";
        if (direct < 2){
            type = "踩钢丝型";
        }else if (sectionPowerNum < 50){
            type = "小富即安型";
        }else if (attendNum < 15){
            type = "外强中干型";
        }else if (fourStarCount < 7){
            type = "厚积薄发型";
        }else{
            type = "枝繁叶茂型";
        }
        data.setType(type);
    }

    private void handleGroup(List<StructureStandard> standardList, MobileUserModel user,
                               String orgCode, SructureAnalysisResponse data){
        List<String> situation = Lists.newArrayList();//现状
        List<String> advantage = Lists.newArrayList();//优势
        List<String> inferiority = Lists.newArrayList();//劣势
        List<String> advice = Lists.newArrayList();//建议

        //根据部门类型及标准类型分组
        Map<StructureType, List<StructureStandard>> standardMaps = standardList.stream()
                .collect(Collectors.groupingBy(StructureStandard::getStructureType));
        //获取人员集合
        List<SructureAnalysisEmployeeModel> employeeList = Lists.newArrayList();
        List<MarketingManpower> manpowerList = manpowerRepo.listByDeptCode4(orgCode);
        SructureAnalysisEmployeeModel employee = new SructureAnalysisEmployeeModel();
        employee.setGroupCode(user.getEmployee_group_com());
        employee.setName(user.getName());
        employee.setAgentCode(user.getEmployee_code());
        employee.setPersonNum(manpowerList.size());
        employeeList.add(employee);

        data.setEmployeeList(employeeList);

        //查询直辖组人员数量
        List<MarketingManpower> groupManpower = manpowerRepo.listByDeptCode4(user.getEmployee_group_com());

        //获取人员编码集合，为后续查询做准备
        List<String> agentCodes = groupManpower.stream().map(MarketingManpower::getAgentCode).collect(Collectors.toList());
        //获取查询时间范围
        LocalDate now = LocalDate.now();
        LocalDate startDate = now.withDayOfMonth(1);
        LocalDate endDate = startDate.plusDays(1);
        //处理在册人力
        int groupPowerNum = isEmpty(groupManpower) ? 0 : groupManpower.size();
        situation.add(toLogString(StructureType.ZCRL.getGroupSituation(), groupPowerNum));
        judgeRateType(standardMaps.get(StructureType.ZCRL), manpowerList.size(), advantage, inferiority, advice);

        //处理出勤人力
        List<Attendance> attendanceList = attendanceRepo.listByAgentCodes(agentCodes, startDate, endDate);
        int frequent = 0;
        int attendNum = 0;
        if (!isEmpty(attendanceList)) {
            Map<String, List<Attendance>> attendanceMaps = attendanceList.stream().collect(Collectors.groupingBy(Attendance::getStaffCode));
            attendNum = attendanceMaps.size();
            for (Map.Entry<String, List<Attendance>> map : attendanceMaps.entrySet()) {
                if (map.getValue().size() >= 15) {
                    frequent++;
                }
            }
        }
        situation.add(toLogString(StructureType.CQRL.getGroupSituation(), attendNum, frequent));
        judgeRateType(standardMaps.get(StructureType.CQRL), attendNum, advantage, inferiority, advice);

        //处理绩优人力
        List<StarRating> starRatings = starRatingRepo.listByAgentCodes(agentCodes);
        Long fourStarCount = starRatings.stream().filter(s -> Integer.valueOf(s.getFhagentGrade().substring(2)) >= 4).count();

        //获取本年度业务清单
        LocalDate MDRTStartDate = now.withDayOfYear(1);
        LocalDate MDRTEndDate = MDRTStartDate.plusYears(1);
        List<Object[]> businessList = businessRepo.listSumByGroup(orgCode, MDRTStartDate, MDRTEndDate);
        //计算MDRT人数
        List<BigDecimal> MDRTList = businessList.stream().map(o -> new BigDecimal(String.valueOf(o[1])))
                .filter(b -> b.compareTo(new BigDecimal(MDRT)) > -1).collect(Collectors.toList());

        situation.add(toLogString(StructureType.JYRL.getGroupSituation(), fourStarCount, MDRTList.size()));
        judgeRateType(standardMaps.get(StructureType.JYRL), fourStarCount.intValue(), advantage, inferiority, advice);

        //处理直增人数
        //直增人数
        List<MarketingManpower> manpowers = manpowerRepo.listByRecomm(user.getEmployee_code());
        List<String> recommCodes = manpowers.stream().map(MarketingManpower::getAgentCode).collect(Collectors.toList());
        Integer indirectNum = manpowerRepo.countByRecommCodes(recommCodes);

        situation.add(toLogString(StructureType.ZYZS.getGroupSituation(), manpowers.size(), indirectNum));
        judgeRateType(standardMaps.get(StructureType.ZYZS), manpowers.size(), advantage, inferiority, advice);

        //处理实动人力
        List<BigDecimal> realActionList = businessList.stream().map(o -> new BigDecimal(String.valueOf(o[1])))
                .filter(b -> b.compareTo(new BigDecimal(5000)) > -1).collect(Collectors.toList());
        situation.add(toLogString(StructureType.SDRL.getGroupSituation(), realActionList.size()));
        judgeRateType(standardMaps.get(StructureType.SDRL), realActionList.size(), advantage, inferiority, advice);

        //月新增数
        Integer groupMonthNew = manpowerRepo.groupMonthNew(orgCode, startDate, endDate);
        situation.add(toLogString(StructureType.YXZS.getGroupSituation(), groupMonthNew));
        judgeRateType(standardMaps.get(StructureType.YXZS), groupMonthNew, advantage, inferiority, advice);

        //主管绩优
        StarRating oneselfStar = starRatingRepo.findByAgentCode(user.getEmployee_code());
        Integer star = Integer.valueOf(oneselfStar.getFhagentGrade().substring(2));
        Double stadprem = businessRepo.sumByAgentCode(user.getEmployee_code(), startDate, endDate);
        boolean isMDRT = stadprem >= MDRT;
        situation.add(toLogString(StructureType.ZGJY.getGroupSituation(), star, isMDRT ? "已" : "未"));
        judgeRateType(standardMaps.get(StructureType.YXZS), star, advantage, inferiority, advice);

        data.setAdvantage(advantage);
        data.setAdvice(advice);
        data.setInferiority(inferiority);
        data.setSituation(situation);

        //判断类型
        String type = "";
        if (indirectNum < 2){
            type = "踩钢丝型";
        }else if (groupPowerNum < 8){
            type = "小富即安型";
        }else if (attendNum < 5){
            type = "外强中干型";
        }else if (fourStarCount < 3){
            type = "厚积薄发型";
        }else{
            type = "枝繁叶茂型";
        }
        data.setType(type);
    }


    private void judgeRateType(List<StructureStandard> standardList, int value, List<String> advantage,
                                   List<String> inferiority, List<String> advice){
        //根据等级类型分组
        Map<RateType, StructureStandard> maps = standardList.stream()
                .collect(Collectors.toMap(StructureStandard::getRateType, Function.identity()));
        RateType rateType = null;
        int scopeMin = 0;
        for (StructureStandard standard : standardList) {
            Integer min = standard.getMin();
            if (min <= value){
                if (scopeMin <= min) {
                    rateType = standard.getRateType();
                    scopeMin = min;
                }
            }
        }
        StructureStandard standard = maps.get(rateType);
        advantage.add(standard.getAdvantage());
        inferiority.add(standard.getInferiority());
        advice.add(standard.getAdvise());
    }

    @Override
    public String getPersonList(StructurePersonListRequest request){
        CommonResponse response = new CommonResponse();
        SructurePersonListResponse data = new SructurePersonListResponse();
        MobileUserModel user = getUser(request.getToken());
        LocalDate now = LocalDate.now();
        LocalDate startDate = now.withDayOfMonth(1);
        LocalDate endDate = startDate.plusMonths(1);
        if (user == null) return response.setError(ErrorType.NOLOGIN);
        //查询人员数据
        List<MarketingManpower> manpowerList = manpowerRepo.listByDeptCode4(request.getGroupCode());
        //查询营销数据,并根据agentCode拼装Map
        List<Object[]> businessList = businessRepo.listSumByGroup(request.getGroupCode(), startDate, endDate);
        Map<String, BigDecimal> businessMap = Maps.newHashMap();
        for (Object[] o : businessList) {
            businessMap.put(String.valueOf(o[0]), new BigDecimal(String.valueOf(o[1])));
        }
        List<SructurePersonListModel> result = Lists.newArrayList();
        for (MarketingManpower manpower : manpowerList) {
            SructurePersonListModel model = new SructurePersonListModel();
            model.setName(manpower.getName());
            model.setAgentCode(manpower.getAgentCode());
            model.setGradeName(manpower.getAgentGradeName());
            BigDecimal stadprem = businessMap.get(manpower.getAgentCode());
            model.setMdrt(stadprem.compareTo(new BigDecimal(5000)) > -1);
            model.setRecommName(manpower.getRecommAgentName());
            model.setEmployeeDate(String.valueOf(manpower.getEmployDate()));
            result.add(model);
        }

        data.setResult(result);
        response.setData(data);
        return response.toJson();
    }

    @Override
    public String getPersonDetail(StructurePersonDetailRequest request){
        CommonResponse response = new CommonResponse();
        MobileUserModel user = getUser(request.getToken());
        if (user == null) return response.setError(ErrorType.NOLOGIN);
        MarketingManpower manpower = manpowerRepo.findByAgentCode(request.getAgentCode());
        StructurePersonDetailResponse data = new StructurePersonDetailResponse();
        data.setAgentCode(manpower.getAgentCode());
        data.setName(manpower.getName());
        data.setStartDate(String.valueOf(manpower.getStartDate()));
        data.setEmployeeDate(String.valueOf(manpower.getEmployDate()));
        LocalDate now = LocalDate.now();
        LocalDate startDate = now.withDayOfMonth(1);
        LocalDate endDate = startDate.plusMonths(1);
        //获取出勤天数
        List<Attendance> attendances = attendanceRepo.listByAgentCode(manpower.getAgentCode(), startDate, endDate);
        data.setAttendNum(attendances.size());
        //获取凤凰社星级
        StarRating starRating = starRatingRepo.findByAgentCode(manpower.getAgentCode());
        data.setStar(Integer.valueOf(starRating.getFhagentGrade().substring(2)));
        //当月承保标保
        Double sum = businessRepo.sumByAgentCode(manpower.getAgentCode(), startDate, endDate);
        data.setStadprem(sum);

        response.setData(data);
        return response.toJson();
    }
}
