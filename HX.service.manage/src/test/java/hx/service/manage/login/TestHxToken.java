package hx.service.manage.login;

import hx.service.manage.ApplicationTests;
import hx.service.manage.manage.quartz.AccessTokenQuartz;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @ClassName: TestHxToken
 * @Description: TODO
 * @Author HuoJiaJin
 * @Date 2021/3/16 0:58
 * @Version 1.0
 **/
@SpringBootTest
public class TestHxToken extends ApplicationTests {

    @Autowired
    private AccessTokenQuartz quartz;

    @Test
    void test(){
        quartz.run();
    }
}
