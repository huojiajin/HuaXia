package hx.service.mobile.radar;

import hx.base.core.manage.tools.httpclient.HttpClientHelper;
import hx.service.mobile.ApplicationTests;
import hx.service.mobile.model.radar.attend.PersonAttendRequest;
import org.junit.jupiter.api.Test;

import java.io.IOException;

/**
 * @name: AttendanceTest
 * @description: TODO
 * @author: huojiajin
 * @time: 2020/8/13 10:21
 */
public class AttendanceTest extends ApplicationTests {

    @Test
    public void testPerson() throws IOException {
//        String url = "http://39.106.226.73:81/mobile/index/radar/stadprem/section";
        String url = "http://localhost:81/mobile/index/radar/attend/person";
        PersonAttendRequest request = new PersonAttendRequest();
        request.setGroupCode("861102010601001");
        request.setMonth(7);
        request.setPageNo(6);
        request.setPageSize(15);
        request.setToken("82bb3c229963450fbbce054bad092fc4");
        String responseStr = HttpClientHelper.jsonPost(url, request.toJson());
        echo(responseStr);
    }
}
