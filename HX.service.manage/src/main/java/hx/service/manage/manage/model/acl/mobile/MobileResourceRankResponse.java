package hx.service.manage.manage.model.acl.mobile;

import hx.service.manage.manage.model.CommonRequest;

/**
 * @ClassName MobileResourceRankResponse
 * @Description 移动端职级Model
 * @Author HuoJiaJin
 * @Date 2020/6/20 14:42
 * @Version 1.0
 **/
public class MobileResourceRankResponse extends CommonRequest {

    private String rankCode;//职级代码
    private String rankName;//职级名称

    public String getRankCode() {
        return rankCode;
    }

    public void setRankCode(String rankCode) {
        this.rankCode = rankCode;
    }

    public String getRankName() {
        return rankName;
    }

    public void setRankName(String rankName) {
        this.rankName = rankName;
    }
}
