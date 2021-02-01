package hx.service.manage.model.common;

import hx.base.core.dao.entity.common.BaseEntity;

/**
 * @name: CommonRequest
 * @description: 公用Request(登陆除外)
 * @author: huojiajin
 * @time: 2020/5/28 11:39
 */
public class CommonRequest extends BaseEntity {

    private String token;//登陆凭证
    private int resourceCode;//菜单code

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getResourceCode() {
        return resourceCode;
    }

    public void setResourceCode(int resourceCode) {
        this.resourceCode = resourceCode;
    }
}
