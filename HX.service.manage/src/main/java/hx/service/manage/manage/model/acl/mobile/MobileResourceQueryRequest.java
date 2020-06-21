package hx.service.manage.manage.model.acl.mobile;

import hx.service.manage.manage.model.CommonRequest;

/**
 * @ClassName MobileResourceQueryRequest
 * @Description 查询资源菜单Request
 * @Author HuoJiaJin
 * @Date 2020/6/20 14:48
 * @Version 1.0
 **/
public class MobileResourceQueryRequest extends CommonRequest {

    private String rankCode;//职级代码

    public String getRankCode() {
        return rankCode;
    }

    public void setRankCode(String rankCode) {
        this.rankCode = rankCode;
    }
}
