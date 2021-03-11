package hx.service.manage.manage.message;

import hx.base.core.dao.entity.message.MessageCustom;
import hx.base.core.dao.entity.message.MessageSendFail;
import hx.base.core.dao.repo.jpa.message.MessageSendFailRepo;
import hx.base.core.manage.model.HXCommonResponse;
import hx.base.core.manage.tools.JsonTools;
import hx.base.core.manage.tools.httpclient.HttpClientHelper;
import hx.service.manage.manage.common.AbstractManager;
import hx.service.manage.model.message.huaxia.MessageTextModel;
import hx.service.manage.model.message.huaxia.MessageSendModel;
import hx.service.manage.model.message.huaxia.MessageSendRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

/**
 * @ClassName: MessageManagerImpl
 * @Description: 总部消息推送相关接口
 * @Author HuoJiaJin
 * @Date 2021/2/1 0:51
 * @Version 1.0
 **/
@Service
public class MessageManagerImpl extends AbstractManager implements MessageManager {

    @Value("${textUrl}")
    private String textUrl;
    @Autowired
    private MessageSendFailRepo sendFailRepo;

    @Override
    public void sendText(MessageCustom messageCustom, List<String> agentCodes){
        for (String agentCode : agentCodes) {
            sendTextOne(messageCustom, agentCode);
        }
    }

    @Override
    public boolean sendTextOne(MessageCustom messageCustom, String agentCode) {
        MessageSendRequest request = new MessageSendRequest();
        request.setMsg_id(serialNo + System.currentTimeMillis() + String.format("%06d", Math.random() * 1000000 - 1));
        MessageSendModel<MessageTextModel> sendModel = new MessageSendModel();
        sendModel.setTouser(agentCode);
        MessageTextModel contentModel = new MessageTextModel();
        contentModel.setContent(messageCustom.getContent());
        sendModel.setText(contentModel);
        request.setBusi_data(sendModel);
        HXCommonResponse response = null;
        try {
            String responseStr = HttpClientHelper.jsonPost(url + textUrl, request.toJson());
            response = JsonTools.json2Object(responseStr, HXCommonResponse.class);
        } catch (IOException e) {
            failSave(messageCustom, agentCode, response, e.getMessage());
        }
        if (!response.getCode().equals("0")){
            failSave(messageCustom, agentCode, response, response.getMessage());
        }else{
            return true;
        }
        return false;
    }

    /**
     * @Name failSave
     * @Author HuoJiaJin
     * @Description 保存错误信息
     * @Date 2021/3/11 16:46
     * @Param [messageCustom, agentCode, response, message]
     * @Return void
     **/
    private void failSave(MessageCustom messageCustom, String agentCode, HXCommonResponse response, String message) {
        logger.error("======发送信息至{}时报错，报错信息为：{}，发送消息为：{}", agentCode, message, messageCustom.getId());
        MessageSendFail fail = sendFailRepo.findByCustomIdAndAgentCode(messageCustom.getId(), agentCode);
        if (fail == null) {
            fail = new MessageSendFail();
            fail.setAgentCode(agentCode);
            fail.setCustomId(messageCustom.getId());
        }
        fail.setMessage(response.getMessage());
        sendFailRepo.save(fail);
    }
}
