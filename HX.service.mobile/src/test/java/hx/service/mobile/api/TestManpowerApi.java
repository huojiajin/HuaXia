package hx.service.mobile.api;

import hx.base.core.manage.tools.httpclient.HttpClientHelper;
import hx.service.mobile.model.back.kpi.ManpowerAPIRequest;
import org.junit.jupiter.api.Test;

import java.io.IOException;

/**
 * @ClassName: TestManpowerApi
 * @Description: 测试人力
 * @Author HuoJiaJin
 * @Date 2021/3/14 16:46
 * @Version 1.0
 **/
public class TestManpowerApi {

    private static String token = "82bb3c229963450fbbce054bad092fc4";

    @Test
    public void query() throws IOException {
        String url = "http://localhost:81/mobile/manpower/query";

        ManpowerAPIRequest request = new ManpowerAPIRequest();
        request.setToken(token);
        request.setCampCode("861102010");

        String responseStr = HttpClientHelper.jsonPost(url, request.toJson());
        System.out.println(responseStr);
    }
}
