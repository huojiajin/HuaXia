package hx.service.manage.message;

import hx.base.core.dao.entity.message.MessageCustom;
import hx.base.core.dao.repo.jpa.message.MessageCustomRepo;
import hx.service.manage.ApplicationTests;
import hx.service.manage.manage.message.MessageManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

/**
 * @ClassName: TestSendMessage
 * @Description: 测试发送数据
 * @Author HuoJiaJin
 * @Date 2021/3/16 0:19
 * @Version 1.0
 **/
@SpringBootTest
public class TestSendMessage extends ApplicationTests {

    @Autowired
    private MessageManager manager;
    @Autowired
    private MessageCustomRepo customRepo;

    @Test
    void test(){
        Optional<MessageCustom> op = customRepo.findById("5b149485cb234addbc55cb48f1e451ea");
        MessageCustom custom = op.get();
        manager.sendTextOne(custom, "luchaochao");
    }
}
