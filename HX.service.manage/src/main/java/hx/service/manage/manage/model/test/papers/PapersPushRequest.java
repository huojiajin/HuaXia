package hx.service.manage.manage.model.test.papers;

import hx.service.manage.manage.model.CommonRequest;

import java.util.List;

/**
 * @ClassName PapersPushRequest
 * @Description 试卷推送Request
 * @Author HuoJiaJin
 * @Date 2020/6/21 22:22
 * @Version 1.0
 **/
public class PapersPushRequest extends CommonRequest {

    private String paperId;
    private List<String> rankCodeList;

    public String getPaperId() {
        return paperId;
    }

    public void setPaperId(String paperId) {
        this.paperId = paperId;
    }

    public List<String> getRankCodeList() {
        return rankCodeList;
    }

    public void setRankCodeList(List<String> rankCodeList) {
        this.rankCodeList = rankCodeList;
    }
}
