package hx.service.mobile.back;

import hx.base.core.manage.tools.httpclient.HttpClientHelper;
import hx.service.mobile.model.back.org.BackDirectorListRequest;
import hx.service.mobile.model.back.org.BackGroupListRequest;
import hx.service.mobile.model.back.org.BackSectionListRequest;
import hx.service.mobile.model.common.MobileCommonRequest;
import org.junit.jupiter.api.Test;

import java.io.IOException;

/**
 * @ClassName: TestBack
 * @Description: 后勤相关测试
 * @Author HuoJiaJin
 * @Date 2021/3/14 16:14
 * @Version 1.0
 **/
public class TestBack {

    private static String token = "82bb3c229963450fbbce054bad092fc4";

    @Test
    public void getCampList() throws IOException {
        String url = "http://39.106.226.73:81/mobile/back/camplist";
//        String url = "http://localhost:81/mobile/back/camplist";

        MobileCommonRequest request = new MobileCommonRequest();
        request.setToken(token);

        String responseStr = HttpClientHelper.jsonPost(url, request.toJson());
        System.out.println(responseStr);
    }

    @Test
    public void getDirectorList() throws IOException {
        String url = "http://39.106.226.73:81/mobile/back/directorlist";
//        String url = "http://localhost:81/mobile/back/directorlist";

        BackDirectorListRequest request = new BackDirectorListRequest();
        request.setToken(token);

        String responseStr = HttpClientHelper.jsonPost(url, request.toJson());
        System.out.println(responseStr);
    }

    @Test
    public void getSectionList() throws IOException {
//        String url = "http://39.106.226.73:81/mobile/back/sectionlist";
        String url = "http://localhost:81/mobile/back/sectionlist";

        BackSectionListRequest request = new BackSectionListRequest();
        request.setToken(token);
        request.setDirectorCode("8611020106");

        String responseStr = HttpClientHelper.jsonPost(url, request.toJson());
        System.out.println(responseStr);
    }

    @Test
    public void getGroupList() throws IOException {
//        String url = "http://39.106.226.73:81/mobile/back/grouplist";
        String url = "http://localhost:81/mobile/back/grouplist";

        BackGroupListRequest request = new BackGroupListRequest();
        request.setToken(token);
        request.setSectionCode("861102010601");

        String responseStr = HttpClientHelper.jsonPost(url, request.toJson());
        System.out.println(responseStr);
    }
}
