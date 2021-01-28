package hx.service.manage.manage.staff;

import hx.service.manage.manage.model.staff.situation.SituationQueryRequest;

/**
 * @ClassName: SituationManager
 * @Description: 人员情况统计Manager
 * @Author huojiajin
 * @Date 2021/1/29 0:38
 * @Version 1.0
**/
public interface SituationManager {
    String query(SituationQueryRequest request);
}
