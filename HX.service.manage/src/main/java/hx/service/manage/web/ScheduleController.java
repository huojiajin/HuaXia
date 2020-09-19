package hx.service.manage.web;

import hx.service.manage.manage.schedule.ScheduleManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @name: ScheduleController
 * @description: 定时任务相关Controller
 * @author: huojiajin
 * @time: 2020/9/16 23:59
 */
@RestController
@RequestMapping("/schedule")
public class ScheduleController extends MyBaseController{

    @Autowired
    private ScheduleManager manager;

    @GetMapping("/{name}")
    public String runByName(@PathVariable("name")String name, HttpServletRequest request, HttpServletResponse response){
        try {
            manager.runByName(name);
        } catch (Exception e) {
            return toLogString("调用定时任务{}失败!", name);
        }
        return toLogString("调用定时任务{}成功，将在五秒后执行！", name);
    }
}
