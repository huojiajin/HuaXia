package hx.service.manage.web.acl;

import hx.service.manage.dao.entity.common.BaseEntity;
import hx.service.manage.manage.acl.UserManager;
import hx.service.manage.manage.model.CommonPageRequest;
import hx.service.manage.manage.model.CommonResponse;
import hx.service.manage.manage.model.acl.user.UserAddRequest;
import hx.service.manage.manage.model.acl.user.UserDeleteRequest;
import hx.service.manage.manage.model.acl.user.UserEditRequest;
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
@RequestMapping("/manage/role")
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
        userManager.update(editRequest);
        CommonResponse response = new CommonResponse();
        response.setMessage("修改用户成功");
        return response.toJson();
    }

    @PostMapping("/delete")
    public String delete(@RequestBody UserDeleteRequest deleteRequest){
        userManager.delete(deleteRequest);
        CommonResponse response = new CommonResponse();
        response.setMessage("删除角色成功");
        return response.toJson();
    }
}
