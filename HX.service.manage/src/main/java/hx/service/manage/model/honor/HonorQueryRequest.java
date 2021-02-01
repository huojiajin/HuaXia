package hx.service.manage.model.honor;

import hx.service.manage.model.common.CommonPageRequest;

/**
 * @ClassName: HonorQueryRequest
 * @Description: 荣誉分页查询
 * @Author HuoJiaJin
 * @Date 2021/2/1 1:00
 * @Version 1.0
 **/
public class HonorQueryRequest extends CommonPageRequest {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
