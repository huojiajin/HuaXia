package hx.service.manage.model.test.papers;

import hx.service.manage.model.common.CommonRequest;

/**
 * @ClassName PapersImportReqeust
 * @Description 试卷导入Request
 * @Author HuoJiaJin
 * @Date 2020/6/21 20:37
 * @Version 1.0
 **/
public class PapersImportReqeust extends CommonRequest {

    private String paperId;//试卷ID
    private String paperFile;//试卷文件Base64

    public String getPaperId() {
        return paperId;
    }

    public void setPaperId(String paperId) {
        this.paperId = paperId;
    }

    public String getPaperFile() {
        return paperFile;
    }

    public void setPaperFile(String paperFile) {
        this.paperFile = paperFile;
    }
}
