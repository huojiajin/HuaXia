package hx.service.manage.dao.repo.request;

import hx.service.manage.dao.entity.User;
import hx.service.manage.dao.repo.request.common.HqlBuilder;
import hx.service.manage.dao.repo.request.common.JpaPageableDataRequest;

/**
 * @name: RoleRequest
 * @description: 角色分页Request
 * @author: huojiajin
 * @time: 2020/5/28 10:57
 */
public class UserPageRequest extends JpaPageableDataRequest<User> {

    public UserPageRequest() {
        this.orderBy = "list";
    }

    @Override
    public HqlBuilder toSelectHql() {
        HqlBuilder hql = new HqlBuilder("from " + User.class + " where status = 'NORMAL' ");
        return hql;
    }
}
