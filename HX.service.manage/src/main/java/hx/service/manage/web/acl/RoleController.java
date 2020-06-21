package hx.service.manage.web.acl;

import hx.service.manage.manage.acl.RoleManager;
import hx.service.manage.manage.model.CommonPageRequest;
import hx.service.manage.manage.model.CommonRequest;
import hx.service.manage.manage.model.acl.role.*;
import hx.service.manage.web.MyBaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @name: RoleController
 * @description: 角色管理Controller
 * @author: huojiajin
 * @time: 2020/5/27 10:46
 */
@RestController
@RequestMapping("/manage/role")
public class RoleController extends MyBaseController {

    @Autowired
    private RoleManager roleManager;

    @PostMapping("/query")
    public String query(@RequestBody CommonPageRequest pageRequest, HttpServletRequest request){
        return roleManager.query(pageRequest);
    }

    @PostMapping("/list")
    public String query(@RequestBody CommonRequest request){
        return roleManager.list(request);
    }

    @PostMapping("/add")
    public String add(@RequestBody RoleAddRequest addRequest){
        return roleManager.add(addRequest);
    }

    @PostMapping("/edit")
    public String edit(@RequestBody RoleEditRequest editRequest){

        return roleManager.update(editRequest);
    }

    @PostMapping("/delete")
    public String delete(@RequestBody RoleIdRequest deleteRequest){
        return roleManager.delete(deleteRequest);
    }

    @PostMapping("/resource/config")
    public String resourceConfig(@RequestBody RoleResourceRequest resourceRequest){
        return roleManager.resourceConfig(resourceRequest);
    }

    @PostMapping("/resource/list")
    public String resourceList(@RequestBody RoleIdRequest request){
        return roleManager.resourceList(request);
    }
}
