package hx.service.mobile.manage.model.test.paper;

import hx.service.mobile.manage.model.common.MobileCommonRequest;

import java.util.List;

/**
 * @name: PaperSubmitRequest
 * @description: 试卷提交Request
 * @author: huojiajin
 * @time: 2020/6/30 11:20
 */
public class PaperSubmitRequest extends MobileCommonRequest {

    private String paperId;//试卷ID
    private List<PaperSubmitSubjectModel> subjectList;//题目List

    public String getPaperId() {
        return paperId;
    }

    public void setPaperId(String paperId) {
        this.paperId = paperId;
    }

    public List<PaperSubmitSubjectModel> getSubjectList() {
        return subjectList;
    }

    public void setSubjectList(List<PaperSubmitSubjectModel> subjectList) {
        this.subjectList = subjectList;
    }
}
