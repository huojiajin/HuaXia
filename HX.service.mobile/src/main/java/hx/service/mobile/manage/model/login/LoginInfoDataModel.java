package hx.service.mobile.manage.model.login;

import hx.service.manage.dao.entity.common.BaseEntity;

/**
 * @ClassName LoginInfoDataModel
 * @Description 登录信息-dataModel
 * @Author HuoJiaJin
 * @Date 2020/6/23 22:13
 * @Version 1.0
 **/
public class LoginInfoDataModel extends BaseEntity {

    private String redirect_url;//登录成功后要重定向的业务链接地址，商户提供
    private String user_type = "1";//登陆类型。1代表业务员登陆，2代表用户登录，其他登陆类型待开放
    private String first_regist;//是否首次注册，Y-是，N-否（为空则默认为N）
    private String attach;

    public String getRedirect_url() {
        return redirect_url;
    }

    public void setRedirect_url(String redirect_url) {
        this.redirect_url = redirect_url;
    }

    public String getUser_type() {
        return user_type;
    }

    public void setUser_type(String user_type) {
        this.user_type = user_type;
    }

    public String getFirst_regist() {
        return first_regist;
    }

    public void setFirst_regist(String first_regist) {
        this.first_regist = first_regist;
    }

    public String getAttach() {
        return attach;
    }

    public void setAttach(String attach) {
        this.attach = attach;
    }
}
