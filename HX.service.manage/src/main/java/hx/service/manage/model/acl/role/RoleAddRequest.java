package hx.service.manage.model.acl.role;

import hx.service.manage.model.common.CommonRequest;

/**
 * @name: RoleAddRequest
 * @description: 角色添加Request
 * @author: huojiajin
 * @time: 2020/5/28 11:41
 */
public class RoleAddRequest extends CommonRequest {

    private String name;// 角色名称
    private String info;// 描述
    private Integer list;// 排序

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
