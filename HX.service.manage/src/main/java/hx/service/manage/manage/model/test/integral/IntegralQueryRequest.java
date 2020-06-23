package hx.service.manage.manage.model.test.integral;

import hx.service.manage.manage.model.CommonPageRequest;

/**
 * @ClassName IntegralQueryRequest
 * @Description 积分查询Request
 * @Author HuoJiaJin
 * @Date 2020/6/23 19:46
 * @Version 1.0
 **/
public class IntegralQueryRequest extends CommonPageRequest {

    private String month;//年月yyyy-MM
    private String employeeNum;//员工编码

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getEmployeeNum() {
        return employeeNum;
    }

    public void setEmployeeNum(String employeeNum) {
        this.employeeNum = employeeNum;
    }
}
