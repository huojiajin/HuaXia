package hx.service.manage.manage.message;

import com.google.common.collect.Lists;
import hx.base.core.dao.dict.acl.ErrorType;
import hx.base.core.dao.dict.acl.PositionsClass;
import hx.base.core.dao.dict.message.ContentType;
import hx.base.core.dao.dict.message.MessageType;
import hx.base.core.dao.dict.test.PushType;
import hx.base.core.dao.entity.hualife.MarketingManpower;
import hx.base.core.dao.entity.message.MessageCustom;
import hx.base.core.dao.entity.message.MessageCustomLog;
import hx.base.core.dao.repo.jpa.hualife.MarketingManpowerRepo;
import hx.base.core.dao.repo.jpa.message.MessageCustomLogRepo;
import hx.base.core.dao.repo.jpa.message.MessageCustomRepo;
import hx.base.core.dao.repo.request.common.Pagination;
import hx.base.core.dao.repo.request.message.MessageCustomLogPageRequest;
import hx.base.core.dao.repo.request.message.MessageCustomPageRequest;
import hx.base.core.manage.model.CommonResponse;
import hx.base.core.manage.tools.JsonTools;
import hx.base.core.manage.tools.MyTimeTools;
import hx.service.manage.manage.common.AbstractManager;
import hx.service.manage.model.message.*;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @ClassName: MessageManagerImpl
 * @Description: 自定义消息ManagerImpl
 * @Author HuoJiaJin
 * @Date 2021/1/31 20:46
 * @Version 1.0
 **/
@Service
public class MessageCustomManagerImpl extends AbstractManager implements MessageCustomManager {

    @Autowired
    private MessageCustomRepo customRepo;
    @Autowired
    private MessageCustomLogRepo customLogRepo;
    @Autowired
    private MarketingManpowerRepo manpowerRepo;
    @Autowired
    private MessageManager messageManager;

    @Override
    public String query(MessageQueryRequest request){
        CommonResponse response = new CommonResponse();
        MessageCustomPageRequest pageRequest = new MessageCustomPageRequest();
        BeanUtils.copyProperties(request, pageRequest);
        Pagination page = customRepo.page(pageRequest);
        page.convertResult(this::convertModel);
        response.setData(page);
        return response.toJson();
    }

    private MessageQueryResponse convertModel(MessageCustom entity){
        MessageQueryResponse model = new MessageQueryResponse();
        BeanUtils.copyProperties(entity, model);
        model.setMessageType(entity.getMessageType().getCode());
        model.setContentType(entity.getContentType().getCode());
        if (entity.getContentType() == ContentType.TEXT){
            model.setContent(entity.getContent());
        }else if (entity.getContentType() == ContentType.IMAGE){
            byte[] imageBytes = entity.getImage();
            String base64 = new String(Base64.encodeBase64(imageBytes));
            model.setContent(base64);
        }
        model.setDeadline(entity.getDeadline().toString());
        return model;
    }

    @Override
    public String add(MessageAddRequest request){
        CommonResponse response = new CommonResponse();
        MessageType messageType;
        ContentType contentType;
        try {
            messageType = MessageType.fromCode(request.getMessageType());
            contentType = ContentType.fromCode(request.getContentType());
        } catch (InterruptedException e) {
            return response.setError(ErrorType.CONVERT, e.getMessage());
        }
        if (MessageType.isDaily(messageType)){
            List<MessageCustom> messageCustoms = customRepo.listByMessageType(messageType);
            if (!isEmpty(messageCustoms)){
                return response.setError(ErrorType.VALID, "日常消息类型只可存在一条");
            }
        }else{
            if (!hasText(request.getContent())){
                return response.setError(ErrorType.CONVERT, "消息内容不能为空");
            }
        }
        //拼装并保存实体
        MessageCustom custom = new MessageCustom();
        custom.setName(request.getName());
        custom.setContentType(contentType);
        if (contentType == ContentType.TEXT){
            custom.setContent(request.getContent());
        }else if (contentType == ContentType.IMAGE){
            custom.setImage(Base64.decodeBase64(request.getContent()));
        }
        custom.setMessageType(messageType);
        custom.setDeadline(LocalDate.parse(request.getDeadline()));
        custom.setInsertTime(LocalDateTime.now());
        customRepo.persist(custom);
//        if (contentType == ContentType.IMAGE){
//            customRepo.blobSave(custom, "image", new ByteArrayInputStream(Base64.decodeBase64(request.getContent())));
//        }
        addSysLog("添加自定义消息", request.getToken(), custom.getId());
        response.setMessage("添加自定义消息成功！");
        return response.toJson();
    }

    @Override
    public String delete(MessageIdRequest request){
        CommonResponse response = new CommonResponse();
        customRepo.updateDelete(request.getMessageId(), LocalDateTime.now());
        addSysLog("删除自定义消息", request.getToken(), request.getMessageId());
        response.setMessage("删除自定义消息成功！");
        return response.toJson();
    }

    @Override
    public String queryLog(MessageLogQueryRequest request){
        CommonResponse response = new CommonResponse();
        MessageCustomLogPageRequest pageRequest = new MessageCustomLogPageRequest();
        BeanUtils.copyProperties(request, pageRequest);
        Pagination page = customLogRepo.page(pageRequest);
        page.convertResult(this::convertLogModel);
        response.setData(page);
        return response.toJson();
    }

    private MessageLogQueryResponse convertLogModel(MessageCustomLog entity){
        MessageLogQueryResponse model = new MessageLogQueryResponse();
        model.setPushType(entity.getPushType().getCode());
        model.setPushName(entity.getPushName());
        model.setPushTime(MyTimeTools.timeToStr(entity.getPushTime()));
        return model;
    }

    @Override
    @Transactional
    public String push(MessagePushRequest request){
        CommonResponse response = new CommonResponse();
        //查询自定义消息
        Optional<MessageCustom> op = customRepo.findById(request.getMessageId());
        if (!op.isPresent()){
            return response.setError(ErrorType.NOCUSTOM);
        }
        MessageCustom custom = op.get();
        //转换推送类别
        PushType pushType = null;
        try {
            pushType = PushType.fromCode(request.getPushType());
        } catch (InterruptedException e) {
            return response.setError(ErrorType.CONVERT, e.getMessage());
        }
        for (String code : request.getCodeList()) {
            List<MarketingManpower> manpowerList = Lists.newArrayList();
            String pushName = null;
            if (pushType == PushType.RANK) {//按职级推送
                PositionsClass positionsClass;
                try {
                    positionsClass = PositionsClass.valueOf(code);
                } catch (IllegalArgumentException e) {
                    logger.error("无此职级{}", code);
                    return response.setError(ErrorType.CONVERT, "无此职级" + code);
                }
                manpowerList = manpowerRepo.listByAgentGrade(positionsClass.name());
                if (isEmpty(manpowerList)){
                    return response.setError(ErrorType.CONVERT, "该职级无人员：" + code);
                }
                pushName = positionsClass.getValue();
            }else if (pushType == PushType.CAMP) {//按营服推送
                manpowerList = manpowerRepo.listByDeptCode1(code);
                if (isEmpty(manpowerList)){
                    return response.setError(ErrorType.CONVERT, "该营服无人员：" + code);
                }
                pushName = manpowerList.get(0).getDeptName1();
            }
            //获取工号集合
            List<String> agentCodes = manpowerList.stream().map(MarketingManpower::getAgentCode).collect(Collectors.toList());
            //推送消息
            messageManager.sendText(custom, agentCodes);
            //保存推送记录
            saveSendLog(custom, pushType, code, pushName);
        }
        try {
            addSysLog("推送试卷" + request.getMessageId() + "至" + PushType.fromCode(request.getPushType()).getValue() + JsonTools.toJsonStr(request.getCodeList()),
                    request.getToken(), request.getMessageId());
        } catch (Exception e) {
            logger.error("", e);
        }
        response.setMessage("推送成功！");
        return response.toJson();
    }

    /**
     * @Name saveSendLog
     * @Author HuoJiaJin
     * @Description 保存推送记录
     * @Date 2021/3/11 18:29
     * @Param [custom, pushType, code, pushName]
     * @Return void
     **/
    private void saveSendLog(MessageCustom custom, PushType pushType, String code, String pushName) {
        MessageCustomLog log = new MessageCustomLog();
        log.setMessageId(custom.getId());
        log.setPushType(pushType);
        log.setPushCode(code);
        log.setPushName(pushName);
        log.setPushTime(LocalDateTime.now());
        customLogRepo.save(log);
    }


}
