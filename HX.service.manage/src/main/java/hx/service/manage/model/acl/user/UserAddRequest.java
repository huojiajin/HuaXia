package hx.service.manage.model.acl.user;

import hx.service.manage.model.common.CommonRequest;

/**
 * @name: UserAddRequest
 * @description: 用户添加Request
 * @author: huojiajin
 * @time: 2020/5/28 15:01
 */
public class UserAddRequest extends CommonRequest {

    private String employeeNum;//工号
    private String roleId;// 用户所属角色Id
    private String name;// 用户名
    private String mobile;// 手机号

    public String getEmployeeNum() {
        return employeeNum;
    }

    public void setEmployeeNum(String employeeNum) {
        this.employeeNum = employeeNum;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}
