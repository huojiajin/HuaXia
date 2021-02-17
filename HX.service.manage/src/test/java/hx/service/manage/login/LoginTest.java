package hx.service.manage.login;

import hx.base.core.dao.repo.request.common.Pagination;
import hx.base.core.manage.model.CommonResponse;
import hx.base.core.manage.tools.JsonTools;
import hx.base.core.manage.tools.httpclient.HttpClientHelper;
import hx.service.manage.ApplicationTests;
import hx.service.manage.model.common.CommonPageRequest;
import hx.service.manage.model.login.LoginRequest;
import hx.service.manage.model.login.LoginResponse;
import hx.service.manage.model.login.VerifyResponse;
import org.junit.jupiter.api.Test;
import org.springframework.util.Base64Utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * @name: MyTest
 * @description: 登陆Test
 * @author: huojiajin
 * @time: 2020/5/28 17:12
 */
public class LoginTest extends ApplicationTests {

    @Test
    void verify() throws URISyntaxException, IOException {
        String url = "http://localhost/manage/login/verify";
//        String url = "http://123.56.154.176/manage/login/verify";

        String responseStr = HttpClientHelper.httpGet(new URI(url), "UTF-8");
        CommonResponse<VerifyResponse> response = JsonTools.json2Object(responseStr, CommonResponse.class, VerifyResponse.class);
        VerifyResponse verifyResponse = response.getData();

        byte[] b = Base64Utils.decodeFromString(verifyResponse.getVerifyImage());
        for (int i = 0; i < b.length; ++i) {
            if (b[i] < 0) {// 调整异常数据
                b[i] += 256;
            }
        }
        String fileName = "C:\\Users\\huojiajin\\Desktop\\out.png";
        File file = new File(fileName);
        OutputStream out = new FileOutputStream(file);
        out.write(b);
        out.flush();
        out.close();
        System.out.println(verifyResponse.getVerifyId());
    }

    @Test
    public void login() throws IOException {
        LoginRequest request = new LoginRequest();
        request.setLoginName("000000001");
        request.setPassword("1234567");
        request.setVerifyId("fce8ac84f245468087bcbf97da89efed");
        request.setVerifyCode("ktdm");
//        String url = "http://123.56.154.176/manage/login/login";
        String url = "http://localhost/manage/login/login";

        String responseStr = HttpClientHelper.jsonPost(url, request.toJson());
        echo(responseStr);
        CommonResponse<LoginResponse> response = JsonTools.json2Object(responseStr, CommonResponse.class, LoginResponse.class);
        LoginResponse loginResponse = response.getData();
        echo(loginResponse);
    }



    @Test
    public void userQuery() throws IOException {

        CommonPageRequest request = new CommonPageRequest();
        request.setPageNo(1);
        request.setPageSize(16);
        request.setToken("0106130d1d804137b4e038054cc81a91");
        request.setResourceCode(12);
        String url = "http://localhost/manage/user/query";
//        String url = "http://123.56.154.176/manage/user/query";

        String responseStr = HttpClientHelper.jsonPost(url, request.toJson());
        echo(responseStr);
        CommonResponse<Pagination> response = JsonTools.json2Object(responseStr, CommonResponse.class, Pagination.class);
        Pagination page = response.getData();
        echo(page);
    }
}
