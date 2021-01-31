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

    /**
     * @Name query
     * @Author HuoJiaJin
     * @Description 查询
     * @Date 2021/1/31 15:54
     * @Param [request]
     * @return java.lang.String
     **/
    String query(SituationQueryRequest request);

    /**
     * @Name export
     * @Author HuoJiaJin
     * @Description 导出
     * @Date 2021/1/31 15:54
     * @Param [request]
     * @return java.lang.String
     **/
    String export(SituationQueryRequest request);
}
