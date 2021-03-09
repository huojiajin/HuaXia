package hx.service.manage.acl;

import hx.base.core.manage.model.CommonResponse;
import hx.base.core.manage.tools.JsonTools;
import hx.base.core.manage.tools.httpclient.HttpClientHelper;
import hx.service.manage.ApplicationTests;
import hx.service.manage.model.acl.user.UserAddRequest;
import org.junit.jupiter.api.Test;

import java.io.IOException;

/**
 * @name: UserTest
 * @description: TODO
 * @author: huojiajin
 * @time: 2020/6/17 10:54
 */
public class UserTest extends ApplicationTests {

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
//        String url = "http://39.106.226.73/manage/user/add";

        String responseStr = HttpClientHelper.jsonPost(url, request.toJson());
        echo(responseStr);
        CommonResponse response = JsonTools.json2Object(responseStr, CommonResponse.class);
        echo(response);
    }
}
