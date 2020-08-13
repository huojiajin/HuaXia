package hx.service.mobile;

import hx.base.core.manage.tools.httpclient.HttpClientHelper;
import hx.service.mobile.manage.model.common.MobileCommonRequest;
import hx.service.mobile.manage.model.index.GroupListReqeust;
import hx.service.mobile.manage.model.radar.RadarRequest;
import org.junit.jupiter.api.Test;

import java.io.IOException;

/**
 * @ClassName IndexTest
 * @Description 首页Test
 * @Author HuoJiaJin
 * @Date 2020/6/27 19:23
 * @Version 1.0
 **/
public class IndexTest extends ApplicationTests{

    private static final String token = "82bb3c229963450fbbce054bad092fc4";

    @Test
    public void getSectionList() throws IOException {
//        String url = "http://123.56.154.176:81/mobile/index/sectionlist";
        String url = "http://localhost:81/mobile/index/sectionlist";
        MobileCommonRequest request = new MobileCommonRequest();
        request.setToken(token);
        String responseStr = HttpClientHelper.jsonPost(url, request.toJson());
        echo(responseStr);
    }

    @Test
    public void getGroupList() throws IOException {
//        String url = "http://123.56.154.176:81/mobile/index/grouplist";
        String url = "http://localhost:81/mobile/index/grouplist";
        GroupListReqeust request = new GroupListReqeust();
        request.setToken(token);
        request.setSectionCode("861102010602");
        String responseStr = HttpClientHelper.jsonPost(url, request.toJson());
        echo(responseStr);
    }

    @Test
    public void radar() throws IOException {
//        String url = "http://123.56.154.176:81/mobile/index/radar";
        String url = "http://localhost:81/mobile/index/radar";
        RadarRequest request = new RadarRequest();
        request.setToken(token);
        request.setSectionCode("861102010601");
        request.setGroupCode("861102010601044");
        String responseStr = HttpClientHelper.jsonPost(url, request.toJson());
        echo(responseStr);
    }

    @Test
    public void staronself() throws IOException {
//        String url = "http://123.56.154.176:81/mobile/index/radar/oneself";
        String url = "http://localhost:81/mobile/index/radar/oneself";
        MobileCommonRequest request = new MobileCommonRequest();
        request.setToken(token);
        String responseStr = HttpClientHelper.jsonPost(url, request.toJson());
        echo(responseStr);
    }

}
