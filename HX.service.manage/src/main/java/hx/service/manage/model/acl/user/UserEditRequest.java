package hx.service.manage.model.acl.user;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import hx.service.manage.model.common.CommonRequest;

/**
 * @name: UserEditRequest
 * @description: 用户修改Request
 * @author: huojiajin
 * @time: 2020/5/28 15:10
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserEditRequest extends CommonRequest {

    private String id;//用户ID
    private String name;// 用户名
    private String mobile;// 手机号
    private String roleId;// 用户所属角色Id

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }
}
