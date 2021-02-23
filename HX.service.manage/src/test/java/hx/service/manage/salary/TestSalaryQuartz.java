package hx.service.manage.salary;

import hx.service.manage.ApplicationTests;
import hx.service.manage.manage.quartz.SalaryAlertQuartz;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

/**
 * @ClassName: TestSalaryQuartz
 * @Description: 测试薪资项目定时任务
 * @Author HuoJiaJin
 * @Date 2021/2/22 23:44
 * @Version 1.0
 **/
@SpringBootTest
public class TestSalaryQuartz extends ApplicationTests {

    @Autowired
    private SalaryAlertQuartz alertQuartz;

    @Test
    public void test(){
        LocalDate month = LocalDate.parse("2020-06-01");
        alertQuartz.deal(month);
    }
}
