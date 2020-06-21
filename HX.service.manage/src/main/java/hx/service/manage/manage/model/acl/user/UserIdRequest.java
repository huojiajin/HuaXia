package hx.service.manage.manage.model.acl.user;

import hx.service.manage.manage.model.CommonRequest;

/**
 * @name: UserDeleteRequest
 * @description: 用户禁用Request
 * @author: huojiajin
 * @time: 2020/5/28 15:21
 */
public class UserIdRequest extends CommonRequest {

    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
