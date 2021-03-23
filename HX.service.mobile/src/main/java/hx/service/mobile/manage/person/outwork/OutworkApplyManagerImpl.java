package hx.service.mobile.manage.person.outwork;

import hx.base.core.dao.dict.acl.ErrorType;
import hx.base.core.dao.dict.acl.PositionsClass;
import hx.base.core.dao.dict.acl.PositionsType;
import hx.base.core.dao.dict.quit.QuitApplyApprovalType;
import hx.base.core.dao.dict.quit.QuitApplyFlowType;
import hx.base.core.dao.dict.quit.QuitApplyStatus;
import hx.base.core.dao.dict.quit.QuitAssignType;
import hx.base.core.dao.entity.hualife.Incubation;
import hx.base.core.dao.entity.hualife.MarketingManpower;
import hx.base.core.dao.entity.quit.QuitApply;
import hx.base.core.dao.entity.quit.QuitApplyFlow;
import hx.base.core.dao.entity.quit.QuitAssign;
import hx.base.core.dao.repo.jpa.hualife.IncubationRepo;
import hx.base.core.dao.repo.jpa.hualife.MarketingManpowerRepo;
import hx.base.core.dao.repo.jpa.quit.QuitApplyFlowRepo;
import hx.base.core.dao.repo.jpa.quit.QuitApplyRepo;
import hx.base.core.dao.repo.jpa.quit.QuitAssignRepo;
import hx.base.core.dao.repo.request.common.Pagination;
import hx.base.core.dao.repo.request.quit.QuitApplyPageRequest;
import hx.base.core.manage.model.CommonResponse;
import hx.base.core.manage.tools.MyTimeTools;
import hx.service.mobile.manage.common.AbstractMobileManager;
import hx.service.mobile.model.common.MobileCommonPageRequest;
import hx.service.mobile.model.login.MobileUserModel;
import hx.service.mobile.model.person.outwork.apply.*;
import org.apache.commons.compress.utils.Lists;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @ClassName: OutworkApplyManagerImpl
 * @Description: 离职申请相关ManagerImpl
 * @Author HuoJiaJin
 * @Date 2021/3/14 1:45
 * @Version 1.0
 **/
@Service
public class OutworkApplyManagerImpl extends AbstractMobileManager implements OutworkApplyManager {

    @Autowired
    private QuitApplyRepo applyRepo;
    @Autowired
    private QuitApplyFlowRepo flowRepo;
    @Autowired
    private QuitAssignRepo assignRepo;
    @Autowired
    private MarketingManpowerRepo manpowerRepo;
    @Autowired
    private IncubationRepo incubationRepo;

    @Override
    @Transactional
    public String apply(OutworkApplyApplyRequest request){
        CommonResponse response = new CommonResponse();
        MobileUserModel user = getUser(request.getToken());
        if (request.isSpecial()){
            //查询人员当前申请
            QuitApply special = applyRepo.findBySpecial(user.getEmployee_code());
            if (special == null){
                return response.setError(ErrorType.VALID, "未找到对应离职申请！");
            }
            if (!hasText(request.getReason())){
                return response.setError(ErrorType.VALID, "请填写离职原因！");
            }
            special.setReason(request.getReason());
//            special.setIdCardFrontImg(request.getIdcardFrontImg().getBytes());
//            special.setIdCardBackImg(request.getIdcardBackImg().getBytes());
//            special.setSignImg(request.getSignImg().getBytes());
            special.setStatus(QuitApplyStatus.APPROVALING);
            QuitApplyFlow specialFlow = flowRepo.findSpecial(special.getId());
            specialFlow.setStatus(QuitApplyStatus.APPROVALING);
            applyRepo.save(special);
            flowRepo.save(specialFlow);
        }else {
            MarketingManpower manpower = manpowerRepo.findByAgentCode(user.getEmployee_code());
            QuitApply approval = applyRepo.findApproval(user.getEmployee_code());
            if (approval != null){
                return response.setError(ErrorType.VALID, "您已提交离职申请！");
            }
            if (!hasText(request.getReason())){
                return response.setError(ErrorType.VALID, "请填写离职原因！");
            }
            if (!hasText(request.getSignImg())){
                return response.setError(ErrorType.VALID, "请签字确认！");
            }
            if (!hasText(request.getIdcardFrontImg())){
                return response.setError(ErrorType.VALID, "请提交身份证正面照片！");
            }
            if (!hasText(request.getIdcardBackImg())){
                return response.setError(ErrorType.VALID, "请提交身份证背面照片！");
            }
            //拼装实体
            QuitApply apply = new QuitApply();
            apply.setName(user.getName());
            apply.setAgentCode(user.getEmployee_code());
            apply.setEntryTime(manpower.getEmployDate());
            apply.setCampName(manpower.getDeptName1());
            apply.setCampCode(manpower.getDeptCode1());
            apply.setSectionName(manpower.getDeptName3());
            apply.setSectionCode(manpower.getDeptCode3());
            apply.setGroupName(manpower.getDeptName4());
            apply.setGroupCode(manpower.getDeptCode4());
            apply.setReason(request.getReason());
            apply.setApplyTime(LocalDateTime.now());
            //保存审批流程类别
            PositionsType positionsType = null;
            try {
                PositionsClass positionsClass = PositionsClass.valueOf(manpower.getAgentGrade());
                positionsType = PositionsType.fromClass(positionsClass);
            } catch (InterruptedException e) {
                logger.error("", e);
                return response.setError(ErrorType.CONVERT);
            }
            if (positionsType == PositionsType.BM) {
                apply.setFlowType(QuitApplyFlowType.SECTIONMANAGER);
            } else if (positionsType == PositionsType.BC) {
                apply.setFlowType(QuitApplyFlowType.GROUPMANAGER);
            } else if (positionsType == PositionsType.FZG) {
                apply.setFlowType(QuitApplyFlowType.NONEXECUTIVE);
            } else {
                return response.setError(ErrorType.VALID, "暂不支持部经理以上人员申请离职");
            }

            apply.setIdCardFrontImg(request.getIdcardFrontImg().getBytes());
            apply.setIdCardBackImg(request.getIdcardBackImg().getBytes());
            apply.setSignImg(request.getSignImg().getBytes());
            //创建审批流程并获取当前审批人
            try {
                String currentExaminer = createFlow(apply, manpower);
                apply.setCurrentExaminer(currentExaminer);
            } catch (InterruptedException e) {
                logger.error("", e);
                return response.setError(ErrorType.CONVERT, e.getMessage());
            }
            applyRepo.persist(apply);
        }
        response.setMessage("离职申请提交成功！");
        return response.toJson();
    }

    /**
     * @Name createFlow
     * @Author HuoJiaJin
     * @Description 创建流程
     * @Date 2021/3/14 13:20
     * @Param [apply, manpower]
     * @Return java.lang.String
     **/
    private String createFlow(QuitApply apply, MarketingManpower manpower) throws InterruptedException {

        List<QuitApplyFlow> flowList = Lists.newArrayList();
        String currentExaminer = "";//当前环节人
        List<QuitApplyApprovalType> approvalTypeList = apply.getFlowType().getApprovalFlow();//所有环节
        for (int i = 0; i < approvalTypeList.size(); i++) {
            QuitApplyApprovalType approvalType = approvalTypeList.get(i);
            QuitApplyFlow applyFlow = new QuitApplyFlow();
            applyFlow.setApplyId(apply.getId());
            if (i == 0){//如果是首个流程，直接进入审批环节
                applyFlow.setStatus(QuitApplyStatus.APPROVALING);
                applyFlow.setStartTime(LocalDateTime.now());
                apply.setApprovalType(approvalType);
            }else{
                applyFlow.setStatus(QuitApplyStatus.NOTAPPROVAL);
            }
            applyFlow.setApprovalType(approvalType);
            //获取下一审批阶段
            if (i + 1 < approvalTypeList.size()) {
                applyFlow.setNextApprovalType(approvalTypeList.get(i + 1));
            }else{
                applyFlow.setNextApprovalType(null);
            }
            applyFlow.setList(i + 1);

            switch (approvalType) {//获取审批人员相关信息
                case RECOMMEND:
                    applyFlow.setApprovalName(manpower.getRecommAgentName());
                    applyFlow.setApprovalCode(manpower.getRecommAgentCode());
                    if (i == 0) {
                        currentExaminer = manpower.getRecommAgentName();
                    }
                    break;
                case GROUP:
                    applyFlow.setApprovalName(manpower.getName1());
                    applyFlow.setApprovalCode(manpower.getAgentCode1());
                    if (i == 0) {
                        currentExaminer = manpower.getName1();
                    }
                    break;
                case REAR:
                    Incubation incubation;
                    List<Incubation> incubations = incubationRepo.listByAgentCode(manpower.getAgentCode(), 1l);
                    if (isEmpty(incubations)){
                        List<Incubation> secondIncubations = incubationRepo.listByAgentCode(manpower.getAgentCode(), 2l);
                        if (isEmpty(secondIncubations)){
                            continue;
                        }
                        incubation = secondIncubations.get(0);
                    }else {
                        incubation = incubations.get(0);
                    }
                    MarketingManpower rear = manpowerRepo.findByAgentCode(incubation.getRearAgentCode());
                    applyFlow.setApprovalName(rear.getName());
                    applyFlow.setApprovalCode(rear.getAgentCode());
                    if (i == 0) {
                        currentExaminer = rear.getName();
                    }
                    break;
                case SECTION:
                    MarketingManpower directorSection = getDirector(manpower, PositionsType.BM);
                    applyFlow.setApprovalName(directorSection.getName());
                    applyFlow.setApprovalCode(directorSection.getAgentCode());
                    if (i == 0) {
                        currentExaminer = directorSection.getName();
                    }
                    break;
                case CHIEF:
                    MarketingManpower directorChief = getDirector(manpower, PositionsType.AS);
                    applyFlow.setApprovalName(directorChief.getName());
                    applyFlow.setApprovalCode(directorChief.getAgentCode());
                    if (i == 0) {
                        currentExaminer = directorChief.getName();
                    }
                    break;
                case ZX:
                    QuitAssign assignZX = assignRepo.findByCampCodeAndType(manpower.getDeptCode1(), QuitAssignType.ZX);
                    if (assignZX == null){
                        throw new InterruptedException("营服" + manpower.getDeptName1() + "无对应组训经理");
                    }
                    applyFlow.setApprovalName(assignZX.getName());
                    applyFlow.setApprovalCode(assignZX.getAgentCode());
                    if (i == 0) {
                        currentExaminer = assignZX.getName();
                    }
                    break;
                case YFJL:
                    QuitAssign assignYFJL = assignRepo.findByCampCodeAndType(manpower.getDeptCode1(), QuitAssignType.YFJL);
                    applyFlow.setApprovalName(assignYFJL.getName());
                    applyFlow.setApprovalCode(assignYFJL.getAgentCode());
                    if (i == 0) {
                        currentExaminer = assignYFJL.getName();
                    }
                    break;
                case FGSRG:
                    QuitAssign rg = assignRepo.findRG();
                    applyFlow.setApprovalName(rg.getName());
                    applyFlow.setApprovalCode(rg.getAgentCode());
                    if (i == 0) {
                        currentExaminer = rg.getName();
                    }
                    break;
            }

            flowList.add(applyFlow);
        }
        flowRepo.persistAll(flowList);
        return currentExaminer;
    }

    /**
     * @Name getRear
     * @Author HuoJiaJin
     * @Description 获取部门领导
     * @Date 2021/3/14 12:58
     * @Param [manpower]
     * @Return hx.base.core.dao.entity.hualife.MarketingManpower
     **/
    private MarketingManpower getDirector(MarketingManpower manpower, PositionsType directorType) throws InterruptedException {
        List<MarketingManpower> manpowerList = Lists.newArrayList();
        if (directorType == PositionsType.AS) {
            manpowerList = manpowerRepo.listByDeptCode2(manpower.getDeptCode2());
        }else if (directorType == PositionsType.BM){
            manpowerList = manpowerRepo.listByDeptCode3(manpower.getDeptCode3());
        }else {
            manpowerList = manpowerRepo.listByDeptCode4(manpower.getDeptCode4());
        }
        MarketingManpower director = null;
        for (MarketingManpower staff : manpowerList) {
            PositionsType positionsType = null;
            try {
                PositionsClass positionsClass = PositionsClass.valueOf(staff.getAgentGrade());
                positionsType = PositionsType.fromClass(positionsClass);
            } catch (InterruptedException e) {
                logger.error("", e);
                throw new InterruptedException("无此职级" + staff.getAgentGradeName());
            }
            if (positionsType == directorType){
                director = staff;
            }
        }
        if (director == null){
            throw new InterruptedException("该人员无" + manpower.getAgentGradeName());
        }
        return director;
    }

    @Override
    public String query(MobileCommonPageRequest request){
        CommonResponse response = new CommonResponse();
        MobileUserModel user = getUser(request.getToken());
        QuitApplyPageRequest pageRequest = new QuitApplyPageRequest();
        BeanUtils.copyProperties(request, pageRequest);
        pageRequest.setAgentCode(user.getEmployee_code());
        Pagination page = applyRepo.page(pageRequest);
        page.convertResult(this::convert);
        response.setData(page);
        return response.toJson();
    }

    private OutworkApplyQueryResponse convert(QuitApply entity){
        OutworkApplyQueryResponse model = new OutworkApplyQueryResponse();
        BeanUtils.copyProperties(entity, model);
        model.setApplyTime(MyTimeTools.timeToStr(entity.getApplyTime()));
        if (entity.getStatus() == QuitApplyStatus.PASS){
            model.setStage("审批通过");
        }else if (entity.getStatus() == QuitApplyStatus.NOPASS) {
            model.setStage("审批未通过");
        }else {
            model.setStage(entity.getApprovalType().getValue());
        }
        model.setStatus(entity.getStatus().getCode());
        if (entity.getStatus() == QuitApplyStatus.SPECIAL) {
            model.setSpecial(true);
        }
        return model;
    }

    @Override
    public String detail(OutworkApplyDetailRequest request){
        CommonResponse response = new CommonResponse();
        Optional<QuitApply> op = applyRepo.findById(request.getApplyId());
        if (!op.isPresent()){
            response.setError(ErrorType.VALID, "无此申请");
        }
        QuitApply apply = op.get();
        OutworkApplyDetailResponse data = new OutworkApplyDetailResponse();
        BeanUtils.copyProperties(apply, data);
        data.setApplyTime(MyTimeTools.timeToStr(apply.getApplyTime(), "yyyy-MM-dd"));
        data.setEntryTime(apply.getEntryTime().toString());
        data.setStatus(apply.getStatus().getCode());
        //拼装审批流程
        List<QuitApplyFlow> flowList = flowRepo.listByApplyId(request.getApplyId());
        List<OutworkApplyDetailApprovalModel> approvalList =flowList.stream().map(f -> {
            OutworkApplyDetailApprovalModel model = new OutworkApplyDetailApprovalModel();
            model.setApprovalName(f.getApprovalName());
            model.setApprovalStage(f.getApprovalType().getValue());
            model.setApprovalStatus(f.getStatus().getCode());
            model.setApprovalRemark(f.getRemark());
            if (f.getEndTime() != null) {
                model.setApprovalTime(MyTimeTools.timeToStr(f.getEndTime()));
            }
            return model;
        }).collect(Collectors.toList());
        data.setApprovalList(approvalList);
        response.setData(data);
        return response.toJson();
    }
}
