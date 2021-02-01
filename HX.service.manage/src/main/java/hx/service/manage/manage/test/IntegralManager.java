package hx.service.manage.manage.test;

import hx.service.manage.model.test.integral.IntegralQueryRequest;

/**
 * @ClassName IntegralManager
 * @Description 积分管理Manager
 * @Author HuoJiaJin
 * @Date 2020/6/23 19:43
 * @Version 1.0
 **/
public interface IntegralManager {

    String query(IntegralQueryRequest request);

    String export(IntegralQueryRequest request);
}
