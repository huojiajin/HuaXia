package hx.service.manage.manage.acl;

import hx.service.manage.dao.dict.ErrorType;
import hx.service.manage.dao.entity.User;
import hx.service.manage.dao.repo.jpa.UserRepo;
import hx.service.manage.dao.repo.request.UserPageRequest;
import hx.service.manage.dao.repo.request.common.Pagination;
import hx.service.manage.manage.common.AbstractManager;
import hx.service.manage.manage.model.CommonPageRequest;
import hx.service.manage.manage.model.CommonResponse;
import hx.service.manage.manage.model.acl.user.UserAddRequest;
import hx.service.manage.manage.model.acl.user.UserDeleteRequest;
import hx.service.manage.manage.model.acl.user.UserEditRequest;
import hx.service.manage.manage.tools.SecurityUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
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
        response.setData(page.toJson());
        return response.toJson();
    }

    @Override
    public String add(UserAddRequest addRequest){
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
        userRepo.persist(user);
        addSysLog("添加用户" + addRequest.getEmployeeNum(), addRequest.getToken());
        response.setMessage("添加用户成功");
        return response.toJson();
    }

    @Override
    public void update(UserEditRequest editRequest){
        User user = userRepo.findById(editRequest.getUserId()).get();
        BeanUtils.copyProperties(editRequest, user);
        userRepo.save(user);
    }

    @Override
    public void delete(UserDeleteRequest deleteRequest){
        Optional<User> op = userRepo.findById(deleteRequest.getUserId());
        addSysLog("删除角色" + op.get().getName(), deleteRequest.getToken());
        userRepo.deleteById(deleteRequest.getUserId());
    }
}
