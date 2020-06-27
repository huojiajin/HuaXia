package hx.service.manage.manage.model.login;

import hx.base.core.dao.entity.common.BaseEntity;

import java.util.List;

/**
 * @name: LoginResponse
 * @description: 登陆返回Response
 * @author: huojiajin
 * @time: 2020/5/27 15:44
 */
public class LoginResponse extends BaseEntity {

    private String token;//用户token

    private String employeeNum;//工号

    private String name;//姓名

    private String mobile;//手机号

    private List<ResourceModel> resourceList;//菜单权限

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
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

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public List<ResourceModel> getResourceList() {
        return resourceList;
    }

    public void setResourceList(List<ResourceModel> resourceList) {
        this.resourceList = resourceList;
    }
}
