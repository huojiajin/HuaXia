package hx.service.manage.web.acl;

import hx.service.manage.manage.acl.MobileResourceManager;
import hx.service.manage.manage.model.CommonRequest;
import hx.service.manage.manage.model.acl.mobile.MobileResourceConfigRequest;
import hx.service.manage.manage.model.acl.mobile.MobileResourceQueryRequest;
import hx.service.manage.web.MyBaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @name: MobileResourceController
 * @description: 移动端资源配置Controller
 * @author: huojiajin
 * @time: 2020/6/18 16:14
 */
@RestController
@RequestMapping("/manage/mobile/resource")
public class MobileResourceController extends MyBaseController {

    @Autowired
    private MobileResourceManager manager;

    @PostMapping("/class/query")
    public String classQuery(@RequestBody CommonRequest request){
        return manager.listType(request);
    }

    @PostMapping("/query")
    public String query(@RequestBody MobileResourceQueryRequest request){
        return manager.resourceQuery(request);
    }

    @PostMapping("/config")
    public String config(@RequestBody MobileResourceConfigRequest request){
        return manager.resourceConfig(request);
    }
}
