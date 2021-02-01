package hx.service.mobile.web.test;

import hx.base.core.manage.common.CommonAbstract;
import hx.service.mobile.model.common.MobileCommonRequest;
import hx.service.mobile.manage.test.SignInManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @name: TestIndexControoler
 * @description: 基本法测试首页相关Controller
 * @author: huojiajin
 * @time: 2020/6/30 17:06
 */
@RestController
@RequestMapping("/test/")
public class TestIndexControoler extends CommonAbstract {

    @Autowired
    private SignInManager manager;

    @PostMapping("/sign")
    public String signIn(@RequestBody MobileCommonRequest request){
        return manager.signIn(request);
    }

    @PostMapping("/integral/rank")
    public String integralRank(@RequestBody MobileCommonRequest request){
        return manager.integralRank(request);
    }
}
