package hx.service.mobile.manage.radar;

import hx.base.core.dao.entity.hualife.Business;
import hx.base.core.dao.entity.hualife.MarketingManpower;
import hx.base.core.dao.repo.jpa.hualife.BusinessRepo;
import hx.base.core.dao.repo.jpa.hualife.MarketingManpowerRepo;
import hx.base.core.dao.repo.request.hualife.MarketingManpowerPageRequest;
import hx.base.core.dao.repo.request.common.Pagination;
import hx.base.core.dao.repo.request.hualife.BusinessPageRequest;
import hx.base.core.manage.model.CommonResponse;
import hx.base.core.manage.tools.MyTimeTools;
import hx.service.mobile.manage.common.AbstractMobileManager;
import hx.service.mobile.model.radar.stadprem.*;
import org.apache.commons.compress.utils.Lists;
import org.springframework.beans.BeanUtils;
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
    @Autowired
    private MarketingManpowerRepo manpowerRepo;

    @Override
    public String getSectionStadprem(SectionStadpremRequest request){
        CommonResponse response = new CommonResponse();
        SectionStadpremResponse data = new SectionStadpremResponse();
        String sectionCode = request.getSectionCode();
        LocalDate now = LocalDate.now();
        List<SectionStadpremModel> result = Lists.newArrayList();
        List<Integer> quarter = MyTimeTools.getQuarter(now.getMonthValue());
        boolean isSection = hasText(sectionCode);
        String deptCode = isSection ? sectionCode : request.getGroupCode();
        data.setType(isSection ? 0 : 1);
        for (Integer month : quarter) {
            SectionStadpremModel model = new SectionStadpremModel();
            model.setMonth(month);
            if (month <= now.getMonthValue()) {
                LocalDate startDate = now.withMonth(month).withDayOfMonth(1);
                LocalDate endDate = startDate.plusMonths(1);
                Double stadprem = isSection ? businessRepo.sumByDeptCode3(deptCode, startDate, endDate)
                        : businessRepo.sumByDeptCode4(deptCode, startDate, endDate);
                if (stadprem == null) stadprem = 0d;
                BigDecimal stadpremNumBd = new BigDecimal(String.valueOf(stadprem));
                stadpremNumBd = stadpremNumBd.divide(new BigDecimal("10000"), 2, RoundingMode.HALF_UP);
                model.setStadprem(stadpremNumBd.compareTo(new BigDecimal("0")) == 0 ? "0" :
                        String.valueOf(stadpremNumBd.doubleValue()));
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
            stadpremNumBd = stadpremNumBd.divide(new BigDecimal("10000"), 2, RoundingMode.HALF_UP);
            model.setStadprem(stadpremNumBd.compareTo(new BigDecimal("0")) == 0 ? "0" :
                    String.valueOf(stadpremNumBd.doubleValue()));
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
        List<Business> businessList = businessRepo.listByDeptCode4(request.getGroupCode(), startDate, endDate);
        List<PersonStadpremModel> result = Lists.newArrayList();
        Map<String, List<Business>> businessMap = businessList.stream()
                .collect(Collectors.groupingBy(Business::getAgentCode));
        MarketingManpowerPageRequest pageRequest = new MarketingManpowerPageRequest();
        BeanUtils.copyProperties(request,pageRequest);
        pageRequest.setDeptCode4(request.getGroupCode());
        Pagination page = manpowerRepo.page(pageRequest);
        BeanUtils.copyProperties(page, data);
        for (MarketingManpower manpower : page.getResult(MarketingManpower.class)) {
            PersonStadpremModel model = new PersonStadpremModel();
            String agentCode = manpower.getAgentCode();
            model.setAgentCode(agentCode);
            model.setName(manpower.getName());
            List<Business> businesses = businessMap.get(agentCode);
            if (isEmpty(businesses)){
                model.setPreStadprem("0");
                model.setStadprem("0");
                model.setType(0);
            }else{
                Double preStadprem = businesses.parallelStream().mapToDouble(Business::getPreStadPrem).sum();
                model.setPreStadprem(preStadprem.toString());
                Double stadPrem = businesses.parallelStream().mapToDouble(Business::getWrittenStadPrem).sum();
                model.setStadprem(stadPrem.toString());
                model.setType(stadPrem > 5000 ? 1 : 0);
            }
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
        BusinessPageRequest pageRequest = new BusinessPageRequest();
        BeanUtils.copyProperties(request, pageRequest);
        pageRequest.setIssueDateStart(startDate);
        pageRequest.setIssueDateEnd(endDate);
        Pagination page = businessRepo.page(pageRequest);
        BeanUtils.copyProperties(page, data);
        List<PersonStadpremDetailModel> result = Lists.newArrayList();
        for (Business business : page.getResult(Business.class)) {
            PersonStadpremDetailModel model = new PersonStadpremDetailModel();
            model.setPolicyNo(business.getPolicyNo());
            model.setCustomerName(business.getApplicantName());
            model.setInsuranceName(business.getProductName());
            model.setStadprem(business.getWrittenStadPrem().toString());
            model.setIsContinue(business.getRnFlag());
            model.setInsuranceTime(business.getIssueDate().toString());
            model.setReceiptTime(business.getConfirmDate() == null ? "" : business.getConfirmDate().toString());
            model.setVisitTime(business.getCallDate() == null ? "" : business.getCallDate().toString());
            result.add(model);
        }
        data.setResult(result);
        response.setData(data);
        return response.toJson();
    }
}
