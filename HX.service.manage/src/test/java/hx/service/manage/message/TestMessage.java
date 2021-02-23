package hx.service.manage.message;

import hx.base.core.manage.tools.httpclient.HttpClientHelper;
import hx.service.manage.ApplicationTests;
import hx.service.manage.model.message.MessageAddRequest;
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

    private static final String token = "d3d88221b8c9416b9ed0e15140f2b2c4";

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
//        String url = "http://123.56.154.176/manage/message/add";
        String url = "http://localhost/manage/message/add";
        String s = HttpClientHelper.jsonPost(url, request.toJson());
        echo(s);
    }
}
