package hx.service.manage.manage.acl;

import hx.base.core.dao.entity.acl.User;
import hx.service.manage.model.common.CommonPageRequest;
import hx.service.manage.model.acl.user.UserAddRequest;
import hx.service.manage.model.acl.user.UserEditRequest;
import hx.service.manage.model.acl.user.UserIdRequest;

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
     * @return: hx.service.manage.dao.entity.acl.User
    **/
    User findByLoginName(String loginName);

    /**
     * @Name query
     * @Author HuoJiaJin
     * @Description 用户分页查询
     * @Date 2020/7/12 14:56
     * @Param [request]
     * @return java.lang.String
     **/
    String query(CommonPageRequest request);

    /**
     * @Name add
     * @Author HuoJiaJin
     * @Description 添加用户
     * @Date 2020/7/12 14:56
     * @Param [addRequest]
     * @return java.lang.String
     **/
    String add(UserAddRequest addRequest);

    /**
     * @Name update
     * @Author HuoJiaJin
     * @Description 用户修改
     * @Date 2020/7/12 14:56
     * @Param [editRequest]
     * @return java.lang.String
     **/
    String update(UserEditRequest editRequest);

    /**
     * @Name stop
     * @Author HuoJiaJin
     * @Description 用户停用
     * @Date 2020/7/12 14:57
     * @Param [deleteRequest]
     * @return java.lang.String
     **/
    String stop(UserIdRequest deleteRequest);

    /**
     * @Name start
     * @Author HuoJiaJin
     * @Description 用户启用
     * @Date 2020/7/12 14:57
     * @Param [startRequest]
     * @return java.lang.String
     **/
    String start(UserIdRequest startRequest);
}
