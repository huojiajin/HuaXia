package hx.service.manage.manage.acl;

import hx.service.manage.manage.model.CommonPageRequest;
import hx.service.manage.manage.model.CommonRequest;
import hx.service.manage.manage.model.acl.role.RoleAddRequest;
import hx.service.manage.manage.model.acl.role.RoleDeleteRequest;
import hx.service.manage.manage.model.acl.role.RoleEditRequest;
import hx.service.manage.manage.model.acl.role.RoleResourceRequest;
import org.springframework.transaction.annotation.Transactional;

/**
 * @name: RoleManager
 * @description: 角色Manager
 * @time: 2020/5/27 10:55
 */
public interface RoleManager {

    String query(CommonPageRequest request);

    String list(CommonRequest request);

    void add(RoleAddRequest roleAddRequest);

    void update(RoleEditRequest editRequest);

    void delete(RoleDeleteRequest deleteRequest);

    @Transactional
    void resourceConfig(RoleResourceRequest resourceRequest);
}
