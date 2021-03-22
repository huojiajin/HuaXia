package hx.service.mobile.outwork;

import hx.base.core.manage.tools.httpclient.HttpClientHelper;
import hx.service.mobile.ApplicationTests;
import hx.service.mobile.model.common.MobileCommonRequest;
import hx.service.mobile.model.person.outwork.apply.OutworkApplyApplyRequest;
import org.junit.jupiter.api.Test;

import java.io.IOException;

/**
 * @name: TestOutwork
 * @description: 测试离职申请相关
 * @author: huojiajin
 * @time: 2021/3/16 15:17
 */
public class TestOutworkApply extends ApplicationTests {

    @Test
    public void query() throws IOException {
//        String url = "http://39.106.226.73:81/mobile/outwork/apply/query";
        String url = "http://localhost:81/mobile/outwork/apply/query";
        MobileCommonRequest request = new MobileCommonRequest();
        request.setToken("7ea849e7f26348498a6bc6d8cd383b09");
        String responseStr = HttpClientHelper.jsonPost(url, request.toJson());
        echo(responseStr);
    }

    @Test
    public void apply() throws IOException {

        OutworkApplyApplyRequest request = new OutworkApplyApplyRequest();
        request.setReason("123123");
        request.setToken("7ea849e7f26348498a6bc6d8cd383b09");
        request.setSignImg("data:image/png;base64,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");
        request.setIdcardBackImg("data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAABVoA");
        request.setIdcardFrontImg("data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAVYA");

        //String url = "http://39.106.226.73:81/mobile/outwork/apply/apply";
        String url = "http://localhost:81/mobile/outwork/apply/apply";
        String responseStr = HttpClientHelper.jsonPost(url, request.toJson());
        echo(responseStr);
    }
}
