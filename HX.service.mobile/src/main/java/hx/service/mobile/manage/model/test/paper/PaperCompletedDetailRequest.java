package hx.service.mobile.manage.model.test.paper;

import hx.service.mobile.manage.model.common.MobileCommonRequest;

/**
 * @name: PaperCompletedDetailRequest
 * @description: 获取已答试卷详情Request
 * @author: huojiajin
 * @time: 2020/6/30 14:43
 */
public class PaperCompletedDetailRequest extends MobileCommonRequest {

    private String paperId;//试卷ID

    public String getPaperId() {
        return paperId;
    }

    public void setPaperId(String paperId) {
        this.paperId = paperId;
    }
}
