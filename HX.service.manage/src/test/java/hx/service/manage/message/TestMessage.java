package hx.service.manage.message;

import hx.base.core.manage.tools.httpclient.HttpClientHelper;
import hx.service.manage.ApplicationTests;
import hx.service.manage.model.message.MessageAddRequest;
import hx.service.manage.model.message.MessageQueryRequest;
import org.junit.jupiter.api.Test;

import java.io.IOException;

/**
 * @ClassName: TestMessage
 * @Description: 自定义消息测试
 * @Author HuoJiaJin
 * @Date 2021/2/20 17:57
 * @Version 1.0
 **/
public class TestMessage extends ApplicationTests {

    private static final String token = "9dec44d1f9bc49cab5ade7d020b0416b";

    @Test
    void add() throws IOException {
        MessageAddRequest request = new MessageAddRequest();
        request.setToken(token);
        request.setResourceCode(6);
        request.setName("消息1");
        request.setContentType(1);
        request.setDeadline("2021-02-28");
        request.setMessageType(21);
        request.setContent("文字内容");
//        String url = "http://39.106.226.73/manage/message/add";
        String url = "http://localhost/manage/message/add";
        String s = HttpClientHelper.jsonPost(url, request.toJson());
        echo(s);
    }

    @Test
    void query() throws IOException {
        MessageQueryRequest request = new MessageQueryRequest();
        request.setToken(token);
        request.setResourceCode(6);
//        String url = "http://39.106.226.73/manage/message/query";
        String url = "http://localhost/manage/message/query";
        String s = HttpClientHelper.jsonPost(url, request.toJson());
        echo(s);
    }
}
