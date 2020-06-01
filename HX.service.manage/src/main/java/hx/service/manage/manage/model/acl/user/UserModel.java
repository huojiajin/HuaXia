package hx.service.manage.manage.model.acl.user;

import hx.service.manage.dao.entity.common.BaseEntity;

/**
 * @ClassName UserModel
 * @Description 用户model
 * @Author HuoJiaJin
 * @Date 19:38
 * @Version 1.0
 **/
public class UserModel extends BaseEntity {

    private String id;
    private String employeeNum;//工号
    private String roleName;// 用户所属角色名称
    private String name;// 用户名
    private String mobile;// 手机号

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

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
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
