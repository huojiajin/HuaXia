package hx.service.manage.web.schedule;

import hx.service.manage.manage.schedule.ScheduleManager;
import hx.service.manage.web.MyBaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @name: ScheduleController
 * @description: 定时任务相关Controller
 * @author: huojiajin
 * @time: 2020/7/24 16:45
 */
@Controller
@RequestMapping("/schedule")
public class ScheduleController extends MyBaseController {

    @Autowired
    private ScheduleManager manager;


}
