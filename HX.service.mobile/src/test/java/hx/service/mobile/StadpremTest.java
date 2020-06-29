package hx.service.mobile;

import hx.base.core.dao.dict.RateType;
import hx.base.core.manage.tools.httpclient.HttpClientHelper;
import hx.service.mobile.manage.model.common.MobileCommonRequest;
import hx.service.mobile.manage.model.radar.stadprem.GroupStadpremRequest;
import hx.service.mobile.manage.model.radar.stadprem.SectionStadpremRequest;
import org.junit.jupiter.api.Test;

import java.io.IOException;

/**
 * @name: StadpremTest
 * @description: 标保Test
 * @author: huojiajin
 * @time: 2020/6/29 14:25
 */
public class StadpremTest extends ApplicationTests{

    @Test
    public void testSection() throws IOException {
//        String url = "http://123.56.154.176:81/mobile/index/radar/stadprem/section";
        String url = "http://localhost:81/mobile/index/radar/stadprem/section";
        SectionStadpremRequest request = new SectionStadpremRequest();
        request.setSectionCode("861102010601");
        request.setGroupCode("0");
        request.setToken("f9dd36c1380b438dbcd97d50bef29564");
        String responseStr = HttpClientHelper.jsonPost(url, request.toJson());
        echo(responseStr);
    }

    @Test
    public void testGroup() throws IOException {
//        String url = "http://123.56.154.176:81/mobile/index/radar/stadprem/section";
        String url = "http://localhost:81/mobile/index/radar/stadprem/group";
        GroupStadpremRequest request = new GroupStadpremRequest();
        request.setSectionCode("861102010601");
        request.setMonth(4);
        request.setToken("f9dd36c1380b438dbcd97d50bef29564");
        String responseStr = HttpClientHelper.jsonPost(url, request.toJson());
        echo(responseStr);
    }
}
