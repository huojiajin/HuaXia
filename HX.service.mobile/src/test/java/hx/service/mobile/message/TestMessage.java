package hx.service.mobile.message;

import hx.base.core.manage.tools.httpclient.HttpClientHelper;
import hx.service.mobile.model.common.MobileCommonPageRequest;
import org.junit.jupiter.api.Test;

import java.io.IOException;

/**
 * @ClassName: TestMessage
 * @Description: 消息通知测试
 * @Author HuoJiaJin
 * @Date 2021/3/2 22:09
 * @Version 1.0
 **/
public class TestMessage {

    private static String token = "82bb3c229963450fbbce054bad092fc4";

    @Test
    public void queryHonor() throws IOException {
        String url = "http://localhost:81/mobile/message/query";

        MobileCommonPageRequest request = new MobileCommonPageRequest();
        request.setToken(token);

        String responseStr = HttpClientHelper.jsonPost(url, request.toJson());
        System.out.println(responseStr);
    }
}
