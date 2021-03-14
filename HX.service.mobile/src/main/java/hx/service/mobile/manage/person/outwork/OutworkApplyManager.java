package hx.service.mobile.manage.person.outwork;

import hx.service.mobile.model.common.MobileCommonRequest;
import hx.service.mobile.model.person.outwork.apply.OutworkApplyApplyRequest;
import hx.service.mobile.model.person.outwork.apply.OutworkApplyDetailRequest;

/**
 * @ClassName: OutworkApplyManager
 * @Description: 离职申请相关Manager
 * @Author HuoJiaJin
 * @Date 2021/3/14 1:45
 * @Version 1.0
 **/
public interface OutworkApplyManager {

    /**
     * @Name apply
     * @Author HuoJiaJin
     * @Description 提交申请
     * @Date 2021/3/14 13:19
     * @Param [request]
     * @Return java.lang.String
     **/
    String apply(OutworkApplyApplyRequest request);

    /**
     * @Name query
     * @Author HuoJiaJin
     * @Description 列表查询
     * @Date 2021/3/14 13:54
     * @Param [request]
     * @Return java.lang.String
     **/
    String query(MobileCommonRequest request);

    /**
     * @Name detail
     * @Author HuoJiaJin
     * @Description 查看申请详情
     * @Date 2021/3/14 13:54
     * @Param [request]
     * @Return java.lang.String
     **/
    String detail(OutworkApplyDetailRequest request);
}
