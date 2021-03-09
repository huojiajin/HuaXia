package hx.service.manage.test;

import hx.base.core.dao.repo.request.common.Pagination;
import hx.base.core.manage.model.CommonResponse;
import hx.base.core.manage.tools.JsonTools;
import hx.base.core.manage.tools.httpclient.HttpClientHelper;
import hx.service.manage.ApplicationTests;
import hx.service.manage.model.test.integral.IntegralQueryRequest;
import org.junit.jupiter.api.Test;

import java.io.IOException;

/**
 * @name: IntegralManagerTest
 * @description: 积分管理相关测试
 * @author: huojiajin
 * @time: 2020/6/17 10:29
 */
public class IntegralManagerTest extends ApplicationTests {

    @Test
    public void roleQuery() throws IOException {
        IntegralQueryRequest request = new IntegralQueryRequest();
        request.setPageNo(2);
        request.setPageSize(20);
        request.setToken("e60ece9e96a5464cb890f0b8323a03f1");
        request.setResourceCode(12);
        String url = "http://localhost/manage/test/integral/query";
//        String url = "http://39.106.226.73/manage/test/integral/query";

        String responseStr = HttpClientHelper.jsonPost(url, request.toJson());
        echo(responseStr);
        CommonResponse<Pagination> response = JsonTools.json2Object(responseStr, CommonResponse.class, Pagination.class);
        Pagination page = response.getData();
        echo(page);
    }
}
