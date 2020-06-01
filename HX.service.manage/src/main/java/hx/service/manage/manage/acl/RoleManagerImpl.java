package hx.service.manage.manage.acl;

import com.google.common.collect.Lists;
import hx.service.manage.dao.dict.ErrorType;
import hx.service.manage.dao.dict.ResourceType;
import hx.service.manage.dao.entity.Role;
import hx.service.manage.dao.entity.RoleResource;
import hx.service.manage.dao.repo.jpa.RoleRepo;
import hx.service.manage.dao.repo.jpa.RoleResourceRepo;
import hx.service.manage.dao.repo.request.RolePageRequest;
import hx.service.manage.dao.repo.request.common.Pagination;
import hx.service.manage.manage.common.AbstractManager;
import hx.service.manage.manage.model.CommonPageRequest;
import hx.service.manage.manage.model.CommonRequest;
import hx.service.manage.manage.model.CommonResponse;
import hx.service.manage.manage.model.acl.role.RoleAddRequest;
import hx.service.manage.manage.model.acl.role.RoleDeleteRequest;
import hx.service.manage.manage.model.acl.role.RoleEditRequest;
import hx.service.manage.manage.model.acl.role.RoleResourceRequest;
import hx.service.manage.manage.tools.JsonTools;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * @name: RoleManagerImpl
 * @description: 角色相关ManagerImpl
 * @author: huojiajin
 * @time: 2020/5/28 10:48
 */
@Service
public class RoleManagerImpl extends AbstractManager implements RoleManager {

    @Autowired
    private RoleRepo roleRepo;
    @Autowired
    private RoleResourceRepo roleResourceRepo;

    @Override
    public String query(CommonPageRequest request){
        CommonResponse response = new CommonResponse();
        RolePageRequest pageRequest = new RolePageRequest();
        BeanUtils.copyProperties(request, pageRequest);
        Pagination page = roleRepo.page(pageRequest);
        response.setData(page.toJson());
        return response.toJson();
    }

    @Override
    public String list(CommonRequest request){
        CommonResponse response = new CommonResponse();
        RolePageRequest pageRequest = new RolePageRequest();
        List<Role> list = roleRepo.list(pageRequest);
        try {
            response.setData(JsonTools.toJsonStr(list));
        } catch (IOException e) {
            logger.error("", e);
            response.setError(ErrorType.CONVERT);
        }
        return response.toJson();
    }

    @Override
    public void add(RoleAddRequest addRequest){
        Role role = new Role();
        BeanUtils.copyProperties(addRequest, role);
        roleRepo.persist(role);
        addSysLog("添加角色" + addRequest.getName(), addRequest.getToken());
    }

    @Override
    public void update(RoleEditRequest editRequest){
        Role role = roleRepo.findById(editRequest.getId()).get();
        BeanUtils.copyProperties(editRequest, role);
        roleRepo.save(role);
        addSysLog("修改角色" + editRequest.getName(), editRequest.getToken());
    }

    @Override
    public void delete(RoleDeleteRequest deleteRequest){
        Optional<Role> op = roleRepo.findById(deleteRequest.getRoleId());
        addSysLog("删除角色" + op.get().getName(), deleteRequest.getToken());
        roleRepo.updateDelete(deleteRequest.getRoleId(), LocalDateTime.now());
    }

    @Override
    @Transactional
    public void resourceConfig(RoleResourceRequest resourceRequest){
        List<RoleResource> roleResourceList = Lists.newArrayList();
        String roleId = resourceRequest.getRoleId();
        Optional<Role> op = roleRepo.findById(roleId);
        for (Integer code : resourceRequest.getResourceCodeList()) {
            RoleResource roleResource = new RoleResource();
            roleResource.setRoleId(roleId);
            roleResource.setResourceType(ResourceType.fromCode(code));
            roleResourceList.add(roleResource);
        }
        roleResourceRepo.deleteByRoleId(roleId);
        roleResourceRepo.persistAll(roleResourceList);
        addSysLog("配置角色" + op.get().getName() + "的权限", resourceRequest.getToken());
    }
}
