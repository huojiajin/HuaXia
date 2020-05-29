package hx.service.manage.manage.acl;

import hx.service.manage.dao.entity.User;
import hx.service.manage.manage.model.CommonPageRequest;
import hx.service.manage.manage.model.acl.user.UserAddRequest;
import hx.service.manage.manage.model.acl.user.UserDeleteRequest;
import hx.service.manage.manage.model.acl.user.UserEditRequest;

/**
 * @name: UserManager
 * @description: 用户Manager
 * @author: huojiajin
 * @time: 2020/5/27 17:19
 */
public interface UserManager {
    /**
     * @name: findByLoginName
     * @description: 根据登陆名查找用户
     * @author: huojiajin
     * @para: [loginName]
     * @return: hx.service.manage.dao.entity.User
    **/
    User findByLoginName(String loginName);

    String query(CommonPageRequest request);

    String add(UserAddRequest addRequest);

    void update(UserEditRequest editRequest);

    void delete(UserDeleteRequest deleteRequest);
}
