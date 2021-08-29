package hx.service.mobile.honor;

import hx.base.core.manage.tools.httpclient.HttpClientHelper;
import hx.service.mobile.model.common.MobileCommonRequest;
import hx.service.mobile.model.honor.WallQueryRequest;
import org.junit.jupiter.api.Test;

import java.io.IOException;

/**
 * @ClassName: TestHonor
 * @Description: TODO
 * @Author HuoJiaJin
 * @Date 2021/3/2 22:09
 * @Version 1.0
 **/
public class TestHonor {

    private static String token = "82bb3c229963450fbbce054bad092fc4";

    @Test
    public void entryTime() throws IOException {
        String url = "http://localhost:81/mobile/honor/entrytime";

        MobileCommonRequest request = new MobileCommonRequest();
        request.setToken(token);

        String responseStr = HttpClientHelper.jsonPost(url, request.toJson());
        System.out.println(responseStr);
    }

    @Test
    public void track() throws IOException {
        String url = "http://localhost:81/mobile/honor/track";

        MobileCommonRequest request = new MobileCommonRequest();
        request.setToken(token);

        String responseStr = HttpClientHelper.jsonPost(url, request.toJson());
        System.out.println(responseStr);
    }

    @Test
    public void queryHonor() throws IOException {
        String url = "http://localhost:81/mobile/honor/wall/query";

        WallQueryRequest request = new WallQueryRequest();
        request.setToken("7ea849e7f26348498a6bc6d8cd383b09");
        request.setYear(2021);

        String responseStr = HttpClientHelper.jsonPost(url, request.toJson());
        System.out.println(responseStr);
    }

    @Test
    public void track() throws IOException {
        String url = "http://localhost:81/mobile/honor/track";

        MobileCommonRequest request = new MobileCommonRequest();
        request.setToken("7ea849e7f26348498a6bc6d8cd383b09");

        String responseStr = HttpClientHelper.jsonPost(url, request.toJson());
        System.out.println(responseStr);
    }

}
