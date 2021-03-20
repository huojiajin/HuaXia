package hx.service.mobile.back.team;

import hx.base.core.manage.tools.httpclient.HttpClientHelper;
import hx.service.mobile.model.common.MobileCommonRequest;
import hx.service.mobile.model.person.team.TeamInfoQueryRequest;
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

    private static String token = "82bb3c229963450fbbce054bad092fc4";

    @Test
    public void getSectionList() throws IOException {
        String url = "http://localhost:81/mobile/teaminfo/sectionlist";

        MobileCommonRequest request = new MobileCommonRequest();
        request.setToken(token);

        String responseStr = HttpClientHelper.jsonPost(url, request.toJson());
        System.out.println(responseStr);
    }

    @Test
    public void query() throws IOException {
        String url = "http://localhost:81/mobile/teaminfo/query";

        TeamInfoQueryRequest request = new TeamInfoQueryRequest();
        request.setToken(token);
        request.setEndDate("2021-03");
//        request.setGroupCode("861102010602004");
        request.setSectionCode("861102010609");
        request.setStartDate("2021-03");

        String responseStr = HttpClientHelper.jsonPost(url, request.toJson());
        System.out.println(responseStr);
    }
}
