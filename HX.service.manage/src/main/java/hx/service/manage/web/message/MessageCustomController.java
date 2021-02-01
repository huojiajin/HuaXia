package hx.service.manage.web.message;

import hx.service.manage.manage.message.MessageCustomManager;
import hx.service.manage.model.message.*;
import hx.service.manage.web.common.MyBaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: MessageController
 * @Description: 自定义消息Controller
 * @Author HuoJiaJin
 * @Date 2021/1/31 20:43
 * @Version 1.0
 **/
@RestController
@RequestMapping("/message")
public class MessageCustomController extends MyBaseController {

    @Autowired
    private MessageCustomManager manager;

    @PostMapping("/query")
    public String query(@RequestBody MessageQueryRequest request){
        return manager.query(request);
    }

    @PostMapping("/add")
    public String add(@RequestBody MessageAddRequest request){
        return manager.add(request);
    }

    @PostMapping("/delete")
    public String delete(@RequestBody MessageIdRequest request){
        return manager.delete(request);
    }

    @PostMapping("/push")
    public String push(@RequestBody MessagePushRequest request){
        return manager.push(request);
    }

    @PostMapping("/querylog")
    public String queryLog(@RequestBody MessageLogQueryRequest request){
        return manager.queryLog(request);
    }
}
