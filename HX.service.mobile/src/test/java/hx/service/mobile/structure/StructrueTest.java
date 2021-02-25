package hx.service.mobile.structure;

import hx.base.core.manage.tools.httpclient.HttpClientHelper;
import hx.service.mobile.ApplicationTests;
import hx.service.mobile.model.structure.StructureAnalysisRequest;
import hx.service.mobile.model.structure.StructurePersonListRequest;
import org.junit.jupiter.api.Test;

import java.io.IOException;

/**
 * @name: StructrueTest
 * @description: 组织架构Test
 * @author: huojiajin
 * @time: 2020/7/12 19:35
 */
public class StructrueTest extends ApplicationTests {

    @Test
    public void structure() throws IOException {
        String url = "http://localhost:81/mobile//organization/structure";

        StructureAnalysisRequest request = new StructureAnalysisRequest();
        request.setToken("0b274c829cac4dba8972ce886ad98b6d");
        request.setOrgCode("861102010601001");
        request.setOrgType(0);

        String responseStr = HttpClientHelper.jsonPost(url, request.toJson());
        echo(responseStr);
    }

    @Test
    public void getList() throws IOException {
        String url = "http://localhost:81/mobile//organization/structure/list";

        StructurePersonListRequest request = new StructurePersonListRequest();
        request.setGroupCode("861102010601001");
        request.setToken("0b274c829cac4dba8972ce886ad98b6d");

        String responseStr = HttpClientHelper.jsonPost(url, request.toJson());
        echo(responseStr);
    }
}
