package hx.service.manage.dao.repo.request;

import hx.service.manage.dao.entity.Role;
import hx.service.manage.dao.repo.request.common.HqlBuilder;
import hx.service.manage.dao.repo.request.common.JpaPageableDataRequest;

/**
 * @name: RoleRequest
 * @description: 角色分页Request
 * @author: huojiajin
 * @time: 2020/5/28 10:57
 */
public class RolePageRequest extends JpaPageableDataRequest<Role> {

    public RolePageRequest() {
        this.orderBy = "list";
    }

    @Override
    public HqlBuilder toSelectHql() {
        HqlBuilder hql = new HqlBuilder("from " + Role.class + " where isDelete = 0 ");
        return hql;
    }
}
