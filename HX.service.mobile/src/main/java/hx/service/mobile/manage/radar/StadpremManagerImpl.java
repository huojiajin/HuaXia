package hx.service.mobile.manage.radar;

import hx.service.manage.dao.entity.Business;
import hx.service.manage.dao.repo.jpa.BusinessRepo;
import hx.service.manage.manage.model.CommonResponse;
import hx.service.manage.manage.tools.MyTimeTools;
import hx.service.mobile.manage.AbstractMobileManager;
import hx.service.mobile.manage.model.radar.*;
import org.apache.commons.compress.utils.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @ClassName StadpremManagerImpl
 * @Description 标保相关ManagerImpl
 * @Author HuoJiaJin
 * @Date 2020/6/27 10:42
 * @Version 1.0
 **/
@Service
public class StadpremManagerImpl extends AbstractMobileManager implements StadpremManager {

    @Autowired
    private BusinessRepo businessRepo;

    @Override
    public String getSectionStadprem(SectionStadpremRequest request){
        CommonResponse response = new CommonResponse();
        SectionStadpremResponse data = new SectionStadpremResponse();
        String sectionCode = request.getSectionCode();
        LocalDate now = LocalDate.now();
        LocalDate startDate = now.withDayOfMonth(1);
        LocalDate endDate = startDate.plusMonths(1);
        List<SectionStadpremModel> result = Lists.newArrayList();
        List<Integer> quarter = MyTimeTools.getQuarter(now.getMonthValue());
        boolean isSection = hasText(sectionCode);
        String deptCode = isSection ? sectionCode : request.getGroupCode();
        data.setType(isSection ? "0" : "1");
        for (Integer month : quarter) {
            SectionStadpremModel model = new SectionStadpremModel();
            model.setMonth(month);
            if (month <= now.getMonthValue()) {
                double stadprem = isSection ? businessRepo.sumByDeptCode3(deptCode, startDate, endDate)
                        : businessRepo.sumByDeptCode4(deptCode, startDate, endDate);
                BigDecimal stadpremNumBd = new BigDecimal(String.valueOf(stadprem));
                stadpremNumBd = stadpremNumBd.divide(new BigDecimal("10000"), 0, RoundingMode.HALF_UP);
                int stadpremInt = stadpremNumBd.intValue();
                model.setStadprem(String.valueOf(stadpremInt));
            }
            result.add(model);
        }
        data.setResult(result);
        response.setData(data);
        return response.toJson();
    }

    @Override
    public String getGroupStadprem(GroupStadpremRequest request){
        CommonResponse response = new CommonResponse();
        GroupStadpremResponse data = new GroupStadpremResponse();

        LocalDate now = LocalDate.now();
        LocalDate startDate = now.withMonth(request.getMonth()).withDayOfMonth(1);
        LocalDate endDate = startDate.plusMonths(1);
        List<Business> businessList = businessRepo.listByDeptCode3(request.getSectionCode(), startDate, endDate);
        Map<String, List<Business>> businessMaps = businessList.stream()
                .collect(Collectors.groupingBy(b -> b.getDeptCode4() + "|" + b.getDeptName4()));

        List<GroupStadpremModel> result = Lists.newArrayList();
        for (Map.Entry<String, List<Business>> map : businessMaps.entrySet()) {
            GroupStadpremModel model = new GroupStadpremModel();
            String[] groupArr = map.getKey().split("\\|");
            model.setGroupCode(groupArr[0]);
            model.setName(groupArr[1]);
            double stadprem = map.getValue().stream().mapToDouble(Business::getWrittenStadPrem).sum();
            BigDecimal stadpremNumBd = new BigDecimal(String.valueOf(stadprem));
            stadpremNumBd = stadpremNumBd.divide(new BigDecimal("10000"), 0, RoundingMode.HALF_UP);
            int stadpremInt = stadpremNumBd.intValue();
            model.setStadprem(String.valueOf(stadpremInt));
            result.add(model);
        }
        data.setResult(result);
        response.setData(data);
        return response.toJson();
    }

    @Override
    public String getPersonStadprem(PersonStadpremRequest request){
        CommonResponse response = new CommonResponse();
        PersonStadpremResponse data = new PersonStadpremResponse();
        LocalDate now = LocalDate.now();
        LocalDate startDate = now.withMonth(request.getMonth()).withDayOfMonth(1);
        LocalDate endDate = startDate.plusMonths(1);
        List<Business> businessList = businessRepo.listByDeptCode3(request.getGroupCode(), startDate, endDate);
        List<PersonStadpremModel> result = Lists.newArrayList();
        Map<String, List<Business>> businessMap = businessList.stream()
                .collect(Collectors.groupingBy(b -> b.getAgentCode() + "|" + b.getAgentName()));
        for (Map.Entry<String, List<Business>> map : businessMap.entrySet()) {
            PersonStadpremModel model = new PersonStadpremModel();
            model.setAgentCode(map.getKey().split("\\|")[0]);
            model.setName(map.getKey().split("\\|")[1]);
            Double preStadprem = map.getValue().parallelStream().mapToDouble(Business::getPreStadPrem).sum();
            model.setPreStadprem(preStadprem.toString());
            Double stadPrem = map.getValue().parallelStream().mapToDouble(Business::getWrittenStadPrem).sum();
            model.setStadprem(stadPrem.toString());
            model.setType(stadPrem > 5000 ? 1 : 0);
            result.add(model);
        }
        data.setResult(result);
        response.setData(data);
        return response.toJson();
    }

    @Override
    public String getPersonStadpremDetail(PersonStadpremDetailRequest request){
        CommonResponse response = new CommonResponse();
        PersonStadpremDetailResponse data = new PersonStadpremDetailResponse();
        LocalDate now = LocalDate.now();
        LocalDate startDate = now.withMonth(request.getMonth()).withDayOfMonth(1);
        LocalDate endDate = startDate.plusMonths(1);
        List<Business> businessList = businessRepo.listByAgentCode(request.getAgentCode(), startDate, endDate);
        List<PersonStadpremDetailModel> result = Lists.newArrayList();
        for (Business business : businessList) {
            PersonStadpremDetailModel model = new PersonStadpremDetailModel();
            model.setPolicyNo(business.getPolicyNo());
            model.setCustomerName(business.getApplicantName());
            model.setInsuranceName(business.getProductName());
            model.setStadprem(business.getWrittenStadPrem().toString());
            model.setIsContinue(business.getRnFlag());
            model.setInsuranceTime(business.getIssueDate().toString());
            model.setReceiptTime(business.getConfirmDate().toString());
            model.setVisitTime(business.getCallDate().toString());
            result.add(model);
        }
        data.setResult(result);
        response.setData(data);
        return response.toJson();
    }
}
