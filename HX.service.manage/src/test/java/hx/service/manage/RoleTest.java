package hx.service.manage;

import hx.service.manage.dao.repo.request.common.Pagination;
import hx.service.manage.manage.model.CommonPageRequest;
import hx.service.manage.manage.model.CommonResponse;
import hx.service.manage.manage.model.acl.role.RoleAddRequest;
import hx.service.manage.manage.model.acl.role.RoleResourceRequest;
import hx.service.manage.manage.tools.JsonTools;
import hx.service.manage.manage.tools.httpclient.HttpClientHelper;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;

import java.io.IOException;

/**
 * @name: RoleTest
 * @description: TODO
 * @author: huojiajin
 * @time: 2020/6/17 10:29
 */
public class RoleTest extends ApplicationTests{

    @Test
    public void roleQuery() throws IOException {

        CommonPageRequest request = new CommonPageRequest();
        request.setPageNo(1);
        request.setPageSize(16);
        request.setToken("daae4849b6ed46fabb527f8448aa5b07");
        request.setResourceCode(12);
        String url = "http://localhost/manage/role/query";
//        String url = "http://123.56.154.176/manage/role/query";

        String responseStr = HttpClientHelper.jsonPost(url, request.toJson());
        echo(responseStr);
        CommonResponse<Pagination> response = JsonTools.json2Object(responseStr, CommonResponse.class, Pagination.class);
        Pagination page = response.getData();
        echo(page);
    }

    @Test
    public void roleAdd() throws IOException {
        RoleAddRequest request = new RoleAddRequest();
        request.setName("测试");
        request.setToken("1a8db6051b2846fd918ae851ffb93d3e");
        request.setInfo("测试用");
        request.setList(1);
        request.setResourceCode(12);
//        String url = "http://localhost/manage/role/add";
        String url = "http://123.56.154.176/manage/role/add";
        String responseStr = HttpClientHelper.jsonPost(url, request.toJson());
        echo(responseStr);
        CommonResponse response = JsonTools.json2Object(responseStr, CommonResponse.class);
        echo(response);
    }

    @Test
    public void roleResourceConfig() throws IOException {

        RoleResourceRequest request = new RoleResourceRequest();
        request.setToken("daae4849b6ed46fabb527f8448aa5b07");
        request.setResourceCode(12);
        request.setRoleId("123456");
        request.setResourceCodeList(Lists.newArrayList(1,11,12,13,2,21,22,23));
        String url = "http://localhost/manage/role/resource/config";
//        String url = "http://123.56.154.176/manage/role/resource/config";

        String responseStr = HttpClientHelper.jsonPost(url, request.toJson());
        echo(responseStr);
        CommonResponse response = JsonTools.json2Object(responseStr, CommonResponse.class);
        echo(response);
    }
}
