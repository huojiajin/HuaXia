package hx.service.mobile.manage.radar;

import hx.base.core.dao.entity.Attendance;
import hx.base.core.dao.entity.MarketingManpower;
import hx.base.core.dao.repo.jpa.AttendanceRepo;
import hx.base.core.dao.repo.jpa.MarketingManpowerRepo;
import hx.base.core.dao.repo.request.MarketingManpowerPageRequest;
import hx.base.core.dao.repo.request.common.Pagination;
import hx.base.core.manage.model.CommonResponse;
import hx.base.core.manage.tools.MyTimeTools;
import hx.service.mobile.manage.AbstractMobileManager;
import hx.service.mobile.manage.model.radar.attend.*;
import org.apache.commons.compress.utils.Lists;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @ClassName AttendanceManagerImpl
 * @Description 出勤人力ManagerImpl
 * @Author HuoJiaJin
 * @Date 2020/6/27 16:48
 * @Version 1.0
 **/
@Service
public class AttendanceManagerImpl extends AbstractMobileManager implements AttendanceManager {

    @Autowired
    private AttendanceRepo repo;
    @Autowired
    private MarketingManpowerRepo manpowerRepo;

    @Override
    public String getSectionAttend(SectionAttendRequest request){
        CommonResponse response = new CommonResponse();
        SectionAttendResponse data = new SectionAttendResponse();
        boolean isSection = hasText(request.getSectionCode());
        data.setType(isSection ? 0 : 1);
        LocalDate now = LocalDate.now();
        LocalDate startDate = now.withDayOfMonth(1);
        LocalDate endDate = startDate.plusMonths(1);
        int monthValue = now.getMonthValue();
        List<Integer> quarter = MyTimeTools.getQuarter(monthValue);
        List<SectionAttendModel> result = Lists.newArrayList();
        for (Integer month : quarter) {
            SectionAttendModel model = new SectionAttendModel();
            model.setMonth(month);
            if (month <= monthValue) {
                List<MarketingManpower> manpowerList = isSection ? manpowerRepo.listByDeptCode3(request.getSectionCode())
                        : manpowerRepo.listByDeptCode4(request.getGroupCode());
                List<String> agentCodes = manpowerList.stream().map(m -> m.getAgentCode()).collect(Collectors.toList());
                List<Attendance> attendanceList = repo.listByAgentCodes(agentCodes, startDate, endDate);
                Map<String, List<Attendance>> attendanceMaps = attendanceList.stream()
                        .collect(Collectors.groupingBy(Attendance::getStaffCode));
                int freAttend = 0;
                for (Map.Entry<String, List<Attendance>> map : attendanceMaps.entrySet()) {
                    if (map.getValue().size() >= 15){
                        freAttend++;
                    }
                }
                model.setAttendNum(freAttend);
            }
            result.add(model);
        }
        data.setResult(result);
        response.setData(data);
        return response.toJson();
    }

    @Override
    public String getGroupAttend(GroupAttendRequest request){
        CommonResponse response = new CommonResponse();
        GroupAttendResponse data = new GroupAttendResponse();
        LocalDate startDate = LocalDate.now().withMonth(request.getMonth()).withDayOfMonth(1);
        LocalDate endDate = startDate.plusMonths(1);
        List<MarketingManpower> manpowerList = manpowerRepo.listByDeptCode3(request.getSectionCode());
        Map<String, List<MarketingManpower>> manPowerMaps = manpowerList.stream()
                .collect(Collectors.groupingBy(m -> m.getDeptCode4() + "|" + m.getDeptName4()));
        List<GroupAttendModel> result = Lists.newArrayList();
        for (Map.Entry<String, List<MarketingManpower>> map : manPowerMaps.entrySet()) {
            GroupAttendModel model = new GroupAttendModel();
            String[] groupArr = map.getKey().split("\\|");
            model.setGroupCode(groupArr[0]);
            model.setName(groupArr[1]);
            List<String> agentCodes = map.getValue().stream().map(m -> m.getAgentCode()).collect(Collectors.toList());
            List<Attendance> attendanceList = repo.listByAgentCodes(agentCodes, startDate, endDate);
            Map<String, List<Attendance>> attendMaps = attendanceList.stream().collect(Collectors.groupingBy(Attendance::getStaffCode));
            model.setAttendNum(attendMaps.size());
            int freAttendNum = 0;
            for (Map.Entry<String, List<Attendance>> attendMap : attendMaps.entrySet()) {
                if(attendMap.getValue().size() >= 15){
                    freAttendNum++;
                }
            }
            model.setFreAttendNum(freAttendNum);
            result.add(model);
        }
        data.setResult(result);
        response.setData(data);
        return response.toJson();
    }

    @Override
    public String getAttendDetail(PersonAttendRequest request){
        CommonResponse response = new CommonResponse();
        PersonAttendResponse data = new PersonAttendResponse();
        LocalDate startDate = LocalDate.now().withMonth(request.getMonth()).withDayOfMonth(1);
        LocalDate endDate = startDate.plusMonths(1);
        MarketingManpowerPageRequest pageRequest = new MarketingManpowerPageRequest();
        BeanUtils.copyProperties(request, pageRequest);
        pageRequest.setDeptCode4(request.getGroupCode());
        Pagination pagn = manpowerRepo.page(pageRequest);
        BeanUtils.copyProperties(pagn, data);
        List<PersonAttendModel> result = Lists.newArrayList();
        for (MarketingManpower manpower : pagn.getResult(MarketingManpower.class)) {
            PersonAttendModel model = new PersonAttendModel();
            model.setName(manpower.getName());
            model.setEntryTime(manpower.getEmployDate().toString());
            //查询出勤情况
            List<Attendance> attendances = repo.listByAgentCode(manpower.getAgentCode(), startDate, endDate);
            int attendNum = attendances.size();
            model.setAttendDay(attendNum);
            model.setFreAttend(attendNum >= 15);
            result.add(model);
        }
        data.setResult(result);
        response.setData(data);
        return response.toJson();
    }
}
