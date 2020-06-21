package hx.service.manage.manage.model.test.papers;

import hx.service.manage.manage.model.CommonRequest;

/**
 * @ClassName PapersDeleteRequest
 * @Description 试卷删除Request
 * @Author HuoJiaJin
 * @Date 2020/6/21 14:35
 * @Version 1.0
 **/
public class PapersIdRequest extends CommonRequest {

    private String id;//试卷ID

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
