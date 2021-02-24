package hx.service.mobile.manage.salary;

import hx.base.core.dao.dict.salary.PrizeInfluenceType;
import hx.base.core.dao.entity.salary.SalaryAlert;
import hx.base.core.dao.entity.salary.SalaryAlertInfluence;
import hx.base.core.dao.repo.jpa.salary.SalaryAlertInfluenceRepo;
import hx.base.core.dao.repo.jpa.salary.SalaryAlertRepo;
import hx.base.core.dao.repo.request.common.Pagination;
import hx.base.core.dao.repo.request.salary.SalaryAlertPageRequest;
import hx.base.core.manage.model.CommonResponse;
import hx.service.mobile.manage.common.AbstractMobileManager;
import hx.service.mobile.model.salary.SalaryQueryInfluenceModel;
import hx.service.mobile.model.salary.SalaryQueryRequest;
import hx.service.mobile.model.salary.SalaryQueryResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @ClassName: SalaryManagerImpl
 * @Description: 追踪支持相关ManagerImpl
 * @Author HuoJiaJin
 * @Date 2021/2/24 20:43
 * @Version 1.0
 **/
@Service
public class SalaryManagerImpl extends AbstractMobileManager implements SalaryManager {

    @Autowired
    private SalaryAlertRepo alertRepo;
    @Autowired
    private SalaryAlertInfluenceRepo influenceRepo;

    @Override
    public String query(SalaryQueryRequest request){
        CommonResponse response = new CommonResponse();
        SalaryAlertPageRequest pageRequest = new SalaryAlertPageRequest();
        BeanUtils.copyProperties(request, pageRequest);
        pageRequest.setMonth(LocalDate.parse(request.getMonth() + "-01"));
        Pagination page = alertRepo.page(pageRequest);
        page.convertResult(this::convert);
        response.setData(page);
        return response.toJson();
    }

    private SalaryQueryResponse convert(SalaryAlert entity){
        SalaryQueryResponse model = new SalaryQueryResponse();
        BeanUtils.copyProperties(entity, model);
        model.setType(entity.getType().name());
        //查询影响因素
        List<SalaryAlertInfluence> influenceList = influenceRepo.listByAlertId(entity.getId());
        List<SalaryQueryInfluenceModel> itemList = influenceList.stream().map(i -> {
            SalaryQueryInfluenceModel item = new SalaryQueryInfluenceModel();
            item.setItemName(i.getType().getValue());
            if (i.getType() == PrizeInfluenceType.CONTINUERATE) {
                BigDecimal num = new BigDecimal(i.getNum()).multiply(new BigDecimal("100")).setScale(0, BigDecimal.ROUND_HALF_UP);
                item.setItemValue(num.toString());
            } else {
                item.setItemValue(String.valueOf(i.getNum()));
            }
            return item;
        }).collect(Collectors.toList());
        model.setItemList(itemList);
        return model;
    }
}
