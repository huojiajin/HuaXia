package hx.service.manage.manage.test;

import hx.service.manage.manage.model.CommonRequest;
import hx.service.manage.manage.model.test.papers.*;

/**
 *@ClassName PaperManager.java
 *@Description 试卷管理Manager
 *@Author HuoJiaJin
 *@Date 2020/6/20 21:47
 *@Version 1.0
 **/
public interface PapersManager {
    String query(PapersQueryRequest request);

    String add(PapersAddRequest request);

    String delete(PapersIdRequest request);

    String downloadTemplate(CommonRequest request);

    String importPapers(PapersImportReqeust request);

    String view(PapersIdRequest request);

    String push(PapersPushRequest request);

    String resultView(PapersIdRequest request);
}
