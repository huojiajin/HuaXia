package hx.service.manage.model.quit;

import hx.service.manage.model.common.CommonPageRequest;

/**
 * @ClassName: QuitApplyQueryRequest
 * @Description: 离职申请查询Request
 * @Author HuoJiaJin
 * @Date 2021/2/2 0:04
 * @Version 1.0
 **/
public class QuitApplyQueryRequest extends CommonPageRequest {

    private String employeeNum;//工号
    private int status;//状态

    public String getEmployeeNum() {
        return employeeNum;
    }

    public void setEmployeeNum(String employeeNum) {
        this.employeeNum = employeeNum;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
