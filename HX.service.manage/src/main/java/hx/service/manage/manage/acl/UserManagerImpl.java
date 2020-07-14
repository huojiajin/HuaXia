package hx.service.manage.manage.acl;

import hx.base.core.dao.dict.ErrorType;
import hx.base.core.dao.entity.acl.Role;
import hx.base.core.dao.entity.acl.User;
import hx.base.core.dao.repo.jpa.acl.RoleRepo;
import hx.base.core.dao.repo.jpa.acl.UserRepo;
import hx.base.core.dao.repo.request.acl.UserPageRequest;
import hx.base.core.dao.repo.request.common.Pagination;
import hx.base.core.manage.model.CommonResponse;
import hx.base.core.manage.tools.JsonTools;
import hx.base.core.manage.tools.RegexValid;
import hx.base.core.manage.tools.SecurityUtil;
import hx.service.manage.manage.AbstractManager;
import hx.service.manage.manage.MyMecachedPrefix;
import hx.service.manage.manage.model.CommonPageRequest;
import hx.service.manage.manage.model.acl.user.*;
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
        page.convertResult(this::convert);
        response.setData(page);
        return response.toJson();
    }

    private UserModel convert(User user){
        UserModel model = new UserModel();
        BeanUtils.copyProperties(user, model);
        Optional<Role> op = roleRepo.findById(user.getRoleId());
        if (op.isPresent()){
            model.setRoleName(op.get().getName());
        }
        return model;
    }

    @Override
    public String add(UserAddRequest addRequest) {
        CommonResponse response = new CommonResponse();
        User existsUser = findByLoginName(addRequest.getEmployeeNum());
        if (existsUser != null){
            response.setError(ErrorType.USEREXISTS);
        }
        if (!RegexValid.validMobile(addRequest.getMobile())){
            return response.setError(ErrorType.VALID, "手机号格式不正确");
        }
        User oldUser = userRepo.findByLoginName(addRequest.getEmployeeNum());
        if (oldUser != null) {
            return response.setError(ErrorType.VALID, "工号已存在");
        }
        User user = new User();
        BeanUtils.copyProperties(addRequest, user);
        user.setLoginName(user.getEmployeeNum());
        //密码加密
        byte[] hashBytes = SecurityUtil.hash(addRequest.getEmployeeNum().getBytes(), SecurityUtil.HashType.MD5);
        String password = new BigInteger(1, hashBytes).toString(16);
        user.setPassword(password);
        user.setInsertTime(LocalDateTime.now());
        userRepo.persist(user);
        addSysLog("添加用户" + addRequest.getEmployeeNum(), addRequest.getToken(), user.getId());
        response.setMessage("添加用户成功");
        return response.toJson();
    }

    @Override
    public String update(UserEditRequest editRequest){
        CommonResponse response = new CommonResponse();
        if (!RegexValid.validMobile(editRequest.getMobile())){
            return response.setError(ErrorType.VALID, "手机号格式不正确");
        }
        User user = userRepo.findById(editRequest.getId()).get();
        BeanUtils.copyProperties(editRequest, user);
        user.setUpdateTime(LocalDateTime.now());
        addSysLog("修改用户" + editRequest.getName(), editRequest.getToken(), user.getId());
        response.setMessage("修改用户成功");
        userRepo.save(user);
        return response.toJson();
    }

    @Override
    public String stop(UserIdRequest deleteRequest){
        CommonResponse response = new CommonResponse();
        Optional<User> op = userRepo.findById(deleteRequest.getId());
        if (op.isEmpty()){
            return response.setError(ErrorType.NOUSER);
        }
        userRepo.updateStop(deleteRequest.getId(), User.UserStatus.INVALID, LocalDateTime.now());
        addSysLog("停用角色" + op.get().getName(), deleteRequest.getToken(), deleteRequest.getId());
        response.setMessage("停用用户成功");
        return response.toJson();
    }

    @Override
    public String start(UserIdRequest startRequest){
        CommonResponse response = new CommonResponse();
        Optional<User> op = userRepo.findById(startRequest.getId());
        if (op.isEmpty()){
            return response.setError(ErrorType.NOUSER);
        }
        Optional<Role> roleOp = roleRepo.findById(op.get().getRoleId());
        if (roleOp.isEmpty() || roleOp.get().isStop()){
            return response.setError(ErrorType.NOROLE);
        }
        userRepo.updateStop(startRequest.getId(), User.UserStatus.NORMAL, LocalDateTime.now());
        addSysLog("启动角色" + op.get().getName(), startRequest.getToken(), startRequest.getId());
        response.setMessage("启用用户成功");
        return response.toJson();

    }

    @Override
    public String passwordEdit(UserPasswordEditRequest request){
        CommonResponse response = new CommonResponse();
        User user = getUser(request.getToken());
        if (user == null) return response.setError(ErrorType.NOLOGIN);
        //校验密码
        byte[] hashBytes = SecurityUtil.hash(request.getPassword().getBytes(), SecurityUtil.HashType.MD5);
        String requestPassword = new BigInteger(1, hashBytes).toString(16);
        if (!requestPassword.equals(user.getPassword())){
            return response.setError(ErrorType.PASSWORD);
        }

        //新密码加密
        byte[] newHashBytes = SecurityUtil.hash(request.getNewPassword().getBytes(), SecurityUtil.HashType.MD5);
        String password = new BigInteger(1, newHashBytes).toString(16);
        userRepo.updatePassword(user.getId(), password, LocalDateTime.now());

        addSysLog("修改密码" + user.getName(), request.getToken(), user.getId());

        response.setMessage("修改密码成功");
        return response.toJson();
    }
}
