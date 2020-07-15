package hx.service.manage.web;

import hx.service.manage.manage.login.LoginUserManager;
import hx.service.manage.manage.model.CommonRequest;
import hx.service.manage.manage.model.acl.user.UserPasswordEditRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @name: LoginUserController
 * @description: 登陆用户相关操作Controller
 * @author: huojiajin
 * @time: 2020/7/15 15:12
 */
@RestController
@RequestMapping("/user")
public class LoginUserController {

    @Autowired
    private LoginUserManager manager;

    @PostMapping("/pwedit")
    public String passwordEdit(@RequestBody UserPasswordEditRequest request){
        return  manager.passwordEdit(request);
    }

    @PostMapping("/loginout")
    public String loginout(@RequestBody CommonRequest request){
        return  manager.loginout(request);
    }
}
