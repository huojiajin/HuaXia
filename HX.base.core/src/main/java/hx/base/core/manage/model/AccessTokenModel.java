package hx.base.core.manage.model;

import hx.base.core.dao.entity.common.BaseEntity;

/**
 * @ClassName AccessTokenModel
 * @Description 获取accessToken接口返回Model
 * @Author HuoJiaJin
 * @Date 2020/6/26 14:36
 * @Version 1.0
 **/
public class AccessTokenModel extends BaseEntity {

    private String access_token;//生成的access_token
    private long expires_in;//access_token的有效时长

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public long getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(long expires_in) {
        this.expires_in = expires_in;
    }
}
