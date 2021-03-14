package hx.service.mobile.web.person.outwork;

import hx.base.core.manage.common.CommonAbstract;
import hx.service.mobile.manage.person.outwork.OutworkApprovalManager;
import hx.service.mobile.model.common.MobileCommonRequest;
import hx.service.mobile.model.person.outwork.approval.OutworkApprovalApprovalRequest;
import hx.service.mobile.model.person.outwork.approval.OutworkApprovalDetailRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: OutworkApprovalController
 * @Description: 离职审批相关Controller
 * @Author HuoJiaJin
 * @Date 2021/3/14 18:24
 * @Version 1.0
 **/
@RestController
@RequestMapping("/outwork/approval")
public class OutworkApprovalController extends CommonAbstract {
    @Autowired
    private OutworkApprovalManager manager;

    @PostMapping("/query")
    public String query(@RequestBody MobileCommonRequest request){
        return manager.query(request);
    }

    @PostMapping("/view")
    public String detail(@RequestBody OutworkApprovalDetailRequest request){
        return manager.detail(request);
    }

    @PostMapping("/approval")
    public String apply(@RequestBody OutworkApprovalApprovalRequest request){
        return manager.approval(request);
    }
}
