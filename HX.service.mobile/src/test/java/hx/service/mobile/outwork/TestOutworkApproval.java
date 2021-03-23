package hx.service.mobile.outwork;

import hx.base.core.manage.tools.httpclient.HttpClientHelper;
import hx.service.mobile.ApplicationTests;
import hx.service.mobile.model.common.MobileCommonRequest;
import hx.service.mobile.model.person.outwork.approval.OutworkApprovalApprovalRequest;
import org.junit.jupiter.api.Test;

import java.io.IOException;

/**
 * @name: TestOutworkApproval
 * @description: 测试离职审批相关
 * @author: huojiajin
 * @time: 2021/3/16 15:18
 */
public class TestOutworkApproval extends ApplicationTests {

    @Test
    public void query() throws IOException {
        String url = "http://39.106.226.73:81/mobile/outwork/approval/query";
//        String url = "http://localhost:81/mobile/outwork/apply/query";
        MobileCommonRequest request = new MobileCommonRequest();
        request.setToken("82bb3c229963450fbbce054bad092fc4");
        String responseStr = HttpClientHelper.jsonPost(url, request.toJson());
        echo(responseStr);
    }

    @Test
    public void approval() throws IOException {
//        String url = "http://39.106.226.73:81/mobile/outwork/approval/approval";
        String url = "http://localhost:81/mobile/outwork/approval/approval";
        OutworkApprovalApprovalRequest request = new OutworkApprovalApprovalRequest();
        request.setToken("82bb3c229963450fbbce054bad092fc4");
        request.setSignImg("data:image/png;base64,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");
        request.setAdopt(true);
        String responseStr = HttpClientHelper.jsonPost(url, request.toJson());
        echo(responseStr);
    }
}
