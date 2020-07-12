package hx.service.manage.web.acl;

import hx.base.core.dao.entity.common.BaseEntity;
import hx.base.core.manage.model.CommonResponse;
import hx.service.manage.manage.acl.UserManager;
import hx.service.manage.manage.model.CommonPageRequest;
import hx.service.manage.manage.model.CommonRequest;
import hx.service.manage.manage.model.acl.user.UserAddRequest;
import hx.service.manage.manage.model.acl.user.UserEditRequest;
import hx.service.manage.manage.model.acl.user.UserIdRequest;
import hx.service.manage.manage.model.acl.user.UserPasswordEditRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @name: UserController
 * @description: 用户Controller
 * @author: huojiajin
 * @time: 2020/5/28 14:59
 */
@RestController
@RequestMapping("/user")
public class UserController extends BaseEntity {

    @Autowired
    private UserManager userManager;

    @PostMapping("/query")
    public String query(@RequestBody CommonPageRequest request){
        return userManager.query(request);
    }

    @PostMapping("/add")
    public String add(@RequestBody UserAddRequest addRequest){
        return userManager.add(addRequest);
    }

    @PostMapping("/edit")
    public String edit(@RequestBody UserEditRequest editRequest){
        return userManager.update(editRequest);
    }

    @PostMapping("/stop")
    public String stop(@RequestBody UserIdRequest deleteRequest){
        return userManager.stop(deleteRequest);
    }

    @PostMapping("/start")
    public String start(@RequestBody UserIdRequest startRequest){
        return userManager.start(startRequest);
    }

    @PostMapping("/pwedit")
    public String passwordEdit(@RequestBody UserPasswordEditRequest request){
        return  userManager.passwordEdit(request);
    }
}
