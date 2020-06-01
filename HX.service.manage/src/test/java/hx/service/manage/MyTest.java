package hx.service.manage;

import hx.service.manage.dao.repo.request.common.Pagination;
import hx.service.manage.manage.model.CommonPageRequest;
import hx.service.manage.manage.model.CommonResponse;
import hx.service.manage.manage.model.login.LoginRequest;
import hx.service.manage.manage.model.login.LoginResponse;
import hx.service.manage.manage.model.login.VerifyResponse;
import hx.service.manage.manage.tools.JsonTools;
import hx.service.manage.manage.tools.httpclient.HttpClientHelper;
import org.junit.jupiter.api.Test;
import org.springframework.util.Base64Utils;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * @name: MyTest
 * @description: TODO
 * @author: huojiajin
 * @time: 2020/5/28 17:12
 */
public class MyTest extends ApplicationTests{

    @Test
    void verify() throws URISyntaxException, IOException {
        String url = "http://localhost/manage/login/verify";
        String responseStr = HttpClientHelper.httpGet(new URI(url), "UTF-8");
        CommonResponse response = JsonTools.json2Object(responseStr, CommonResponse.class);
        VerifyResponse verifyResponse = JsonTools.json2Object(response.getData(), VerifyResponse.class);

        byte[] b = Base64Utils.decodeFromString(verifyResponse.getVerifyImage());
        for (int i = 0; i < b.length; ++i) {
            if (b[i] < 0) {// 调整异常数据
                b[i] += 256;
            }
        }

        OutputStream out = new FileOutputStream("C:\\Users\\霍佳进\\Desktop\\out.png");
        out.write(b);
        out.flush();
        out.close();
        System.out.println(verifyResponse.getVerifyId());
    }

    @Test
    public void login() throws IOException {
        LoginRequest request = new LoginRequest();
        request.setLoginName("000000001");
        request.setPassword("123456");
        request.setVerifyId("6d09557dc9dc43e1bd1a24948ee23dec");
        request.setVerifyCode("e5dc");
        String url = "http://localhost/manage/login/login";

        String responseStr = HttpClientHelper.jsonPost(url, request.toJson());
        echo(responseStr);
        CommonResponse response = JsonTools.json2Object(responseStr, CommonResponse.class);
        LoginResponse loginResponse = JsonTools.json2Object(response.getData(), LoginResponse.class);
        echo(loginResponse);
    }

    @Test
    public void roleQuery() throws IOException {

        CommonPageRequest request = new CommonPageRequest();
        request.setPageNo(1);
        request.setPageSize(16);
        request.setToken("0ed83461cc9d4803a5a3187755e8c24a");
        request.setResourceCode(1);
        String url = "http://localhost/manage/role/query";

        String responseStr = HttpClientHelper.jsonPost(url, request.toJson());
        echo(responseStr);
        CommonResponse response = JsonTools.json2Object(responseStr, CommonResponse.class);
        Pagination page = JsonTools.json2Object(response.getData(), Pagination.class);
        echo(page);
    }
}
