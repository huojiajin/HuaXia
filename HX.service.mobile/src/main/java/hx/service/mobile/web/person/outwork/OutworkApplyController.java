package hx.service.mobile.web.person.outwork;

import hx.base.core.manage.common.CommonAbstract;
import hx.service.mobile.manage.person.outwork.OutworkApplyManager;
import hx.service.mobile.model.common.MobileCommonRequest;
import hx.service.mobile.model.person.outwork.apply.OutworkApplyApplyRequest;
import hx.service.mobile.model.person.outwork.apply.OutworkApplyDetailRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: OutworkApplyController
 * @Description: 离职申请相关Controller
 * @Author HuoJiaJin
 * @Date 2021/3/14 18:24
 * @Version 1.0
 **/
@RestController
@RequestMapping("/outwork/apply")
public class OutworkApplyController extends CommonAbstract {
    @Autowired
    private OutworkApplyManager manager;

    @PostMapping("/query")
    public String query(@RequestBody MobileCommonRequest request){
        return manager.query(request);
    }

    @PostMapping("/view")
    public String detail(@RequestBody OutworkApplyDetailRequest request){
        return manager.detail(request);
    }

    @PostMapping("/apply")
    public String apply(@RequestBody OutworkApplyApplyRequest request){
        return manager.apply(request);
    }
}
