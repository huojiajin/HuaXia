package hx.service.manage.model.acl.user;

import hx.base.core.dao.entity.acl.User;
import hx.base.core.dao.entity.common.BaseEntity;

/**
 * @ClassName UserModel
 * @Description 用户model
 * @Author HuoJiaJin
 * @Date 19:38
 * @Version 1.0
 **/
public class UserModel extends User {

    private String roleName;// 用户所属角色名称

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
