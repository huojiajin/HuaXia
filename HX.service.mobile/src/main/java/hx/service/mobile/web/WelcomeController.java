package hx.service.mobile.web;

import hx.service.manage.web.MyBaseController;
import hx.service.mobile.manage.login.LoginManager;
import hx.service.mobile.manage.model.login.LoginRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName WelcomeController
 * @Description 登录Controller
 * @Author HuoJiaJin
 * @Date 2020/6/23 21:43
 * @Version 1.0
 **/
@RestController
@RequestMapping("/login")
public class WelcomeController extends MyBaseController {

    @Autowired
    private LoginManager manager;

    @PostMapping("/verify")
    public String getLoginInfo(){
        return manager.loginInfo();
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest request){
        return manager.login(request);
    }
}
