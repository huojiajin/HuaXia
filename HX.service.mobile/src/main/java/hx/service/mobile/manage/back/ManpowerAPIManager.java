package hx.service.mobile.manage.back;

import hx.service.mobile.model.back.kpi.ManpowerAPIRequest;

/**
 * @ClassName: ManpowerAPIManager
 * @Description: 人力KPIManager
 * @Author HuoJiaJin
 * @Date 2021/2/6 23:55
 * @Version 1.0
 **/
public interface ManpowerAPIManager {
    /**
     * @Name api
     * @Author HuoJiaJin
     * @Description 获取人力API
     * @Date 2021/3/13 15:46
     * @Param [request]
     * @Return java.lang.String
     **/
    String api(ManpowerAPIRequest request);
}
