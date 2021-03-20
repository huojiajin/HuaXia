package hx.service.mobile.outwork;

import hx.base.core.manage.tools.httpclient.HttpClientHelper;
import hx.service.mobile.ApplicationTests;
import hx.service.mobile.model.common.MobileCommonRequest;
import org.junit.jupiter.api.Test;

import java.io.IOException;

/**
 * @name: TestOutwork
 * @description: 测试离职申请相关
 * @author: huojiajin
 * @time: 2021/3/16 15:17
 */
public class TestOutworkApply extends ApplicationTests {

    @Test
    public void query() throws IOException {
//        String url = "http://39.106.226.73:81/mobile/outwork/apply/query";
        String url = "http://localhost:81/mobile/outwork/apply/query";
        MobileCommonRequest request = new MobileCommonRequest();
        request.setToken("82bb3c229963450fbbce054bad092fc4");
        String responseStr = HttpClientHelper.jsonPost(url, request.toJson());
        echo(responseStr);
    }

    @Test
    public void apply(){
        return;
    }
}
