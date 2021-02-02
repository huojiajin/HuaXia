package hx.service.manage.model.black;

import hx.service.manage.model.common.CommonPageRequest;

/**
 * @ClassName: BlackListQueryRequest
 * @Description: 黑名单查询Request
 * @Author HuoJiaJin
 * @Date 2021/2/3 0:46
 * @Version 1.0
 **/
public class BlackListQueryRequest extends CommonPageRequest {

    private int type;//类别

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
