package hx.service.manage.manage.schedule;

import org.quartz.*;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @name: ScheduleManager
 * @description: 定时任务相关Manager
 * @author: huojiajin
 * @time: 2020/7/24 16:31
 */
public interface ScheduleManager {

    /**
     * @Name runByName
     * @Author HuoJiaJin
     * @Description 根据名称调用定时任务，调用成功五秒后执行
     * @Date 2020/9/17 0:00
     * @Param [name]
     * @return void
     **/
    void runByName(String name) throws Exception;
}
