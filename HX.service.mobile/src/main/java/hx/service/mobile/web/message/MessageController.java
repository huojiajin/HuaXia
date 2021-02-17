package hx.service.mobile.web.message;

import hx.base.core.manage.common.CommonAbstract;
import hx.service.mobile.manage.message.MessageManager;
import hx.service.mobile.model.common.MobileCommonPageRequest;
import hx.service.mobile.model.common.MobileCommonRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: MessageController
 * @Description: 消息通知Controller
 * @Author HuoJiaJin
 * @Date 2021/2/6 3:32
 * @Version 1.0
 **/
@RestController
@RequestMapping("/message")
public class MessageController extends CommonAbstract {

    @Autowired
    private MessageManager manager;

    @PostMapping("/query")
    public String query(@RequestBody MobileCommonPageRequest request){
        return manager.query(request);
    }

    @PostMapping("/read")
    public String read(@RequestBody MobileCommonRequest request){
        return manager.read(request);
    }
}
