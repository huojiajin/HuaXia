package hx.service.manage.web.test;

import hx.service.manage.model.test.integral.IntegralQueryRequest;
import hx.service.manage.manage.test.IntegralManager;
import hx.service.manage.web.common.MyBaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName IntegralController
 * @Description 积分管理Controller
 * @Author HuoJiaJin
 * @Date 2020/6/23 20:13
 * @Version 1.0
 **/
@RestController
@RequestMapping("/test/integral")
public class IntegralController extends MyBaseController {

    @Autowired
    private IntegralManager manager;

    @PostMapping("/query")
    public String query(@RequestBody IntegralQueryRequest request){
        return manager.query(request);
    }

    @PostMapping("/export")
    public String export(@RequestBody IntegralQueryRequest request){
        return manager.export(request);
    }
}
