package hx.service.mobile;

import hx.base.core.dao.entity.quit.QuitApply;
import hx.base.core.dao.repo.jpa.quit.QuitApplyRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @ClassName: TestViewApply
 * @Description: 测试薪资项目定时任务
 * @Author HuoJiaJin
 * @Date 2021/2/22 23:44
 * @Version 1.0
 **/
@SpringBootTest
public class TestViewApply extends MobileApplicationTests {

    @Autowired
    private QuitApplyRepo applyRepo;

    @Test
    public void test(){
        List<QuitApply> all = applyRepo.findAll();
        for (QuitApply quitApply : all) {
            echo(new String(quitApply.getIdCardFrontImg()));
        }
    }
}
