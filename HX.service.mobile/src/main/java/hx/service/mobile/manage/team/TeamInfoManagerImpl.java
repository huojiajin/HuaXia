package hx.service.mobile.manage.team;

import hx.base.core.dao.dict.acl.ErrorType;
import hx.base.core.dao.dict.acl.PositionsClass;
import hx.base.core.dao.dict.acl.PositionsType;
import hx.base.core.dao.entity.hualife.MarketingManpower;
import hx.base.core.dao.entity.hualife.StarRating;
import hx.base.core.dao.repo.jpa.hualife.BusinessRepo;
import hx.base.core.dao.repo.jpa.hualife.MarketingManpowerRepo;
import hx.base.core.dao.repo.jpa.hualife.StarRatingRepo;
import hx.base.core.manage.model.CommonResponse;
import hx.service.mobile.manage.common.AbstractMobileManager;
import hx.service.mobile.model.common.MobileCommonRequest;
import hx.service.mobile.model.login.MobileUserModel;
import hx.service.mobile.model.team.*;
import org.apache.commons.compress.utils.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @ClassName: TeamInfoManagerImpl
 * @Description: 团队信息相关ManagerImpl
 * @Author HuoJiaJin
 * @Date 2021/2/24 23:42
 * @Version 1.0
 **/
@Service
public class TeamInfoManagerImpl extends AbstractMobileManager implements TeamInfoManager {

    @Autowired
    private MarketingManpowerRepo manpowerRepo;
    @Autowired
    private BusinessRepo businessRepo;
    @Autowired
    private StarRatingRepo starRatingRepo;

    @Override
    public String getSectionList(MobileCommonRequest request){
        CommonResponse response = new CommonResponse();
        TeamInfoSectionListResponse data = new TeamInfoSectionListResponse();
        //获取用户
        MobileUserModel user = getUser(request.getToken());
        //解析职级
        PositionsType positionsType;
        try {
            PositionsClass positionsClass = PositionsClass.valueOf(user.getPosition_code());
            positionsType = PositionsType.fromClass(positionsClass);
        } catch (Exception e) {
            logger.error("", e);
            return response.setError(ErrorType.CONVERT, "无此职级" + user.getPosition_code());
        }
        List<TeamInfoSectionModel> sectionList = Lists.newArrayList();
        if (positionsType == PositionsType.AS){
            TeamInfoSectionModel modelAll = new TeamInfoSectionModel();
            modelAll.setSectionName("全部");
            modelAll.setSectionCode("0");
            sectionList.add(modelAll);
            List<Map<String, String>> mapList = manpowerRepo.groupBySection(user.getEmployee_major_com());
            for (Map<String, String> map : mapList) {
                TeamInfoSectionModel model = new TeamInfoSectionModel();
                model.setSectionName(map.get("sectionName"));
                model.setSectionCode(map.get("sectionCode"));
                sectionList.add(model);
            }
        }else if (positionsType == PositionsType.BC || positionsType == PositionsType.BM){
            TeamInfoSectionModel model = new TeamInfoSectionModel();
            model.setSectionName(user.getEmployee_part_com_name());
            model.setSectionCode(user.getEmployee_part_com());
            sectionList.add(model);
        }else {
            return response.setError(ErrorType.NORESOURCE);
        }
        data.setSectionList(sectionList);
        response.setData(data);
        return response.toJson();
    }

    @Override
    public String getGroupList(TeamInfoGroupListReqeust request){
        CommonResponse response = new CommonResponse();
        TeamInfoGroupListResponse data = new TeamInfoGroupListResponse();
        //获取用户
        MobileUserModel user = getUser(request.getToken());
        //解析职级
        PositionsType positionsType;
        try {
            PositionsClass positionsClass = PositionsClass.valueOf(user.getPosition_code());
            positionsType = PositionsType.fromClass(positionsClass);
        } catch (Exception e) {
            logger.error("", e);
            return response.setError(ErrorType.CONVERT, "无此职级" + user.getPosition_code());
        }
        List<TeamInfoGroupModel> groupList = Lists.newArrayList();
        if (!request.getSectionCode().equals("0")) {
            if (positionsType == PositionsType.BC) {
                TeamInfoGroupModel model = new TeamInfoGroupModel();
                model.setGroupName(user.getEmployee_group_com_name());
                model.setGroupCode(user.getEmployee_group_com());
                groupList.add(model);
            }else {
                TeamInfoGroupModel modelAll = new TeamInfoGroupModel();
                modelAll.setGroupName("全部");
                modelAll.setGroupCode("0");
                groupList.add(modelAll);
                List<Map<String, String>> mapList = manpowerRepo.groupByGroup(request.getSectionCode());
                for (Map<String, String> map : mapList) {
                    TeamInfoGroupModel model = new TeamInfoGroupModel();
                    model.setGroupName(map.get("groupName"));
                    model.setGroupCode(map.get("groupCode"));
                    groupList.add(model);
                }
            }
        }
        data.setGroupList(groupList);
        response.setData(data);
        return response.toJson();
    }

    @Override
    public String query(TeamInfoQueryRequest request){
        CommonResponse response = new CommonResponse();
        //请求参数校验
        if (!hasText(request.getSectionCode()) && !hasText(request.getGroupCode())){
            return response.setError(ErrorType.CONVERT, "请传入组织代码参数");
        }
        if (!hasText(request.getStartDate()) || !hasText(request.getEndDate())){
            return response.setError(ErrorType.CONVERT, "请传入时间参数");
        }
        //获取用户
        MobileUserModel user = getUser(request.getToken());
        //初始化日期
        LocalDate startDate = LocalDate.parse(request.getStartDate() + "-01");
        LocalDate endDate = startDate.plusMonths(1);
        LocalDate endDateFinally = LocalDate.parse(request.getEndDate() + "-01").plusMonths(1);
        //开始处理
        TeamInfoQueryResponse data = new TeamInfoQueryResponse();
        if (hasText(request.getSectionCode())){
            if (request.getSectionCode().equals("0")){//查询所有部数据
                dealSection(user, startDate, endDate, endDateFinally, data);
            }else if (request.getGroupCode().equals("0") || !hasText(request.getSectionCode())){//查询所有组数据
                dealGroup(request.getSectionCode(), startDate, endDate, endDateFinally, data);
            }else if (!request.getGroupCode().equals("0")){//查询指定组个人数据
                dealPerson(request.getGroupCode(), startDate, endDate, endDateFinally, data);
            }
        }else {
            if (!request.getGroupCode().equals("0")){//查询指定组个人数据
                dealPerson(request.getGroupCode(), startDate, endDate, endDateFinally, data);
            }
        }
        response.setData(data);
        return response.toJson();
    }

    /**
     * @Name dealSection
     * @Author HuoJiaJin
     * @Description 处理所有部数据
     * @Date 2021/2/25 1:58
     * @Param [user, startDate, endDate, endDateFinally, result]
     * @Return void
     **/
    private void dealSection(MobileUserModel user, LocalDate startDate, LocalDate endDate, LocalDate endDateFinally, TeamInfoQueryResponse data) {
        List<TeamInfoQueryModel> result = Lists.newArrayList();
        List<MarketingManpower> manpowerList = manpowerRepo.listByDeptCode2(user.getEmployee_major_com());
        //按部代码分组
        Map<String, List<MarketingManpower>> sectionMaps = manpowerList.stream().collect(Collectors.groupingBy(MarketingManpower::getDeptCode3));
        for (Map.Entry<String, List<MarketingManpower>> sectionMap : sectionMaps.entrySet()) {
            TeamInfoQueryModel model = new TeamInfoQueryModel();
            String sectionCode = sectionMap.getKey();
            List<MarketingManpower> sectionManpowerList = sectionMap.getValue();
            LocalDate startDateSelf = startDate;
            LocalDate endDateSelf = endDate;
            //获取主管信息 同时处理健康人力
            MarketingManpower executive = null;
            int healthNum = 0;
            for (MarketingManpower manpower : sectionManpowerList) {
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
                //判断是否健康人力
                boolean isHealth = false;
                do {
                    Double sumFYC = businessRepo.sumFYCByAgentCode(manpower.getAgentCode(), startDateSelf, endDateSelf);
                    if (sumFYC >= 1500){
                        isHealth = true;
                    }
                    startDateSelf = startDateSelf.plusMonths(1);
                    endDateSelf = endDateSelf.plusMonths(1);
                }while(!endDateSelf.isAfter(endDateFinally));
                if (isHealth){
                    healthNum++;
                }
            }
            if (executive == null){
                continue;//无主管，略过
            }
            StarRating starRating = starRatingRepo.findByAgentCode(executive.getAgentCode());
            if (starRating != null) {
                model.setStar(Integer.valueOf(starRating.getFhagentGrade().substring(2)));
            }
            model.setHealth(healthNum);
            model.setName(executive.getDeptName3());
            model.setCode(sectionCode);
            model.setType(1);

            //获取月份数据集合
            LocalDate startDateSelf2 = startDate;
            LocalDate endDateSelf2 = endDate;
            List<TeamInfoQueryMonthModel> monthList = Lists.newArrayList();
            do {
                TeamInfoQueryMonthModel monthModel = new TeamInfoQueryMonthModel();
                monthModel.setMonth(startDate.getMonthValue());
                Double sumFYC = businessRepo.sumFYCByDeptCode3(sectionCode, startDateSelf2, endDateSelf2);
                monthModel.setData(sumFYC == null ? 0 : sumFYC);
                monthList.add(monthModel);
                startDateSelf2 = startDateSelf2.plusMonths(1);
                endDateSelf2 = endDateSelf2.plusMonths(1);
            }while(!endDateSelf2.isAfter(endDateFinally));
            model.setMonthList(monthList);
            result.add(model);
        }
        data.setResult(result);

        //处理月份汇总数据
        LocalDate startDateSelf3 = startDate;
        LocalDate endDateSelf3 = endDate;
        List<TeamInfoQueryMonthModel> summary = Lists.newArrayList();
        do {
            TeamInfoQueryMonthModel monthModel = new TeamInfoQueryMonthModel();
            monthModel.setMonth(startDate.getMonthValue());
            Double sumFYC = businessRepo.sumFYCByDeptCode2(user.getEmployee_major_com(), startDateSelf3, endDateSelf3);
            monthModel.setData(sumFYC == null ? 0 : sumFYC);
            summary.add(monthModel);
            startDateSelf3 = startDateSelf3.plusMonths(1);
            endDateSelf3 = endDateSelf3.plusMonths(1);
        }while(!endDateSelf3.isAfter(endDateFinally));
        data.setSummary(summary);
    }

    /**
     * @Name dealGroup
     * @Author HuoJiaJin
     * @Description 处理所有组数据
     * @Date 2021/2/25 1:58
     * @Param [sectionCode, startDate, endDate, endDateFinally, result]
     * @Return void
     **/
    private void dealGroup(String sectionCode, LocalDate startDate, LocalDate endDate, LocalDate endDateFinally, TeamInfoQueryResponse data) {
        List<TeamInfoQueryModel> result = Lists.newArrayList();
        List<MarketingManpower> manpowerList = manpowerRepo.listByDeptCode3(sectionCode);
        //按组代码分组
        Map<String, List<MarketingManpower>> groupMaps = manpowerList.stream().collect(Collectors.groupingBy(MarketingManpower::getDeptCode4));
        for (Map.Entry<String, List<MarketingManpower>> groupMap : groupMaps.entrySet()) {
            TeamInfoQueryModel model = new TeamInfoQueryModel();
            String groupCode = groupMap.getKey();
            List<MarketingManpower> groupManpowerList = groupMap.getValue();
            LocalDate startDateSelf = startDate;
            LocalDate endDateSelf = endDate;
            //获取主管信息 同时处理健康人力
            MarketingManpower executive = null;
            int healthNum = 0;
            for (MarketingManpower manpower : groupManpowerList) {
                //解析职级,确定主管
                PositionsClass positionsClass;
                try {
                    positionsClass = PositionsClass.valueOf(manpower.getAgentGrade());
                } catch (Exception e) {
                    logger.error("", e);
                    continue;
                }
                if (!PositionsClass.isNotExecutive(positionsClass)){
                    executive = manpower;
                }
                //判断是否健康人力
                boolean isHealth = false;
                do {
                    Double sumFYC = businessRepo.sumFYCByAgentCode(manpower.getAgentCode(), startDateSelf, endDateSelf);
                    if (sumFYC >= 1500){
                        isHealth = true;
                    }
                    startDateSelf = startDateSelf.plusMonths(1);
                    endDateSelf = endDateSelf.plusMonths(1);
                }while(!endDateSelf.isAfter(endDateFinally));
                if (isHealth){
                    healthNum++;
                }
            }
            if (executive == null){
                continue;//无主管，略过
            }
            StarRating starRating = starRatingRepo.findByAgentCode(executive.getAgentCode());
            if (starRating != null) {
                model.setStar(Integer.valueOf(starRating.getFhagentGrade().substring(2)));
            }
            model.setHealth(healthNum);
            model.setName(executive.getDeptName4());
            model.setCode(groupCode);
            model.setType(2);

            //获取月份数据集合
            LocalDate startDateSelf2 = startDate;
            LocalDate endDateSelf2 = endDate;
            List<TeamInfoQueryMonthModel> monthList = Lists.newArrayList();
            do {
                TeamInfoQueryMonthModel monthModel = new TeamInfoQueryMonthModel();
                monthModel.setMonth(startDate.getMonthValue());
                Double sumFYC = businessRepo.sumFYCByDeptCode4(groupCode, startDateSelf2, endDateSelf2);
                monthModel.setData(sumFYC == null ? 0 : sumFYC);
                monthList.add(monthModel);
                startDateSelf2 = startDateSelf2.plusMonths(1);
                endDateSelf2 = endDateSelf2.plusMonths(1);
            }while(!endDateSelf2.isAfter(endDateFinally));
            model.setMonthList(monthList);
            result.add(model);
        }
        data.setResult(result);

        //处理月份汇总数据
        LocalDate startDateSelf3 = startDate;
        LocalDate endDateSelf3 = endDate;
        List<TeamInfoQueryMonthModel> summary = Lists.newArrayList();
        do {
            TeamInfoQueryMonthModel monthModel = new TeamInfoQueryMonthModel();
            monthModel.setMonth(startDate.getMonthValue());
            Double sumFYC = businessRepo.sumFYCByDeptCode3(sectionCode, startDateSelf3, endDateSelf3);
            monthModel.setData(sumFYC == null ? 0 : sumFYC);
            summary.add(monthModel);
            startDateSelf3 = startDateSelf3.plusMonths(1);
            endDateSelf3 = endDateSelf3.plusMonths(1);
        }while(!endDateSelf3.isAfter(endDateFinally));
        data.setSummary(summary);
    }

    /**
     * @Name dealPerson
     * @Author HuoJiaJin
     * @Description 处理个人数据
     * @Date 2021/2/25 1:58
     * @Param [groupCode, startDate, endDate, endDateFinally, result]
     * @Return void
     **/
    private void dealPerson(String groupCode, LocalDate startDate, LocalDate endDate, LocalDate endDateFinally, TeamInfoQueryResponse data) {

        List<TeamInfoQueryModel> result = Lists.newArrayList();
        //查询组下所有数据
        List<MarketingManpower> manpowerList = manpowerRepo.listByDeptCode4(groupCode);
        for (MarketingManpower manpower : manpowerList) {
            String agentCode = manpower.getAgentCode();
            TeamInfoQueryModel model = new TeamInfoQueryModel();
            model.setName(manpower.getName());
            model.setCode(agentCode);
            model.setType(3);
            StarRating starRating = starRatingRepo.findByAgentCode(agentCode);
            if (starRating != null) {
                model.setStar(Integer.valueOf(starRating.getFhagentGrade().substring(2)));
            }
            //获取月份数据集合并判断是否健康人力
            boolean isHealth = false;
            LocalDate startDateSelf = startDate;
            LocalDate endDateSelf = endDate;
            List<TeamInfoQueryMonthModel> monthList = Lists.newArrayList();
            do {
                TeamInfoQueryMonthModel monthModel = new TeamInfoQueryMonthModel();
                monthModel.setMonth(startDate.getMonthValue());
                Double sumFYC = businessRepo.sumFYCByAgentCode(groupCode, startDateSelf, endDateSelf);
                if (sumFYC == null){
                    sumFYC = 0d;
                }
                if (sumFYC >= 1500){
                    isHealth = true;
                }
                monthModel.setData(sumFYC);
                monthList.add(monthModel);
                startDateSelf = startDateSelf.plusMonths(1);
                endDateSelf = endDateSelf.plusMonths(1);
            }while(!endDateSelf.isAfter(endDateFinally));
            model.setMonthList(monthList);
            model.setHealth(isHealth ? 1 : 0);

            model.setGradeName(manpower.getAgentGradeName());
            model.setEntryTime(manpower.getEmployDate().toString());
            result.add(model);
        }
        data.setResult(result);

        //处理月份汇总数据
        LocalDate startDateSelf3 = startDate;
        LocalDate endDateSelf3 = endDate;
        List<TeamInfoQueryMonthModel> summary = Lists.newArrayList();
        do {
            TeamInfoQueryMonthModel monthModel = new TeamInfoQueryMonthModel();
            monthModel.setMonth(startDate.getMonthValue());
            Double sumFYC = businessRepo.sumFYCByDeptCode2(groupCode, startDateSelf3, endDateSelf3);
            monthModel.setData(sumFYC == null ? 0 : sumFYC);
            summary.add(monthModel);
            startDateSelf3 = startDateSelf3.plusMonths(1);
            endDateSelf3 = endDateSelf3.plusMonths(1);
        }while(!endDateSelf3.isAfter(endDateFinally));
        data.setSummary(summary);
    }
}
