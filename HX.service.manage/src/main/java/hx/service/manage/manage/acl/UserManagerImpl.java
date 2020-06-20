package hx.service.manage.manage.acl;

import hx.service.manage.dao.dict.ErrorType;
import hx.service.manage.dao.entity.Role;
import hx.service.manage.dao.entity.User;
import hx.service.manage.dao.repo.jpa.RoleRepo;
import hx.service.manage.dao.repo.jpa.UserRepo;
import hx.service.manage.dao.repo.request.UserPageRequest;
import hx.service.manage.dao.repo.request.common.Pagination;
import hx.service.manage.manage.common.AbstractManager;
import hx.service.manage.manage.model.CommonPageRequest;
import hx.service.manage.manage.model.CommonResponse;
import hx.service.manage.manage.model.acl.user.UserAddRequest;
import hx.service.manage.manage.model.acl.user.UserIdRequest;
import hx.service.manage.manage.model.acl.user.UserEditRequest;
import hx.service.manage.manage.tools.SecurityUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.Optional;

/**
 * @name: UserManagerImpl
 * @description: 用户ManagerImpl
 * @author: huojiajin
 * @time: 2020/5/27 17:20
 */
@Service
public class UserManagerImpl extends AbstractManager implements UserManager {

    @Autowired
    private UserRepo userRepo;
    @Autowired
    private RoleRepo roleRepo;

    @Override
    public User findByLoginName(String loginName){
        return userRepo.findByLoginName(loginName);
    }

    @Override
    public String query(CommonPageRequest request){
        CommonResponse response = new CommonResponse();
        UserPageRequest pageRequest = new UserPageRequest();
        BeanUtils.copyProperties(request, pageRequest);
        Pagination page = userRepo.page(pageRequest);
        response.setData(page);
        return response.toJson();
    }

    @Override
    public String add(UserAddRequest addRequest) {
        CommonResponse response = new CommonResponse();
        User existsUser = findByLoginName(addRequest.getEmployeeNum());
        if (existsUser != null){
            response.setError(ErrorType.USEREXISTS);
        }
        User user = new User();
        BeanUtils.copyProperties(addRequest, user);
        user.setLoginName(user.getEmployeeNum());
        //密码加密
        byte[] hashBytes = SecurityUtil.hash(addRequest.getEmployeeNum().getBytes(), SecurityUtil.HashType.SHA_1);
        String password = new BigInteger(1, hashBytes).toString(16);
        user.setPassword(password);
        user.setInsertTime(LocalDateTime.now());
        userRepo.persist(user);
        addSysLog("添加用户" + addRequest.getEmployeeNum(), addRequest.getToken());
        response.setMessage("添加用户成功");
        return response.toJson();
    }

    @Override
    public void update(UserEditRequest editRequest){
        User user = userRepo.findById(editRequest.getUserId()).get();
        BeanUtils.copyProperties(editRequest, user);
        user.setUpdateTime(LocalDateTime.now());
        userRepo.save(user);
    }

    @Override
    public String stop(UserIdRequest deleteRequest){
        CommonResponse response = new CommonResponse();
        Optional<User> op = userRepo.findById(deleteRequest.getUserId());
        if (op.isEmpty()){
            return response.setError(ErrorType.NOUSER);
        }
        userRepo.updateStop(deleteRequest.getUserId(), User.UserStatus.INVALID, LocalDateTime.now());
        addSysLog("停用角色" + op.get().getName(), deleteRequest.getToken());
        response.setMessage("停用用户成功");
        return response.toJson();
    }

    @Override
    public String start(UserIdRequest startRequest){
        CommonResponse response = new CommonResponse();
        Optional<User> op = userRepo.findById(startRequest.getUserId());
        if (op.isEmpty()){
            return response.setError(ErrorType.NOUSER);
        }
        Optional<Role> roleOp = roleRepo.findById(op.get().getRoleId());
        if (roleOp.isEmpty() || roleOp.get().isStop()){
            return response.setError(ErrorType.NOROLE);
        }
        userRepo.updateStop(startRequest.getUserId(), User.UserStatus.NORMAL, LocalDateTime.now());
        addSysLog("启动角色" + op.get().getName(), startRequest.getToken());
        response.setMessage("启用用户成功");
        return response.toJson();

    }
}
