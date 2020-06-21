package hx.service.manage.dao.repo.request.acl;

import hx.service.manage.dao.entity.acl.User;
import hx.service.manage.dao.repo.request.common.HqlBuilder;
import hx.service.manage.dao.repo.request.common.JpaPageableDataRequest;

/**
 * @name: RoleRequest
 * @description: 角色分页Request
 * @author: huojiajin
 * @time: 2020/5/28 10:57
 */
public class UserPageRequest extends JpaPageableDataRequest<User> {

    @Override
    public HqlBuilder toSelectHql() {
        HqlBuilder hql = new HqlBuilder("from " + clazz.getName() + " where 1 = 1 ");
        return hql;
    }
}
