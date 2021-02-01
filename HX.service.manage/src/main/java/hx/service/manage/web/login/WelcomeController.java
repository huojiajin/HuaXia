package hx.service.manage.web.login;

import hx.service.manage.manage.login.LoginManager;
import hx.service.manage.model.login.LoginRequest;
import hx.service.manage.web.common.MyBaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @name: WelcomeController
 * @description: 登陆Controller
 * @author: huojiajin
 * @time: 2020/5/27 15:01
 */
@RestController
@RequestMapping("/login")
public class WelcomeController extends MyBaseController {

    @Autowired
    private LoginManager loginManager;

    @RequestMapping("/verify")
    public String createVerifyImage(){
        return loginManager.getVerifyImage();
    }

    @PostMapping(value = "/login")
    public String login(@RequestBody LoginRequest loginRequest){
        return loginManager.login(loginRequest);
    }
}
