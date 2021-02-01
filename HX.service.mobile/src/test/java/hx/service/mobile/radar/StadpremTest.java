package hx.service.mobile.radar;

import hx.base.core.manage.tools.httpclient.HttpClientHelper;
import hx.service.mobile.ApplicationTests;
import hx.service.mobile.model.radar.stadprem.GroupStadpremRequest;
import hx.service.mobile.model.radar.stadprem.PersonStadpremDetailRequest;
import hx.service.mobile.model.radar.stadprem.PersonStadpremRequest;
import hx.service.mobile.model.radar.stadprem.SectionStadpremRequest;
import org.junit.jupiter.api.Test;

import java.io.IOException;

/**
 * @name: StadpremTest
 * @description: 标保Test
 * @author: huojiajin
 * @time: 2020/6/29 14:25
 */
public class StadpremTest extends ApplicationTests {

    @Test
    public void testSection() throws IOException {
//        String url = "http://123.56.154.176:81/mobile/index/radar/stadprem/section";
        String url = "http://localhost:81/mobile/index/radar/stadprem/section";
        SectionStadpremRequest request = new SectionStadpremRequest();
        request.setSectionCode("861102010601");
        request.setGroupCode("0");
        request.setToken("82bb3c229963450fbbce054bad092fc4");
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

    @Test
    public void testPerson() throws IOException {
//        String url = "http://123.56.154.176:81/mobile/index/radar/stadprem/section";
        String url = "http://localhost:81/mobile/index/radar/stadprem/person";
        PersonStadpremRequest request = new PersonStadpremRequest();
        request.setGroupCode("861102010601007");
        request.setMonth(7);
        request.setPageNo(1);
        request.setPageSize(15);
        request.setToken("82bb3c229963450fbbce054bad092fc4");
        String responseStr = HttpClientHelper.jsonPost(url, request.toJson());
        echo(responseStr);
    }

    @Test
    public void testPersonDetail() throws IOException {
//        String url = "http://123.56.154.176:81/mobile/index/radar/stadprem/section";
        String url = "http://localhost:81/mobile/index/radar/stadprem/person/detail";
        PersonStadpremDetailRequest request = new PersonStadpremDetailRequest();
        request.setAgentCode("110002207");
        request.setMonth(7);
        request.setPageNo(1);
        request.setPageSize(15);
        request.setToken("82bb3c229963450fbbce054bad092fc4");
        String responseStr = HttpClientHelper.jsonPost(url, request.toJson());
        echo(responseStr);
    }
}
