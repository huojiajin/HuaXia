package hx.service.manage.manage.quartz;

import hx.base.core.dao.entity.quartz.ScheduleTaskLog;
import hx.base.core.dao.repo.jpa.quartz.ScheduleTaskLogRepo;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

/**
 * @name: CommonQuartz
 * @description: 定时任务公用父类
 * @author: huojiajin
 * @time: 2020/7/16 15:04
 */
public abstract class CommonQuartz extends QuartzJobBean {

    protected Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private ScheduleTaskLogRepo logRepo;

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        JobDataMap map = jobExecutionContext.getMergedJobDataMap();
        String name = map.getString("name");
        LocalDateTime startTime = LocalDateTime.now();
        logger.info("=======开始运行定时任务{}, 运行时间为：{}", name, startTime);
        run();
        LocalDateTime endTime = LocalDateTime.now();
        long execTime = ChronoUnit.SECONDS.between(startTime, endTime);
        logger.info("=======定时任务{}运行结束, 结束时间为：{}，用时：{}", name, endTime, execTime);
        //保存定时任务日志
        ScheduleTaskLog log = new ScheduleTaskLog();
        log.setTaskName(name);
        log.setStartTime(startTime);
        log.setEndTime(endTime);
        log.setSystem(true);
        log.setExecTime(execTime);
        logRepo.persist(log);
    }

    public abstract void run();
}
