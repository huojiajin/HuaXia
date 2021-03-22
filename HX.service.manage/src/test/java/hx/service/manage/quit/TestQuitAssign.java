package hx.service.manage.quit;

import hx.base.core.manage.tools.httpclient.HttpClientHelper;
import hx.service.manage.ApplicationTests;
import hx.service.manage.model.common.CommonPageRequest;
import org.junit.jupiter.api.Test;

import java.io.IOException;

/**
 * @ClassName: TestSalaryQuartz
 * @Description: 测试薪资项目定时任务
 * @Author HuoJiaJin
 * @Date 2021/2/22 23:44
 * @Version 1.0
 **/
public class TestQuitAssign extends ApplicationTests {

    @Test
    void query() throws IOException {

        CommonPageRequest request = new CommonPageRequest();
        request.setToken("869fce4c22bd48fe9c32f844849595ab");
        request.setResourceCode(11);
        request.setPageNo(1);
        request.setPageSize(20);
//        String url = "http://39.106.226.73/manage/quit/assign/query";
        String url = "http://localhost/manage/quit/assign/query";
        String s = HttpClientHelper.jsonPost(url, request.toJson());
        echo(s);
    }
}
