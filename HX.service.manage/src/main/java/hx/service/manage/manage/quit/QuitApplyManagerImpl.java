package hx.service.manage.manage.quit;

import com.google.common.collect.Maps;
import hx.base.core.dao.dict.acl.ErrorType;
import hx.base.core.dao.dict.quit.QuitApplyApprovalType;
import hx.base.core.dao.dict.quit.QuitApplyFlowType;
import hx.base.core.dao.dict.quit.QuitApplyStatus;
import hx.base.core.dao.entity.acl.MobileUser;
import hx.base.core.dao.entity.hualife.MarketingManpower;
import hx.base.core.dao.entity.quit.QuitApply;
import hx.base.core.dao.entity.quit.QuitApplyFlow;
import hx.base.core.dao.repo.jpa.acl.MobileUserRepo;
import hx.base.core.dao.repo.jpa.hualife.MarketingManpowerRepo;
import hx.base.core.dao.repo.jpa.quit.QuitApplyFlowRepo;
import hx.base.core.dao.repo.jpa.quit.QuitApplyRepo;
import hx.base.core.dao.repo.request.common.Pagination;
import hx.base.core.dao.repo.request.quit.QuitApplyPageRequest;
import hx.base.core.manage.model.CommonResponse;
import hx.base.core.manage.tools.MyTimeTools;
import hx.base.core.manage.tools.PdfUtils;
import hx.service.manage.manage.common.AbstractManager;
import hx.service.manage.model.common.CommonTemplateResponse;
import hx.service.manage.model.quit.QuitApplyIdRequest;
import hx.service.manage.model.quit.QuitApplyQueryRequest;
import hx.service.manage.model.quit.QuitApplyQueryResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @ClassName: QuitApplyManagerImpl
 * @Description: 离职申请Manager
 * @Author HuoJiaJin
 * @Date 2021/2/2 23:24
 * @Version 1.0
 **/
@Service
public class QuitApplyManagerImpl extends AbstractManager implements QuitApplyManager{

    @Autowired
    private QuitApplyRepo applyRepo;
    @Autowired
    private QuitApplyFlowRepo applyFlowRepo;
    @Autowired
    private MarketingManpowerRepo manpowerRepo;
    @Autowired
    private MobileUserRepo mobileUserRepo;

    @Override
    public String query(QuitApplyQueryRequest request){
        CommonResponse response = new CommonResponse();
        QuitApplyPageRequest pageRequest = new QuitApplyPageRequest();
        pageRequest.setAgentCode(request.getEmployeeNum());
        if (request.getStatus() != 0) {
            try {
                pageRequest.setStatus(QuitApplyStatus.fromCode(request.getStatus()));
            } catch (InterruptedException e) {
                return response.setError(ErrorType.CONVERT, e.getMessage());
            }
        }
        Pagination page = applyRepo.page(pageRequest);
        page.convertResult(this::convertModel);
        response.setData(page);
        return response.toJson();
    }

    private QuitApplyQueryResponse convertModel(QuitApply entity){
        QuitApplyQueryResponse model = new QuitApplyQueryResponse();
        BeanUtils.copyProperties(entity, model);
        model.setEmployeeNum(entity.getAgentCode());
        model.setApplyTime(MyTimeTools.timeToStr(entity.getApplyTime()));
        model.setStatus(entity.getStatus().getCode());
        return model;
    }

    @Override
    public String export(QuitApplyIdRequest request){
        CommonResponse response = new CommonResponse();
        //校验申请是否存在
        Optional<QuitApply> op = applyRepo.findById(request.getApplyId());
        if (!op.isPresent()){
            return response.setError(ErrorType.VALID, "未找到对应申请");
        }
        QuitApply apply = op.get();
        MarketingManpower manpower = manpowerRepo.findByAgentCode(apply.getAgentCode());
        if (manpower == null){
            return response.setError(ErrorType.VALID, "未找到申请人员信息");
        }
        //拼装数据
        Map<String, String> dataMap = Maps.newHashMap();
        dataMap.put("campName", apply.getCampName());
        LocalDateTime applyTime = apply.getApplyTime();
        dataMap.put("applyTime", MyTimeTools.timeToStr(applyTime, "yyyy-MM-dd"));
        dataMap.put("gradeName", manpower.getAgentGradeName());
        dataMap.put("name", apply.getName());
        dataMap.put("agentCode", apply.getAgentCode());
        if (hasText(manpower.getAgentMobile())){
            dataMap.put("mobile", manpower.getAgentMobile());
        }else {//如果人员表没有数据，则从手机端用户数据查找
            MobileUser mobileUser = mobileUserRepo.findByAgentCode(apply.getAgentCode());
            if (mobileUser != null) {
                dataMap.put("mobile", mobileUser.getMobile());
            }
        }
        dataMap.put("reason", apply.getReason());
        dataMap.put("sign", new String(apply.getSignImg()));
        dataMap.put("year", String.valueOf(applyTime.getYear()));
        dataMap.put("month", String.valueOf(applyTime.getMonthValue()));
        dataMap.put("day", String.valueOf(applyTime.getDayOfMonth()));
        //拼装过程数据
        List<QuitApplyFlow> flowList = applyFlowRepo.listByApplyId(apply.getId());
        List<QuitApplyFlow> approvaledList = flowList.stream().filter(f -> f.getEndTime() != null).collect(Collectors.toList());
        int index = 1;
        for (int i = 0; i < approvaledList.size(); i++) {
            QuitApplyFlow flow = approvaledList.get(i);
            QuitApplyFlowType flowType = apply.getFlowType();
            if (i == 0) {//获取第一个流程时
                if (flowType == QuitApplyFlowType.SECTIONMANAGER) {//如果审批流程类别为部经理
                    if (flow.getApprovalType() != QuitApplyApprovalType.REAR) {//，且本次流程不为育成人审批，则本流程重复签字
                        putFlowData(dataMap, flow, index);
                        index++;
                    }
                }
            }
            boolean isSkip = false;
            if (i == 1){//获取第二个流程时
                if (flowType != QuitApplyFlowType.SECTIONMANAGER) {//如果审批流程类别为组经理或非主管
                    QuitApplyFlow flow1 = approvaledList.get(i - 1);
                    if (flow1.getApprovalType() == QuitApplyApprovalType.REAR
                            || flow1.getApprovalType() == QuitApplyApprovalType.RECOMMEND) {//如果是推荐人或育成人审批，则跳过本次流程
                        isSkip = true;
                    }
                }
            }
            if (!isSkip) {
                putFlowData(dataMap, flow, index);
                index++;
            }
        }
        String templatePath = apply.getFlowType() == QuitApplyFlowType.SECTIONMANAGER ? "template/quit/sectionQuitApplyTemplate.pdf" : "template/quit/quitApplyTemplate.pdf";
        String newPdfPath = System.getProperty("user.dir") + File.separator + "tmp";
        String str = null;
        try {
            File file = PdfUtils.fillPdf(templatePath, dataMap, newPdfPath, apply.getId() + ".pdf");
            str = FileToBase64Str(file);
        } catch (IOException e) {
            logger.error("", e);
            return response.setError(ErrorType.CONVERT);
        }
        CommonTemplateResponse templateResponse = new CommonTemplateResponse();
        templateResponse.setTemplate(str);
        response.setData(templateResponse);
        return response.toJson();
    }

    private void putFlowData(Map<String, String> dataMap, QuitApplyFlow flow, int index){
        String remark = hasText(flow.getRemark()) ? flow.getRemark() : flow.getStatus().getValue();
        dataMap.put("remark" + index, remark);
        dataMap.put("sign" + index, new String(flow.getSignImg()));
        dataMap.put("date" + index, MyTimeTools.timeToStr(flow.getEndTime(), "yyy-MM-dd"));
    }
}
