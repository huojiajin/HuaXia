package hx.service.manage.model.test.papers;

import hx.service.manage.model.common.CommonRequest;

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
    private int pushType;//推送类型
    private List<String> codeList;//代码集合

    public String getPaperId() {
        return paperId;
    }

    public void setPaperId(String paperId) {
        this.paperId = paperId;
    }

    public int getPushType() {
        return pushType;
    }

    public void setPushType(int pushType) {
        this.pushType = pushType;
    }

    public List<String> getCodeList() {
        return codeList;
    }

    public void setCodeList(List<String> codeList) {
        this.codeList = codeList;
    }
}
