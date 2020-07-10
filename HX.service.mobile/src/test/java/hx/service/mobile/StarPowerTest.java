package hx.service.mobile;

import hx.base.core.manage.tools.httpclient.HttpClientHelper;
import hx.service.mobile.manage.model.radar.star.StarPowerDetailRequest;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URI;

/**
 * @name: StarPowerTest
 * @description: 星级人力Test
 * @author: huojiajin
 * @time: 2020/7/1 15:04
 */
public class StarPowerTest extends ApplicationTests{

    @Test
    public void test() throws IOException {
        StarPowerDetailRequest request = new StarPowerDetailRequest();
        request.setGroupCode("861102010601044");
        request.setSectionCode("861102010601");
        request.setType(0);
        request.setToken("67bc6c6aa5b44ddd85e0eef30aec26e6");

//        String url = "http://localhost:81/mobile/index/radar/star/detail";
        String url = "http://123.56.154.176:81/mobile/index/radar/star/detail";
        String responseStr = HttpClientHelper.jsonPost(url, request.toJson());
        echo(responseStr);
    }
}