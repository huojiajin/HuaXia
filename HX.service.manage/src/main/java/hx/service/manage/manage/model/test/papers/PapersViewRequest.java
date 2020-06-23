package hx.service.manage.manage.model.test.papers;

import hx.service.manage.manage.model.CommonPageRequest;

/**
 * @ClassName PapersViewRequest
 * @Description 试卷查看Request
 * @Author HuoJiaJin
 * @Date 2020/6/22 22:09
 * @Version 1.0
 **/
public class PapersViewRequest extends CommonPageRequest {

    private String id;//试卷ID

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
