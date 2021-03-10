package hx.service.manage.honor;

import hx.base.core.dao.repo.request.common.Pagination;
import hx.base.core.manage.model.CommonResponse;
import hx.base.core.manage.tools.JsonTools;
import hx.base.core.manage.tools.httpclient.HttpClientHelper;
import hx.service.manage.ApplicationTests;
import hx.service.manage.model.honor.HonorQueryRequest;
import org.junit.jupiter.api.Test;

import java.io.IOException;

/**
 * @ClassName: TestHonor
 * @Description: TODO
 * @Author HuoJiaJin
 * @Date 2021/3/9 22:47
 * @Version 1.0
 **/
public class TestHonor extends ApplicationTests {

    private static String token = "82bb3c229963450fbbce054bad092fc4";

    @Test
    void query() throws IOException {
        HonorQueryRequest request = new HonorQueryRequest();
        request.setToken(token);
        request.setResourceCode(11);
        request.setPageNo(1);
        request.setPageSize(20);
//        String url = "http://39.106.226.73/manage/staff/situation/query";
        String url = "http://localhost/manage/staff/situation/query";
        String s = HttpClientHelper.jsonPost(url, request.toJson());
        CommonResponse response = JsonTools.json2Object(s, CommonResponse.class, Pagination.class);
        echo(response);
    }
}
