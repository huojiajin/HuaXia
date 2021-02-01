package hx.service.manage.model.acl.mobile;

import hx.service.manage.model.common.CommonRequest;

import java.util.List;

/**
 * @ClassName MobileResourceConfigRequest
 * @Description 移动端资源配置Request
 * @Author HuoJiaJin
 * @Date 2020/6/20 14:59
 * @Version 1.0
 **/
public class MobileResourceConfigRequest extends CommonRequest {

    private String rankCode;//职级代码
    private List<Integer> resourceCodeList;//资源代码集合

    public String getRankCode() {
        return rankCode;
    }

    public void setRankCode(String rankCode) {
        this.rankCode = rankCode;
    }

    public List<Integer> getResourceCodeList() {
        return resourceCodeList;
    }

    public void setResourceCodeList(List<Integer> resourceCodeList) {
        this.resourceCodeList = resourceCodeList;
    }
}
