package hx.service.mobile.manage.model;

import hx.service.manage.dao.entity.common.BaseEntity;

/**
 * @ClassName LoginVerifyResponse
 * @Description 登录所需信息
 * @Author HuoJiaJin
 * @Date 2020/6/23 22:04
 * @Version 1.0
 **/
public class LoginInfoResponse extends BaseEntity {

    private String app_id;//appId
    private String nonce;//十位随机数
    private String timestamp;//时间戳
    private String sign_type = "md5";//加密方式，默认md5
    private String sign;//签名戳，生成规则：app_id+data+nonce+secret_key+sign_type+time_stamp拼接的字符串进行md5加密



    public String getApp_id() {
        return app_id;
    }

    public void setApp_id(String app_id) {
        this.app_id = app_id;
    }

    public String getNonce() {
        return nonce;
    }

    public void setNonce(String nonce) {
        this.nonce = nonce;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getSign_type() {
        return sign_type;
    }

    public void setSign_type(String sign_type) {
        this.sign_type = sign_type;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }
}
