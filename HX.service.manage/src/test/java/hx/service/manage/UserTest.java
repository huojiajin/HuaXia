package hx.service.manage;

import hx.service.manage.dao.repo.request.common.Pagination;
import hx.service.manage.manage.model.CommonPageRequest;
import hx.service.manage.manage.model.CommonResponse;
import hx.service.manage.manage.model.acl.user.UserAddRequest;
import hx.service.manage.manage.tools.JsonTools;
import hx.service.manage.manage.tools.httpclient.HttpClientHelper;
import org.junit.jupiter.api.Test;

import java.io.IOException;

/**
 * @name: UserTest
 * @description: TODO
 * @author: huojiajin
 * @time: 2020/6/17 10:54
 */
public class UserTest extends ApplicationTests{

    @Test
    public void userAdd() throws IOException {

        UserAddRequest request = new UserAddRequest();
        request.setToken("1a8db6051b2846fd918ae851ffb93d3e");
        request.setResourceCode(11);
        request.setRoleId("123456");
        request.setName("霍佳进");
        request.setEmployeeNum("000000002");
        request.setMobile("18513086652");
        String url = "http://localhost/manage/user/add";
//        String url = "http://123.56.154.176/manage/user/add";

        String responseStr = HttpClientHelper.jsonPost(url, request.toJson());
        echo(responseStr);
        CommonResponse response = JsonTools.json2Object(responseStr, CommonResponse.class);
        echo(response);
    }
}
