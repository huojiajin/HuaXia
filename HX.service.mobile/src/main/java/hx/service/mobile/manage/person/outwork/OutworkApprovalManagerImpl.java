package hx.service.mobile.manage.person.outwork;

import hx.base.core.dao.dict.acl.ErrorType;
import hx.base.core.dao.dict.quit.QuitApplyStatus;
import hx.base.core.dao.entity.quit.QuitApply;
import hx.base.core.dao.entity.quit.QuitApplyFlow;
import hx.base.core.dao.repo.jpa.quit.QuitApplyFlowRepo;
import hx.base.core.dao.repo.jpa.quit.QuitApplyRepo;
import hx.base.core.dao.repo.request.common.Pagination;
import hx.base.core.dao.repo.request.quit.QuitApplyFlowPageRequest;
import hx.base.core.manage.model.CommonResponse;
import hx.base.core.manage.tools.MyTimeTools;
import hx.service.mobile.manage.common.AbstractMobileManager;
import hx.service.mobile.model.common.MobileCommonRequest;
import hx.service.mobile.model.login.MobileUserModel;
import hx.service.mobile.model.person.outwork.approval.OutworkApprovalApprovalRequest;
import hx.service.mobile.model.person.outwork.approval.OutworkApprovalDetailRequest;
import hx.service.mobile.model.person.outwork.approval.OutworkApprovalDetailResponse;
import hx.service.mobile.model.person.outwork.approval.OutworkApprovalQueryResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

/**
 * @ClassName: OutworkApprovalManagerImpl
 * @Description: 离职审批相关ManagerImp
 * @Author HuoJiaJin
 * @Date 2021/3/14 15:29
 * @Version 1.0
 **/
@Service
public class OutworkApprovalManagerImpl extends AbstractMobileManager implements OutworkApprovalManager {

    @Autowired
    private QuitApplyRepo applyRepo;
    @Autowired
    private QuitApplyFlowRepo flowRepo;

    @Override
    public String query(MobileCommonRequest request){

        CommonResponse response = new CommonResponse();
        MobileUserModel user = getUser(request.getToken());
        if (!user.getEmployee_type().equals("0")){
            return response.setError(ErrorType.VALID, "该用户无审批权限");
        }
        QuitApplyFlowPageRequest pageRequest = new QuitApplyFlowPageRequest();
        pageRequest.setApprovalName(user.getName());
        pageRequest.setStatus(QuitApplyStatus.APPROVALING);
        Pagination page = flowRepo.page(pageRequest);
        page.convertResult(this::convert);
        response.setData(page);
        return response.toJson();
    }

    private OutworkApprovalQueryResponse convert(QuitApplyFlow entity){
        OutworkApprovalQueryResponse model = new OutworkApprovalQueryResponse();
        Optional<QuitApply> op = applyRepo.findById(entity.getApplyId());
        if (op.isPresent()) {
            QuitApply apply = op.get();
            model.setId(entity.getId());
            model.setName(entity.getApprovalName());
            model.setSectionName(apply.getSectionName());
            model.setGroupName(apply.getGroupName());
            model.setApplyTime(MyTimeTools.timeToStr(apply.getApplyTime()));
        }
        return model;
    }

    @Override
    public String detail(OutworkApprovalDetailRequest request){
        CommonResponse response = new CommonResponse();
        Optional<QuitApply> op = applyRepo.findById(request.getApplyId());
        if (!op.isPresent()){
            return response.setError(ErrorType.VALID, "未找到对应申请");
        }
        QuitApply apply = op.get();
        OutworkApprovalDetailResponse data = new OutworkApprovalDetailResponse();
        BeanUtils.copyProperties(apply, data);
        data.setApplyTime(MyTimeTools.timeToStr(apply.getApplyTime(), "yyyy-MM-dd"));
        data.setEntryTime(apply.getEntryTime().toString());
        response.setData(data);
        return response.toJson();
    }

    @Override
    @Transactional
    public String approval(OutworkApprovalApprovalRequest request){
        CommonResponse response = new CommonResponse();
        MobileUserModel user = getUser(request.getToken());
        LocalDateTime now = LocalDateTime.now();
        QuitApplyFlow flow = flowRepo.findApproval(request.getApplyId(), user.getName());
        if (flow.getStatus() != QuitApplyStatus.APPROVALING){
            return  response.setError(ErrorType.VALID, "未到该环节或该环节已经审批结束");
        }
        Optional<QuitApply> op = applyRepo.findById(request.getApplyId());
        if (!op.isPresent()){
            return  response.setError(ErrorType.VALID, "未找到对应申请");
        }
        QuitApply apply = op.get();
        if (request.isSpecial()){//特批修改
            flow.setStatus(QuitApplyStatus.SPECIAL);
            flow.setUpdateTime(now);
            apply.setStatus(QuitApplyStatus.SPECIAL);
            apply.setUpdateTime(now);
        }else {//正常审批
            flow.setRemark(request.getRemark());
            flow.setStatus(QuitApplyStatus.PASS);
            flow.setEndTime(now);
            flow.setUpdateTime(now);
            flow.setSignImg(request.getSignImg().getBytes());
            if (request.isAdopt()){//审批通过
                flow.setStatus(QuitApplyStatus.PASS);
                QuitApplyFlow nextFlow = flowRepo.findByType(request.getApplyId(), flow.getNextApprovalType());
                nextFlow.setStatus(QuitApplyStatus.APPROVALING);
                nextFlow.setStartTime(now);
                nextFlow.setUpdateTime(now);
                flowRepo.save(nextFlow);
            }else{//审批不通过
                flow.setStatus(QuitApplyStatus.NOPASS);
                apply.setStatus(QuitApplyStatus.NOPASS);
            }
        }
        applyRepo.save(apply);
        flowRepo.save(flow);
        return response.toJson();
    }
}
