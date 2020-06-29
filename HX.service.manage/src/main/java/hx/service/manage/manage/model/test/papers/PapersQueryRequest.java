package hx.service.manage.manage.model.test.papers;

import hx.service.manage.manage.model.CommonPageRequest;

/**
 * @ClassName PaperQueryPageRequest
 * @Description 试卷分页查询Request
 * @Author HuoJiaJin
 * @Date 2020/6/21 14:18
 * @Version 1.0
 **/
public class PapersQueryRequest extends CommonPageRequest {

    private String name;//名称
    private Integer type;//类别

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
