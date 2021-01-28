package hx.service.manage.manage.staff;

import hx.base.core.dao.entity.MarketingManpower;
import hx.base.core.dao.entity.StarRating;
import hx.base.core.dao.repo.jpa.AttendanceRepo;
import hx.base.core.dao.repo.jpa.BusinessRepo;
import hx.base.core.dao.repo.jpa.MarketingManpowerRepo;
import hx.base.core.dao.repo.jpa.StarRatingRepo;
import hx.base.core.dao.repo.request.MarketingManpowerPageRequest;
import hx.base.core.dao.repo.request.common.Pagination;
import hx.base.core.manage.model.CommonResponse;
import hx.base.core.manage.tools.MyTimeTools;
import hx.service.manage.manage.AbstractManager;
import hx.service.manage.manage.model.staff.situation.SituationQueryRequest;
import hx.service.manage.manage.model.staff.situation.SituationQueryResponse;
import hx.service.manage.manage.model.staff.situation.SituationStadpremModel;
import org.apache.commons.compress.utils.Lists;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

/**
 * @ClassName: SituationManagerImpl
 * @Description: 人员情况统计Manager
 * @Author huojiajin
 * @Date 2021/1/29 0:38
 * @Version 1.0
 **/
@Service
public class SituationManagerImpl extends AbstractManager implements SituationManager {

    @Autowired
    private MarketingManpowerRepo manpowerRepo;
    @Autowired
    private BusinessRepo businessRepo;
    @Autowired
    private AttendanceRepo attendanceRepo;
    @Autowired
    private StarRatingRepo starRatingRepo;

    @Override
    public String query(SituationQueryRequest request){
        CommonResponse response = new CommonResponse();
        MarketingManpowerPageRequest pageRequest = new MarketingManpowerPageRequest();
        BeanUtils.copyProperties(request, pageRequest);
        pageRequest.setDeptCode1(request.getCampName());
        pageRequest.setDeptCode2(request.getChiefName());
        pageRequest.setDeptCode3(request.getSectionName());
        pageRequest.setDeptCode4(request.getGroupName());
        pageRequest.setAgentCode(request.getEmployeeNum());
        Pagination page = manpowerRepo.page(pageRequest);
//        page.convertResult()
        return response.toJson();
    }

    private SituationQueryResponse convertModel(MarketingManpower manpower){
        SituationQueryResponse model = new SituationQueryResponse();
        String agentCode = manpower.getAgentCode();
        model.setEmployeeNum(agentCode);
        model.setName(manpower.getName());
        model.setCampName(manpower.getDeptName1());
        model.setChiefName(manpower.getDeptName2());
        model.setSectionName(manpower.getDeptName3());
        model.setGroupName(manpower.getDeptName4());
        model.setGradeName(manpower.getAgentGradeName());
        model.setEntryTime(manpower.getEmployDate().toString());
        //获取星级
        StarRating starRating = starRatingRepo.findByAgentCode(agentCode);
        if (starRating == null){
            model.setStar("0");
        }else {
            model.setStar(starRating.getFhagentGrade());
        }
        //业绩
        List<SituationStadpremModel> stadpremList = Lists.newArrayList();
        List<LocalDate> threeMonths = MyTimeTools.getThreeMonths(LocalDate.now());
        for (LocalDate month : threeMonths) {
            SituationStadpremModel stadpremModel = new SituationStadpremModel();
            stadpremModel.setMonth(month.getMonthValue());
            LocalDate startOfMonth = LocalDate.of(month.getYear(), month.getMonth(), 1);
            Double stadpremSum = businessRepo.sumByAgentCode(agentCode, startOfMonth, startOfMonth.plusMonths(1));
            stadpremModel.setStadprem(String.valueOf(stadpremSum));
        }
        //出勤 TODO

        return model;
    }
}
