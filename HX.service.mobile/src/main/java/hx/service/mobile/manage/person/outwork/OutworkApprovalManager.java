package hx.service.mobile.manage.person.outwork;

import hx.service.mobile.model.common.MobileCommonPageRequest;
import hx.service.mobile.model.common.MobileCommonRequest;
import hx.service.mobile.model.person.outwork.approval.OutworkApprovalApprovalRequest;
import hx.service.mobile.model.person.outwork.approval.OutworkApprovalDetailRequest;
import org.springframework.transaction.annotation.Transactional;

/**
 * @ClassName: OutworkApprovalManager
 * @Description: 离职审批相关Manager
 * @Author HuoJiaJin
 * @Date 2021/3/14 15:29
 * @Version 1.0
 **/
public interface OutworkApprovalManager {
    
    /**
     * @Name query
     * @Author HuoJiaJin
     * @Description 查询待审批列表
     * @Date 2021/3/14 16:13
     * @Param [request]
     * @Return java.lang.String
     **/
    String query(MobileCommonPageRequest request);

    String detail(OutworkApprovalDetailRequest request);

    @Transactional
    String approval(OutworkApprovalApprovalRequest request);
}
