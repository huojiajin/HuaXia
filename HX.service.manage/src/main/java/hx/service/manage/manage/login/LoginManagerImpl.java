package hx.service.manage.manage.login;

import hx.base.core.dao.dict.acl.ErrorType;
import hx.base.core.dao.entity.acl.RoleResource;
import hx.base.core.dao.entity.acl.User;
import hx.base.core.dao.repo.jpa.acl.RoleResourceRepo;
import hx.base.core.manage.common.CommonAbstract;
import hx.base.core.manage.model.CommonResponse;
import hx.base.core.manage.tools.SecurityUtil;
import hx.service.manage.manage.tools.MyMecachedPrefix;
import hx.service.manage.manage.tools.VerifyImage;
import hx.service.manage.manage.acl.UserManager;
import hx.service.manage.model.login.LoginRequest;
import hx.service.manage.model.login.LoginResponse;
import hx.service.manage.model.login.ResourceModel;
import hx.service.manage.model.login.VerifyResponse;
import net.spy.memcached.MemcachedClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigInteger;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * @name: LoginManagerImpl
 * @description: 登陆ManagerImpl
 * @author: huojiajin
 * @time: 2020/5/27 15:52
 */
@Service
public class LoginManagerImpl extends CommonAbstract implements LoginManager{

    @Autowired
    private MemcachedClient memcachedClient;
    @Autowired
    private UserManager userManager;
    @Autowired
    private RoleResourceRepo roleResourceRepo;

    @Override
    public String getVerifyImage(){

        CommonResponse response = new CommonResponse();

        VerifyImage verifyImage = new VerifyImage();
        verifyImage.generate();
        String verifyId = UUID.randomUUID().toString().replace("-", "");
        //存到memcached
        memcachedClient.set(MyMecachedPrefix.loginVerifyImagePrefix + verifyId, 5 * 60, verifyImage.getVerifyCode());
        try {
            VerifyResponse verifyResponse = new VerifyResponse(verifyImage.getBuffImg(), verifyId);
            response.setData(verifyResponse);
        } catch (IOException e) {
            logger.error("", e);
            response.setError(ErrorType.CONVERT);
        }
        return response.toJson();
    }

    @Override
    public String login(LoginRequest loginRequest){

        CommonResponse response = new CommonResponse();
        //校验验证码
        String verifyCode = (String)memcachedClient.get(MyMecachedPrefix.loginVerifyImagePrefix + loginRequest.getVerifyId());
        if (!hasText(verifyCode)){
            if (!toUpper(loginRequest.getVerifyCode()).equals(toUpper(verifyCode))) {
                return response.setError(ErrorType.VERIFY);
            }else{
                response.setSuccess(false);
                response.setErrCode(ErrorType.VERIFY.getErrCode());
                response.setMessage("验证码已超时");
                return response.toJson();
            }
        }
        //校验用户名
        User user = userManager.findByLoginName(loginRequest.getLoginName());
        if (user == null){
            return response.setError(ErrorType.LOGIN);
        }else if(user.getStatus() == User.UserStatus.INVALID){
            response.setSuccess(false);
            response.setErrCode(ErrorType.LOGIN.getErrCode());
            response.setMessage("用户已停用");
            return response.toJson();
        }
        //校验密码
        byte[] hashBytes = SecurityUtil.hash(loginRequest.getPassword().getBytes(), SecurityUtil.HashType.MD5);
        String requestPassword = new BigInteger(1, hashBytes).toString(16);
        if (!requestPassword.equals(user.getPassword())){
            return response.setError(ErrorType.LOGIN);
        }
        //存放登陆信息
        String token = UUID.randomUUID().toString().replace("-", "");
        memcachedClient.set(MyMecachedPrefix.loginTokenPrefix + token, 30 * 60, user.toJson());

        //拼装返回信息
        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setToken(token);
        loginResponse.setEmployeeNum(user.getEmployeeNum());
        loginResponse.setName(user.getName());
        loginResponse.setMobile(user.getMobile());
        List<RoleResource> roleResources = roleResourceRepo.listByRoleId(user.getRoleId());
        List<ResourceModel> resourceModelList = roleResources.stream().map(this::getResourceModel).collect(Collectors.toList());
        loginResponse.setResourceList(resourceModelList);
        response.setData(loginResponse);

        //存放菜单权限至memcached
        List<Integer> resourceCodeList = roleResources.stream().map(r -> r.getResourceType().getCode()).collect(Collectors.toList());
        memcachedClient.set(MyMecachedPrefix.loginResourcePrefix + user.getId(), 10*60, resourceCodeList);

        return response.toJson();
    }

    private ResourceModel getResourceModel(RoleResource r) {
        ResourceModel resourceModel = new ResourceModel();
        resourceModel.setResourceCode(r.getResourceType().getCode());
        resourceModel.setResourceName(r.getResourceType().getValue());
        return resourceModel;
    }

    private String toUpper(String str){
        char[] c = str.toCharArray();// 把字符串转化为字符数组

        for (int i = 0; i < c.length; i++) {// 循环字符数组

            if (Character.isUpperCase(c[i])) {// 判断是否是大写，如果是大写的就转化为小写。
                // 把c[i]转化为int型，加32后在转化为char型，重新赋值给c[i];
                c[i] = (char) ((int) c[i] + 32);
            }else if (Character.isDigit(c[i])) {// 判断是否是数字
                c[i] = c[i];
            }
        }
        return new String(c);
    }
}
