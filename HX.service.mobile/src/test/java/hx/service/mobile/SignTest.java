package hx.service.mobile;

import hx.base.core.manage.tools.httpclient.HttpClientHelper;
import hx.service.mobile.manage.model.common.MobileCommonRequest;
import hx.service.mobile.manage.model.test.course.CourseListRequest;
import org.junit.jupiter.api.Test;

import java.io.IOException;

/**
 * @ClassName SignTest
 * @Description  签到Test
 * @Author HuoJiaJin
 * @Date 2020/6/27 19:23
 * @Version 1.0
 **/
public class SignTest extends ApplicationTests{

    private static final String token = "882fdfe7018d4f2ba092fabaee438b8a";

    @Test
    public void getList() throws IOException {
        String url = "http://123.56.154.176:81/mobile/test/sign";
//        String url = "http://localhost:81/mobile/test/sign";
        MobileCommonRequest request = new MobileCommonRequest();
        request.setToken(token);
        String responseStr = HttpClientHelper.jsonPost(url, request.toJson());
        echo(responseStr);
    }

}
