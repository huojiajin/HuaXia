package hx.service.mobile.test;

import hx.base.core.manage.model.CommonResponse;
import hx.base.core.manage.tools.JsonTools;
import hx.base.core.manage.tools.httpclient.HttpClientHelper;
import hx.service.mobile.ApplicationTests;
import hx.service.mobile.model.test.course.CourseDetailRequest;
import hx.service.mobile.model.test.course.CourseDetailResponse;
import hx.service.mobile.model.test.course.CourseListRequest;
import org.junit.jupiter.api.Test;
import org.springframework.util.Base64Utils;

import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @ClassName IndexTest
 * @Description 首页Test
 * @Author HuoJiaJin
 * @Date 2020/6/27 19:23
 * @Version 1.0
 **/
public class CourseTest extends ApplicationTests {

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

    @Test
    public void getDetail() throws IOException {
//        String url = "http://123.56.154.176:81/mobile/test/course/detail";
        String url = "http://localhost:81/mobile/test/course/detail";
        CourseDetailRequest request = new CourseDetailRequest();
        request.setToken(token);
        request.setCourseId("0c92b2a423134d9ca1a8764ba5ad210d");
        String responseStr = HttpClientHelper.jsonPost(url, request.toJson());
        CommonResponse<CourseDetailResponse> response = JsonTools.json2Object(responseStr, CommonResponse.class, CourseDetailResponse.class);

        byte[] bytes = Base64Utils.decodeFromString(response.getData().getCourseFile());
        String filePath = "C:\\Users\\霍佳进\\Desktop\\" + "测试.pdf";
        FileOutputStream fileInputStream = new FileOutputStream(filePath);
        fileInputStream.write(bytes);
        fileInputStream.close();
    }
}
