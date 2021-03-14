package hx.service.mobile.manage.back;

import hx.service.mobile.model.back.salary.SalaryQueryRequest;

/**
 * @ClassName: SalaryManager.java
 * @Description: 追踪支持相关Manager
 * @Author HuoJiaJin
 * @Date 2021/2/24 20:43
 * @Version 1.0
**/
public interface SalaryManager {
    
    /**
     * @Name query
     * @Author HuoJiaJin
     * @Description 追踪支持查询
     * @Date 2021/2/24 21:02
     * @Param [request]
     * @Return java.lang.String
     **/
    String query(SalaryQueryRequest request);
}
