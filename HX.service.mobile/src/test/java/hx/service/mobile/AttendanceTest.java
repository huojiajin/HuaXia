package hx.service.mobile;

import hx.base.core.manage.tools.httpclient.HttpClientHelper;
import hx.service.mobile.manage.model.radar.attend.PersonAttendRequest;
import hx.service.mobile.manage.model.radar.stadprem.PersonStadpremRequest;
import org.junit.jupiter.api.Test;

import java.io.IOException;

/**
 * @name: AttendanceTest
 * @description: TODO
 * @author: huojiajin
 * @time: 2020/8/13 10:21
 */
public class AttendanceTest extends ApplicationTests{

    @Test
    public void testPerson() throws IOException {
//        String url = "http://123.56.154.176:81/mobile/index/radar/stadprem/section";
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
