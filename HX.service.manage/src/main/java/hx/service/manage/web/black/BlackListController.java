package hx.service.manage.web.black;

import hx.service.manage.manage.black.BlackListManager;
import hx.service.manage.model.black.BlackListQueryRequest;
import hx.service.manage.web.common.MyBaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: BlackListController
 * @Description: 黑名单Controller
 * @Author HuoJiaJin
 * @Date 2021/2/3 1:04
 * @Version 1.0
 **/
@RestController
@RequestMapping("/blacklist")
public class BlackListController extends MyBaseController {

    @Autowired
    private BlackListManager manager;

    @PostMapping("/query")
    public String query(@RequestBody BlackListQueryRequest request){
        return manager.query(request);
    }

    @PostMapping("/export")
    public String export(@RequestBody BlackListQueryRequest request){
        return manager.export(request);
    }
}
