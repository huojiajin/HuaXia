package hx.service.mobile.team;

import hx.base.core.manage.tools.httpclient.HttpClientHelper;
import hx.service.mobile.model.common.MobileCommonRequest;
import org.junit.jupiter.api.Test;

import java.io.IOException;

/**
 * @ClassName: TestTeamInfo
 * @Description: 团队信息Test
 * @Author HuoJiaJin
 * @Date 2021/3/2 21:14
 * @Version 1.0
 **/
public class TestTeamInfo {

    private static String token = "";

    @Test
    public void getSectionList() throws IOException {
        String url = "http://localhost:81/mobile/teaminfo/sectionlist";

        MobileCommonRequest request = new MobileCommonRequest();
        request.setToken(token);

        String responseStr = HttpClientHelper.jsonPost(url, request.toJson());
        System.out.println(responseStr);
    }
}
