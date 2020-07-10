package hx.service.mobile;

import hx.base.core.manage.tools.httpclient.HttpClientHelper;
import org.apache.commons.compress.utils.Lists;
import org.apache.http.NameValuePair;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

/**
 * @ClassName LoginTest
 * @Description 登陆相关测试
 * @Author HuoJiaJin
 * @Date 2020/6/27 13:55
 * @Version 1.0
 **/
public class LoginTest extends ApplicationTests{

    @Test
    public void loginInfo() throws IOException {
//        String url = "http://123.56.154.176:81/mobile/login/verify";
        String url = "http://localhost:81/mobile/login/verify";
        List<NameValuePair> params = Lists.newArrayList();
        String responseStr = HttpClientHelper.httpPost(url, "UTF-8",  params, "UTF-8");
        echo(responseStr);
    }
}
