package hx.service.manage.manage.acl;

import com.google.common.collect.Lists;
import hx.base.core.dao.dict.ErrorType;
import hx.base.core.dao.dict.ResourceType;
import hx.base.core.dao.entity.acl.Role;
import hx.base.core.dao.entity.acl.RoleResource;
import hx.base.core.dao.entity.acl.User;
import hx.base.core.dao.repo.jpa.acl.RoleRepo;
import hx.base.core.dao.repo.jpa.acl.RoleResourceRepo;
import hx.base.core.dao.repo.jpa.acl.UserRepo;
import hx.base.core.dao.repo.request.acl.RolePageRequest;
import hx.base.core.dao.repo.request.common.Pagination;
import hx.base.core.manage.model.CommonResponse;
import hx.service.manage.manage.AbstractManager;
import hx.service.manage.manage.model.CommonPageRequest;
import hx.service.manage.manage.model.CommonRequest;
import hx.service.manage.manage.model.acl.role.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
    @Autowired
    private UserRepo userRepo;

    @Override
    public String query(CommonPageRequest request){
        CommonResponse response = new CommonResponse();
        RolePageRequest pageRequest = new RolePageRequest();
        BeanUtils.copyProperties(request, pageRequest);
        Pagination page = roleRepo.page(pageRequest);
        response.setData(page);
        return response.toJson();
    }

    @Override
    public String list(CommonRequest request){
        CommonResponse response = new CommonResponse();
        RolePageRequest pageRequest = new RolePageRequest();
        List<Role> list = roleRepo.list(pageRequest);
        RoleListResponse data = new RoleListResponse();
        data.setRoleList(list);
        response.setData(data);
        return response.toJson();
    }

    @Override
    public String add(RoleAddRequest addRequest){
        CommonResponse response = new CommonResponse();
        Role role = new Role();
        BeanUtils.copyProperties(addRequest, role);
        role.setInsertTime(LocalDateTime.now());
        roleRepo.persist(role);
        addSysLog("添加角色" + addRequest.getName(), addRequest.getToken(), role.getId());
        response.setMessage("添加角色成功");
        return response.toJson();
    }

    @Override
    public String update(RoleEditRequest editRequest){
        CommonResponse response = new CommonResponse();
        Role role = roleRepo.findById(editRequest.getId()).get();
        BeanUtils.copyProperties(editRequest, role);
        role.setUpdateTime(LocalDateTime.now());
        roleRepo.save(role);
        addSysLog("修改角色" + editRequest.getName(), editRequest.getToken(), editRequest.getId());
        response.setMessage("修改角色成功");
        return response.toJson();
    }

    @Override
    public String delete(RoleIdRequest deleteRequest){
        CommonResponse response = new CommonResponse();
        Optional<Role> op = roleRepo.findById(deleteRequest.getRoleId());
        if (op.isEmpty()){
            return response.setError(ErrorType.NOROLE);
        }
        List<User> users = userRepo.listByRoleId(op.get().getId());
        if (!isEmpty(users)){
            List<User> normalUsers = users.stream().filter(u -> u.getStatus() == User.UserStatus.NORMAL).collect(Collectors.toList());
            if (!isEmpty(normalUsers)){
                return response.setError(ErrorType.HASUSER);
            }
        }
        roleRepo.updateDelete(deleteRequest.getRoleId());
        addSysLog("删除角色" + op.get().getName(), deleteRequest.getToken(), deleteRequest.getRoleId());
        response.setMessage("删除角色成功");
        return response.toJson();
    }

    @Override
    @Transactional
    public String resourceConfig(RoleResourceRequest resourceRequest){
        CommonResponse response = new CommonResponse();
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
        addSysLog("配置角色" + op.get().getName() + "的权限", resourceRequest.getToken(), resourceRequest.getRoleId());
        response.setMessage("配置权限成功");
        return response.toJson();
    }

    @Override
    public String resourceList(RoleIdRequest request){
        CommonResponse response = new CommonResponse();
        RoleResourceListResponse listResponse = new RoleResourceListResponse();
        List<RoleResource> roleResources = roleResourceRepo.listByRoleId(request.getRoleId());
        if (!isEmpty(roleResources)){
            List<Integer> codeList = roleResources.stream().map(r -> r.getResourceType().getCode()).collect(Collectors.toList());
            listResponse.setResourceCodeList(codeList);
        }
        response.setData(listResponse);
        return response.toJson();
    }
}
