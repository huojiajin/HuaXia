package hx.service.mobile.model.common;

import hx.base.core.dao.entity.common.BaseEntity;

/**
 * @ClassName MobileCommonRequest
 * @Description 移动端通用Request
 * @Author HuoJiaJin
 * @Date 2020/6/26 23:51
 * @Version 1.0
 **/
public class MobileCommonRequest extends BaseEntity {

    private String token;//登录标识

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
