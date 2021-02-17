package hx.service.mobile.manage.back;

import hx.service.mobile.model.kpi.ManpowerAPIRequest;

/**
 * @ClassName: ManpowerAPIManager
 * @Description: 人力KPIManager
 * @Author HuoJiaJin
 * @Date 2021/2/6 23:55
 * @Version 1.0
 **/
public interface ManpowerAPIManager {
    String api(ManpowerAPIRequest request);
}
