package hx.service.manage.manage.model.acl.mobile;

import hx.service.manage.manage.model.CommonRequest;

import java.util.List;

/**
 * @ClassName MobileResourceQueryResponse
 * @Description 查询资源菜单Response
 * @Author HuoJiaJin
 * @Date 2020/6/20 14:48
 * @Version 1.0
 **/
public class MobileResourceQueryResponse extends CommonRequest {

    private List<Integer> resourceCodeList;//资源代码集合

    public List<Integer> getResourceCodeList() {
        return resourceCodeList;
    }

    public void setResourceCodeList(List<Integer> resourceCodeList) {
        this.resourceCodeList = resourceCodeList;
    }
}
