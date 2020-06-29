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

    @Test
    public void getSectionList() throws IOException {
        String url = "http://123.56.154.176:81/mobile/index/sectionlist";
//        String url = "http://localhost:81/mobile/index/sectionlist";
        MobileCommonRequest request = new MobileCommonRequest();
        request.setToken("f9dd36c1380b438dbcd97d50bef29564");
        String responseStr = HttpClientHelper.jsonPost(url, request.toJson());
        echo(responseStr);
    }

    @Test
    public void getGroupList() throws IOException {
        String url = "http://123.56.154.176:81/mobile/index/grouplist";
//        String url = "http://localhost:81/mobile/index/grouplist";
        GroupListReqeust request = new GroupListReqeust();
        request.setToken("f9dd36c1380b438dbcd97d50bef29564");
        request.setSectionCode("861102010601");
        String responseStr = HttpClientHelper.jsonPost(url, request.toJson());
        echo(responseStr);
    }

    @Test
    public void radar() throws IOException {
//        String url = "http://123.56.154.176:81/mobile/index/radar";
        String url = "http://localhost:81/mobile/index/radar";
        RadarRequest request = new RadarRequest();
        request.setToken("501ba6dd06a44b4fa1370cead95e99a8");
        request.setSectionCode("861102010601");
        request.setGroupCode("0");
        String responseStr = HttpClientHelper.jsonPost(url, request.toJson());
        echo(responseStr);
    }
}
