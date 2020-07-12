package hx.service.mobile;

import hx.base.core.manage.tools.httpclient.HttpClientHelper;
import hx.service.mobile.manage.model.common.MobileCommonRequest;
import hx.service.mobile.manage.model.index.GroupListReqeust;
import hx.service.mobile.manage.model.radar.RadarRequest;
import hx.service.mobile.manage.model.test.course.CourseListRequest;
import org.junit.jupiter.api.Test;

import java.io.IOException;

/**
 * @ClassName IndexTest
 * @Description 首页Test
 * @Author HuoJiaJin
 * @Date 2020/6/27 19:23
 * @Version 1.0
 **/
public class CourseTest extends ApplicationTests{

    private static final String token = "0b274c829cac4dba8972ce886ad98b6d";

    @Test
    public void getList() throws IOException {
        String url = "http://123.56.154.176:81/mobile/test/course/list";
//        String url = "http://localhost:81/mobile/test/course/list";
        CourseListRequest request = new CourseListRequest();
        request.setToken(token);
        request.setType(2);
        String responseStr = HttpClientHelper.jsonPost(url, request.toJson());
        echo(responseStr);
    }

}
