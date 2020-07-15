package hx.service.manage;

import hx.base.core.dao.repo.request.common.Pagination;
import hx.base.core.manage.model.CommonResponse;
import hx.base.core.manage.tools.JsonTools;
import hx.base.core.manage.tools.httpclient.HttpClientHelper;
import hx.service.manage.manage.model.CommonPageRequest;
import hx.service.manage.manage.model.acl.role.RoleAddRequest;
import hx.service.manage.manage.model.acl.role.RoleResourceRequest;
import hx.service.manage.manage.model.test.integral.IntegralQueryRequest;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;

import java.io.IOException;

/**
 * @name: IntegralManagerTest
 * @description: 积分管理相关测试
 * @author: huojiajin
 * @time: 2020/6/17 10:29
 */
public class IntegralManagerTest extends ApplicationTests{

    @Test
    public void roleQuery() throws IOException {
        IntegralQueryRequest request = new IntegralQueryRequest();
        request.setPageNo(2);
        request.setPageSize(20);
        request.setToken("e60ece9e96a5464cb890f0b8323a03f1");
        request.setResourceCode(12);
        String url = "http://localhost/manage/test/integral/query";
//        String url = "http://123.56.154.176/manage/test/integral/query";

        String responseStr = HttpClientHelper.jsonPost(url, request.toJson());
        echo(responseStr);
        CommonResponse<Pagination> response = JsonTools.json2Object(responseStr, CommonResponse.class, Pagination.class);
        Pagination page = response.getData();
        echo(page);
    }
}
