package hx.service.manage.manage.acl;

import hx.service.manage.model.common.CommonPageRequest;
import hx.service.manage.model.common.CommonRequest;
import hx.service.manage.model.acl.role.RoleAddRequest;
import hx.service.manage.model.acl.role.RoleEditRequest;
import hx.service.manage.model.acl.role.RoleIdRequest;
import hx.service.manage.model.acl.role.RoleResourceRequest;

/**
 * @name: RoleManager
 * @description: 角色Manager
 * @time: 2020/5/27 10:55
 */
public interface RoleManager {

    String query(CommonPageRequest request);

    String list(CommonRequest request);

    String add(RoleAddRequest roleAddRequest);

    String update(RoleEditRequest editRequest);

    String delete(RoleIdRequest deleteRequest);

    String resourceConfig(RoleResourceRequest resourceRequest);

    String resourceList(RoleIdRequest request);
}
