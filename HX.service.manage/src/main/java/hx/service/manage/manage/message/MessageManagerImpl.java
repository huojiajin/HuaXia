package hx.service.manage.manage.message;

import com.google.common.collect.Lists;
import hx.base.core.dao.dict.message.ContentType;
import hx.base.core.dao.entity.message.MessageCustom;
import hx.base.core.dao.entity.message.MessageSendFail;
import hx.base.core.dao.repo.jpa.message.MessageSendFailRepo;
import hx.base.core.manage.model.HXCommonResponse;
import hx.base.core.manage.tools.JsonTools;
import hx.service.manage.manage.common.AbstractManager;
import hx.service.manage.model.message.huaxia.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
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
    @Value("${picUrl}")
    private String picUrl;
    @Value("${agentid}")
    private String agentid;
    @Value("${myPicUrl}")
    private String myPicUrl;
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
        BigDecimal random = new BigDecimal(Math.random() * 1000000 - 1).setScale(0, BigDecimal.ROUND_HALF_UP);
        //获取时间戳
        Date date = new Date();
        Long timestamp = Long.valueOf(String.valueOf(date.getTime()));
        request.setMsg_id(serialNo + timestamp + String.format("%06d", random.intValue()));
        if (messageCustom.getContentType() == ContentType.TEXT) {
            return sendText(messageCustom, agentCode, request);
        }else {
            return sendPic(messageCustom, agentCode, request);
        }
    }

    /**
     * @Name sendText
     * @Author HuoJiaJin
     * @Description 发送文字消息
     * @Date 2021/3/20 20:18
     * @Param [messageCustom, agentCode, request]
     * @Return boolean
     **/
    private boolean sendText(MessageCustom messageCustom, String agentCode, MessageSendRequest request) {
        MessageSendTextModel sendModel = new MessageSendTextModel();
        sendModel.setTouser(agentCode);
        sendModel.setAgentid(agentid);
        MessageTextModel contentModel = new MessageTextModel();
        contentModel.setContent(messageCustom.getContent());
        sendModel.setText(contentModel);
        request.setBusi_data(sendModel);
        HXCommonResponse response = null;
        try {
            logger.info("======请求信息为：" + request.toJson());
            String responseStr = hxPost(url + textUrl, request.toJson());
            response = JsonTools.json2Object(responseStr, HXCommonResponse.class);
            logger.info("======返回信息为：" + response.toJson());
        } catch (IOException e) {
            failSave(messageCustom, agentCode, e.getMessage());
            return false;
        }
        if (!response.getCode().equals("0")){
            failSave(messageCustom, agentCode, response.getMessage());
        }else{
            return true;
        }
        return false;
    }

    /**
     * @Name sendText
     * @Author HuoJiaJin
     * @Description 发送图片消息
     * @Date 2021/3/20 20:18
     * @Param [messageCustom, agentCode, request]
     * @Return boolean
     **/
    private boolean sendPic(MessageCustom messageCustom, String agentCode, MessageSendRequest request) {
        MessageSendPicModel sendModel = new MessageSendPicModel();
        MessagePicModel newsModel = new MessagePicModel();
        MessagePicContentModel contentModel = new MessagePicContentModel();
        contentModel.setTitle(messageCustom.getTitle());
        contentModel.setDescription(messageCustom.getDescription());
        contentModel.setPicurl(myPicUrl + messageCustom.getId());
        List<MessagePicContentModel> articles = Lists.newArrayList(contentModel);
        newsModel.setArticles(articles);
        sendModel.setNews(newsModel);
        request.setBusi_data(sendModel);
        HXCommonResponse response = null;
        try {
            logger.info("======请求信息为：" + request.toJson());
            String responseStr = hxPost(url + picUrl, request.toJson());
            response = JsonTools.json2Object(responseStr, HXCommonResponse.class);
            logger.info("======返回信息为：" + response.toJson());
        } catch (IOException e) {
            failSave(messageCustom, agentCode, e.getMessage());
            return false;
        }
        if (!response.getCode().equals("0")){
            failSave(messageCustom, agentCode, response.getMessage());
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
    private void failSave(MessageCustom messageCustom, String agentCode, String message) {
        logger.error("======发送信息至{}时报错，报错信息为：{}，发送消息为：{}", agentCode, message, messageCustom.getId());
        MessageSendFail fail = sendFailRepo.findByCustomIdAndAgentCode(messageCustom.getId(), agentCode);
        if (fail == null) {
            fail = new MessageSendFail();
            fail.setAgentCode(agentCode);
            fail.setCustomId(messageCustom.getId());
        }
        fail.setMessage(message);
        sendFailRepo.save(fail);
    }
}
