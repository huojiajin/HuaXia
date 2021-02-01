package hx.service.manage.model.quit;

import hx.service.manage.model.common.CommonRequest;

/**
 * @ClassName: QuitAssignEditRequest
 * @Description: 离职人员指派修改Request
 * @Author HuoJiaJin
 * @Date 2021/2/1 23:27
 * @Version 1.0
 **/
public class QuitAssignEditRequest extends CommonRequest {

    private String id;
    private String employeeNum;//工号
    private String name;//人员名称

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmployeeNum() {
        return employeeNum;
    }

    public void setEmployeeNum(String employeeNum) {
        this.employeeNum = employeeNum;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
