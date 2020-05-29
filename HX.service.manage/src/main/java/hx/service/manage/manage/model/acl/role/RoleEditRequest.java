package hx.service.manage.manage.model.acl.role;

import hx.service.manage.manage.model.CommonRequest;

/**
 * @name: RoleEditRequest
 * @description: 角色修改Request
 * @author: huojiajin
 * @time: 2020/5/28 11:41
 */
public class RoleEditRequest extends CommonRequest {

    private String id;//角色ID
    private String name;// 角色名称
    private String info;// 描述
    private Integer list;// 排序

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public Integer getList() {
        return list;
    }

    public void setList(Integer list) {
        this.list = list;
    }
}
