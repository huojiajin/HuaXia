package hx.service.mobile.manage.test;

import hx.service.mobile.manage.model.test.paper.*;

/**
 * @ClassName PaperManager
 * @Description 试卷相关Manager
 * @Author HuoJiaJin
 * @Date 2020/6/27 21:56
 * @Version 1.0
 **/
public interface PaperManager {

    /**
     * @Name list
     * @Author HuoJiaJin
     * @Description 试卷查询
     * @Date 2020/6/27 22:00
     * @Param [request]
     * @return java.lang.String
     **/
    String list(PaperListRequest request);

    String detail(PaperDetailRequest request);

    String submit(PaperSubmitRequest request);

    String getCompletedList(PaperCompletedListRequest request);

    String getCompletedDetail(PaperCompletedDetailRequest request);
}
