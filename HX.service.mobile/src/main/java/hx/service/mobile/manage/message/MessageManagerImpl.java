package hx.service.mobile.manage.message;

import hx.base.core.dao.dict.acl.ErrorType;
import hx.base.core.dao.entity.message.MobileMessage;
import hx.base.core.dao.repo.jpa.message.MobileMessageRepo;
import hx.base.core.dao.repo.request.common.Pagination;
import hx.base.core.dao.repo.request.message.MobileMessagePageRequest;
import hx.base.core.manage.model.CommonResponse;
import hx.base.core.manage.tools.MyTimeTools;
import hx.service.mobile.manage.common.AbstractMobileManager;
import hx.service.mobile.model.common.MobileCommonPageRequest;
import hx.service.mobile.model.common.MobileCommonRequest;
import hx.service.mobile.model.login.MobileUserModel;
import hx.service.mobile.model.message.MessageQueryResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * @ClassName: MessageManagerImpl
 * @Description: 消息通知Manager
 * @Author HuoJiaJin
 * @Date 2021/2/6 1:08
 * @Version 1.0
 **/
@Service
public class MessageManagerImpl extends AbstractMobileManager implements MessageManager {

    @Autowired
    private MobileMessageRepo messageRepo;

    @Override
    public String query(MobileCommonPageRequest request){

        CommonResponse response = new CommonResponse();
        MobileMessagePageRequest pageRequest = new MobileMessagePageRequest();
        BeanUtils.copyProperties(request, pageRequest);
        Pagination page = messageRepo.page(pageRequest);
        page.convertResult(this::convert);
        response.setData(page);
        return response.toJson();
    }

    private MessageQueryResponse convert(MobileMessage entity){
        MessageQueryResponse model = new MessageQueryResponse();
        model.setContent(entity.getContent());
        model.setSendTime(MyTimeTools.timeToStr(entity.getReadTime(), "yyyy.MM.dd"));
        model.setUnread(!entity.isHasRead());
        return model;
    }

    @Override
    public String read(MobileCommonRequest request){
        CommonResponse response = new CommonResponse();
        MobileUserModel user = getUser(request.getToken());
        if (user == null) {
            return response.setError(ErrorType.NOLOGIN);
        }
        messageRepo.updateRead(user.getEmployee_code(), LocalDateTime.now());
        response.setMessage("已清除未读消息");
        return response.toJson();
    }
}
