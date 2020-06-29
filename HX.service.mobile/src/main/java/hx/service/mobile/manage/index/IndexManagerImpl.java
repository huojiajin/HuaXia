package hx.service.mobile.manage.index;

import hx.base.core.dao.dict.ErrorType;
import hx.base.core.dao.dict.PositionsClass;
import hx.base.core.dao.dict.PositionsType;
import hx.base.core.dao.entity.Incubation;
import hx.base.core.dao.entity.MarketingManpower;
import hx.base.core.dao.repo.jpa.IncubationRepo;
import hx.base.core.dao.repo.jpa.MarketingManpowerRepo;
import hx.base.core.manage.model.CommonResponse;
import hx.service.mobile.manage.AbstractMobileManager;
import hx.service.mobile.manage.model.common.MobileCommonRequest;
import hx.service.mobile.manage.model.index.*;
import hx.service.mobile.manage.model.login.MobileUserModel;
import org.apache.commons.compress.utils.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @ClassName IndexManagerImpl
 * @Description 首页相关ManagerImpl
 * @Author HuoJiaJin
 * @Date 2020/6/27 10:30
 * @Version 1.0
 **/
@Service
public class IndexManagerImpl extends AbstractMobileManager implements IndexManager {

    @Autowired
    private MarketingManpowerRepo manpowerRepo;
    @Autowired
    private IncubationRepo incubationRepo;

    @Override
    public String getSectionList(MobileCommonRequest request){
        CommonResponse response = new CommonResponse();
        MobileUserModel user = getUser(request.getToken());
        if (user == null) return response.setError(ErrorType.NOLOGIN);
        SectionListResponse data = new SectionListResponse();
        PositionsType positionsType;
        try {
            PositionsClass positionsClass = PositionsClass. valueOf(user.getPosition_code());
            positionsType = PositionsType.fromClass(positionsClass);
        } catch (Exception e) {
            logger.error("", e);
            return response.setError(ErrorType.CONVERT);
        }
        if (positionsType == PositionsType.BM || positionsType == PositionsType.AS){
            List<SectionModel> sectionList = Lists.newArrayList();
            SectionModel section = new SectionModel(user.getEmployee_part_com(), user.getEmployee_part_com_name());
            sectionList.add(section);
            //查找育成人员
            List<Incubation> incubationList = incubationRepo.listByRearAgentCode(user.getEmployee_code());
            List<String> agentCodes = incubationList.stream().map(Incubation::getAgentCode).collect(Collectors.toList());
            List<MarketingManpower> manpowers = manpowerRepo.listByAgentCodes(agentCodes);

            //筛选育成部
            List<MarketingManpower> rearList = manpowers.parallelStream().filter(m -> m.getOutworkDate() == null
                    || m.getOutworkDate().isAfter(LocalDate.now())).collect(Collectors.toList());
            rearList = rearList.stream().filter(m -> {
                PositionsClass positionsClass = PositionsClass.valueOf(m.getAgentGrade());
                try {
                    PositionsType type = PositionsType.fromClass(positionsClass);
                    return type == PositionsType.AS || type == PositionsType.BM;
                } catch (InterruptedException e) {
                    logger.error("", e);
                    return false;
                }
            }).collect(Collectors.toList());
            rearList.forEach(m -> {
                SectionModel rearSection = new SectionModel(m.getDeptCode3(), m.getDeptName3());
                sectionList.add(rearSection);
            });
            //判断是否有育成组
            boolean hasRearGroup = false;
            for (MarketingManpower manpower : rearList) {
                PositionsClass positionsClass = PositionsClass.valueOf(manpower.getAgentGrade());
                try {
                    PositionsType type = PositionsType.fromClass(positionsClass);
                    if (type == PositionsType.BC){
                        hasRearGroup = true;
                        break;
                    }
                } catch (InterruptedException e) {
                    logger.error("", e);
                }
            }
            if (hasRearGroup){
                sectionList.add(new SectionModel("0", "育成组"));
            }
            data.setSectionList(sectionList);
        }
        response.setData(data);
        return response.toJson();
    }

    @Override
    public String getGroupList(GroupListReqeust request){
        CommonResponse response = new CommonResponse();
        MobileUserModel user = getUser(request.getToken());
        if (user == null) return response.setError(ErrorType.NOLOGIN);
        GroupListResponse data = new GroupListResponse();
        PositionsType positionsType;
        try {
            PositionsClass positionsClass = PositionsClass. valueOf(user.getPosition_code());
            positionsType = PositionsType.fromClass(positionsClass);
        } catch (Exception e) {
            logger.error("", e);
            return response.setError(ErrorType.CONVERT);
        }
        List<GroupModel> groupList = Lists.newArrayList();
        String sectionCode = request.getSectionCode();
        if (!hasText(sectionCode)){
            GroupModel group = new GroupModel(user.getEmployee_group_com(), user.getEmployee_group_com_name());
            groupList.add(group);
        }else {
            if (!sectionCode.equals("0")) {//若部名称不为育成组
                GroupModel defaultModel = new GroupModel("0", "全部");
                groupList.add(defaultModel);
                //查找本部下所有组
                List<MarketingManpower> manpowers = manpowerRepo.listByDeptCode3(user.getEmployee_part_com());
                Map<String, List<MarketingManpower>> groupMap = manpowers.stream()
                        .collect(Collectors.groupingBy(m -> m.getDeptCode4() + "|" + m.getDeptName4()));
                for (String groupStr : groupMap.keySet()) {
                    String[] groupArr = groupStr.split("\\|");
                    GroupModel group = new GroupModel(groupArr[0], groupArr[1]);
                    groupList.add(group);
                }
            } else {
                //查找育成人员
                List<Incubation> incubationList = incubationRepo.listByRearAgentCode(user.getEmployee_code());
                List<String> agentCodes = incubationList.stream().map(Incubation::getAgentCode).collect(Collectors.toList());
                List<MarketingManpower> manpowers = manpowerRepo.listByAgentCodes(agentCodes);
                //筛选育成组
                List<MarketingManpower> rearList = manpowers.parallelStream().filter(m -> m.getOutworkDate() == null
                        || m.getOutworkDate().isAfter(LocalDate.now())).filter(m -> {
                    PositionsClass positionsClass = PositionsClass.valueOf(m.getAgentGrade());
                    try {
                        PositionsType type = PositionsType.fromClass(positionsClass);
                        return type == PositionsType.BC;
                    } catch (InterruptedException e) {
                        logger.error("", e);
                        return false;
                    }
                }).collect(Collectors.toList());
                rearList.forEach(m -> {
                    GroupModel rearGroup = new GroupModel(m.getDeptCode4(), m.getDeptName4());
                    groupList.add(rearGroup);
                });
            }
        }
        data.setGroupList(groupList);
        response.setData(data);
        return response.toJson();
    }
}
