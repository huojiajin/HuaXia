package hx.service.manage.manage.model.message;

import hx.service.manage.manage.model.CommonPageRequest;

/**
 * @ClassName: MessageQueryRequest
 * @Description: 自定义消息查询Request
 * @Author HuoJiaJin
 * @Date 2021/1/31 23:07
 * @Version 1.0
 **/
public class MessageQueryRequest extends CommonPageRequest {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
