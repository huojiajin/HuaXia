package hx.service.mobile.manage.model.test.paper;

import hx.service.mobile.manage.model.common.MobileCommonRequest;

/**
 * @ClassName PaperDetailRequest
 * @Description 试卷详情Request
 * @Author HuoJiaJin
 * @Date 2020/6/28 20:38
 * @Version 1.0
 **/
public class PaperDetailRequest extends MobileCommonRequest {

    private String paperId;//试卷id

    public String getPaperId() {
        return paperId;
    }

    public void setPaperId(String paperId) {
        this.paperId = paperId;
    }
}
