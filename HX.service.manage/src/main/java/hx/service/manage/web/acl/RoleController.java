package hx.service.manage.web.acl;

import hx.service.manage.manage.acl.RoleManager;
import hx.service.manage.manage.model.CommonPageRequest;
import hx.service.manage.manage.model.CommonRequest;
import hx.service.manage.manage.model.CommonResponse;
import hx.service.manage.manage.model.acl.role.RoleAddRequest;
import hx.service.manage.manage.model.acl.role.RoleDeleteRequest;
import hx.service.manage.manage.model.acl.role.RoleEditRequest;
import hx.service.manage.manage.model.acl.role.RoleResourceRequest;
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
        roleManager.add(addRequest);
        CommonResponse response = new CommonResponse();
        response.setMessage("添加角色成功");
        return response.toJson();
    }

    @PostMapping("/edit")
    public String edit(@RequestBody RoleEditRequest editRequest){
        roleManager.update(editRequest);
        CommonResponse response = new CommonResponse();
        response.setMessage("修改角色成功");
        return response.toJson();
    }

    @PostMapping("/delete")
    public String delete(@RequestBody RoleDeleteRequest deleteRequest){
        roleManager.delete(deleteRequest);
        CommonResponse response = new CommonResponse();
        response.setMessage("删除角色成功");
        return response.toJson();
    }

    @PostMapping("/resource")
    public String resourceConfig(@RequestBody RoleResourceRequest resourceRequest){
        roleManager.resourceConfig(resourceRequest);
        CommonResponse response = new CommonResponse();
        response.setMessage("配置权限成功");
        return response.toJson();
    }
}
