package hx.service.mobile.manage.radar;

import hx.base.core.dao.entity.MarketingManpower;
import hx.base.core.dao.entity.StarRating;
import hx.base.core.dao.repo.jpa.BusinessRepo;
import hx.base.core.dao.repo.jpa.MarketingManpowerRepo;
import hx.base.core.dao.repo.jpa.StarRatingRepo;
import hx.base.core.manage.model.CommonResponse;
import hx.base.core.manage.tools.MyTimeTools;
import hx.service.mobile.manage.AbstractMobileManager;
import hx.service.mobile.manage.model.radar.StadpremMonthModel;
import hx.service.mobile.manage.model.radar.star.*;
import org.apache.commons.compress.utils.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @ClassName StarPowerManagerImpl
 * @Description 星级人力
 * @Author HuoJiaJin
 * @Date 2020/6/27 15:27
 * @Version 1.0
 **/
@Service
public class StarPowerManagerImpl extends AbstractMobileManager implements StarPowerManager {

    @Autowired
    private MarketingManpowerRepo manpowerRepo;
    @Autowired
    private StarRatingRepo ratingRepo;
    @Autowired
    private BusinessRepo businessRepo;

    @Override
    public String getStarList(StarPowerRequest request){
        CommonResponse response = new CommonResponse();
        StarPowerResponse data = new StarPowerResponse();
        boolean isSection = hasText(request.getSectionCode());
        data.setType(isSection ? 0 : 1);
        List<MarketingManpower> manpowerList = isSection ?
                manpowerRepo.listByDeptCode3(request.getSectionCode()) : manpowerRepo.listByDeptCode4(request.getGroupCode());
        //查找四星人员
        List<String> agentCodes = manpowerList.stream().map(m -> m.getAgentCode()).collect(Collectors.toList());
        List<StarRating> starRatingList = ratingRepo.listByAgentCodes(agentCodes);
        Long count = starRatingList.stream().filter(s -> Integer.valueOf(s.getFhagentGrade().substring(2)) >= 4).count();
        //查找预四星人员
        List<String> preList = starRatingList.stream().filter(s -> Integer.valueOf(s.getFhagentGrade().substring(2)) == 3)
                .map(s -> s.getAgentCode()).collect(Collectors.toList());

        data.setCount(count.intValue());
        LocalDate now = LocalDate.now();
        LocalDate startDate = now.withDayOfMonth(1);
        LocalDate endDate = startDate.plusMonths(1);
        int monthValue = now.getMonthValue();
        List<Integer> quarter = MyTimeTools.getQuarter(monthValue);

        //自然季度前几个月业绩达到10000
        int preCount = 0;
        for (String agentCode : preList) {
            boolean hasReach = isPreFourStar(quarter, monthValue, agentCode, startDate, endDate);
            if (hasReach) preCount++;
        }
        data.setPreCount(preCount);
        response.setData(data);
        return response.toJson();
    }

    @Override
    public String getStarDetail(StarPowerDetailRequest request){
        CommonResponse response = new CommonResponse();
        StarPowerDetailResponse data = new StarPowerDetailResponse();
        List<StarPowerDetailModel> result = Lists.newArrayList();
        boolean isSection = hasText(request.getSectionCode());
        //搜索业绩时间范围
        LocalDate now = LocalDate.now();
        LocalDate startDate = now.withDayOfMonth(1);
        LocalDate endDate = startDate.plusMonths(1);
        int monthValue = now.getMonthValue();
        List<Integer> quarter = MyTimeTools.getQuarter(monthValue);

        List<MarketingManpower> manpowerList = isSection ?
                manpowerRepo.listByDeptCode3(request.getSectionCode()) : manpowerRepo.listByDeptCode4(request.getGroupCode());
        List<String> agentCodes = manpowerList.stream().map(m -> m.getAgentCode()).collect(Collectors.toList());
        List<StarRating> starRatingList = ratingRepo.listByAgentCodes(agentCodes);
        if (request.getType() == 0) {//四星及以上人员
            //查找四星人员
            Map<String, StarRating> fourStarMaps = starRatingList.stream()
                    .filter(s -> Integer.valueOf(s.getFhagentGrade().substring(2)) >= 4)
                    .collect(Collectors.toMap(StarRating::getAgentCode, Function.identity()));
            for (MarketingManpower manpower : manpowerList) {
                StarRating starRating = fourStarMaps.get(manpower.getAgentCode());
                if (starRating == null) continue;
                StarPowerDetailModel model = new StarPowerDetailModel();
                model.setName(manpower.getName());
                model.setGroup(manpower.getDeptName4());
                model.setStar(Integer.valueOf(starRating.getFhagentGrade().substring(2)));
                List<StadpremMonthModel> stadpremList = Lists.newArrayList();
                for (Integer month : quarter) {
                    StadpremMonthModel stadModel = new StadpremMonthModel();
                    stadModel.setMonth(month);
                    if (month <= monthValue){
                        Double sum = businessRepo.sumByAgentCode(manpower.getAgentCode(), startDate, endDate);
                        if (sum == null) sum = 0d;
                        stadModel.setStadprem(sum.toString());
                    }
                    stadpremList.add(stadModel);
                }
                model.setStadpremList(stadpremList);
                result.add(model);
            }
        }else {//查找预四星人员
            Map<String, StarRating> preFourStarMaps = starRatingList.stream()
                    .filter(s -> Integer.valueOf(s.getFhagentGrade().substring(2)) == 3)
                    .collect(Collectors.toMap(StarRating::getAgentCode, Function.identity()));
            for (MarketingManpower manpower : manpowerList) {
                StarRating starRating = preFourStarMaps.get(manpower.getAgentCode());
                if (starRating == null) continue;
                //判断是否预四星
                boolean hasReach = isPreFourStar(quarter, monthValue, manpower.getAgentCode(), startDate, endDate);
                if (hasReach) {
                    StarPowerDetailModel model = new StarPowerDetailModel();
                    model.setName(manpower.getName());
                    model.setGroup(manpower.getDeptName4());
                    model.setStar(Integer.valueOf(starRating.getFhagentGrade().substring(2)));
                    List<StadpremMonthModel> stadpremList = Lists.newArrayList();
                    for (Integer month : quarter) {
                        StadpremMonthModel stadModel = new StadpremMonthModel();
                        stadModel.setMonth(month);
                        if (month <= monthValue){
                            Double sum = businessRepo.sumByAgentCode(manpower.getAgentCode(), startDate, endDate);
                            if (sum == null) sum = 0d;
                            stadModel.setStadprem(sum.toString());
                        }
                        stadpremList.add(stadModel);
                    }
                    result.add(model);
                }
            }
        }
        data.setResult(result);
        response.setData(data);
        return response.toJson();
    }

    private boolean isPreFourStar(List<Integer> quarter, int monthValue, String agentCode, LocalDate startDate, LocalDate endDate){
        boolean hasReach = true;
        for (Integer month : quarter) {
            if (month > monthValue) break;
            if (!hasReach) break;
            if (month < monthValue){
                Double sum = businessRepo.sumByAgentCode(agentCode, startDate, endDate);
                if (sum == null) sum = 0d;
                if (sum < 10000){
                    hasReach = false;
                }
            }
        }
        return hasReach;
    }
}
