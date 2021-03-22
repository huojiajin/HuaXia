package hx.service.manage.manage.quartz;

import hx.base.core.manage.annotation.MyScheduler;
import org.springframework.stereotype.Service;

/**
 * @name: DaliyMessageQuartz
 * @description: TODO
 * @author: huojiajin
 * @time: 2021/3/22 17:38
 */
@Service
@MyScheduler(name = "MESSAGE_FAIL", cron = "0 10 9 * * ?")
public class DaliyMessageQuartz extends CommonQuartz{

    @Override
    public void run() {

    }
}
