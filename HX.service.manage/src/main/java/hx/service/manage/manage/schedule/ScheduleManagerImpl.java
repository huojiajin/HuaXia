package hx.service.manage.manage.schedule;

import hx.base.core.dao.entity.quartz.ScheduleTask;
import hx.base.core.dao.repo.jpa.quartz.ScheduleTaskRepo;
import hx.base.core.manage.annotation.MyScheduler;
import hx.base.core.manage.common.CommonAbstract;
import org.apache.commons.compress.utils.Lists;
import org.quartz.*;
import org.quartz.impl.matchers.GroupMatcher;
import org.reflections.Reflections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * @name: ScheduleManagerImpl
 * @description: 定时任务相关ManagerImpl
 * @author: huojiajin
 * @time: 2020/7/24 16:32
 */
@Service
public class ScheduleManagerImpl extends CommonAbstract implements ScheduleManager {

    @Autowired
    private ScheduleTaskRepo repo;
    @Autowired
    private SchedulerFactoryBean schedulerFactoryBean;

    @PostConstruct
    public void initSchedule(){
        try {
            reStartAllJobs();
            logger.info("======Schedule init success");
        } catch (Exception e) {
            logger.error(" ", e);
            logger.error("======Schedule init failed");
        }
    }

    /**
     * @Name reStartAllJobs
     * @Author HuoJiaJin
     * @Description 重新启动所有的job
     * @Date 2020/9/16 23:37
     * @Param []
     * @return void
     **/
    private void reStartAllJobs() throws Exception {
        synchronized (logger) {                                                         //只允许一个线程进入操作
            Scheduler scheduler = schedulerFactoryBean.getScheduler();
            Set<JobKey> set = scheduler.getJobKeys(GroupMatcher.anyGroup());
            scheduler.pauseJobs(GroupMatcher.anyGroup());                               //暂停所有JOB
            for (JobKey jobKey : set) {                                                 //删除从数据库中注册的所有JOB
                scheduler.unscheduleJob(TriggerKey.triggerKey(jobKey.getName(), jobKey.getGroup()));
                scheduler.deleteJob(jobKey);
            }
            List<ScheduleTask> taskList = Lists.newArrayList();
            //通过反射获取所有带@MyScheduler注解的类
            Reflections f = new Reflections("hx.service.manage.manage.quartz");
            Set<Class<?>> scheduleClasses = f.getTypesAnnotatedWith(MyScheduler.class);
            for (Class<?> scheduleClass : scheduleClasses) {
                MyScheduler annotation = scheduleClass.getAnnotation(MyScheduler.class);
                String cron = annotation.cron();
                String description = annotation.description();
                String name = annotation.name();
                if (!hasText(name)){
                    name = scheduleClass.getName();
                }
                //设置任务
                JobDataMap map = getJobDataMap(name, cron, description);
                JobKey jobKey = JobKey.jobKey(name);
                JobDetail jobDetail = JobBuilder.newJob((Class<? extends QuartzJobBean>) scheduleClass)
                        .withIdentity(jobKey)
                        .withDescription(description)
                        .setJobData(map)
                        .storeDurably()
                        .build();
                scheduler.scheduleJob(jobDetail, TriggerBuilder.newTrigger()
                        .withIdentity(name, null)
                        .withSchedule(CronScheduleBuilder.cronSchedule(cron))
                        .build());

                //拼装实体，存入数据库
                ScheduleTask task = new ScheduleTask();
                task.setName(name);
                task.setCron(cron);
                task.setDescription(description);
                task.setAuto(true);
                task.setRunning(false);
                taskList.add(task);
            }
            //更新数据库
            update(taskList);
        }
    }

    @Transactional(rollbackFor=Exception.class)
    private void update(List<ScheduleTask> taskList){
        repo.deleteAll();
        repo.persistAll(taskList);
    }

    public JobDataMap getJobDataMap(String name, String cron, String description) {
        JobDataMap map = new JobDataMap();
        map.put("name", name);
        map.put("cron", cron);
        map.put("description", description);
        map.put("auto", true);
        return map;
    }

    @Override
    public void runByName(String name) throws Exception {
        ScheduleTask task = repo.findByName(name);
        //通过反射获取所有带@MyScheduler注解的类
        Reflections f = new Reflections("hx.service.manage.manage.quartz");
        Set<Class<?>> scheduleClasses = f.getTypesAnnotatedWith(MyScheduler.class);
        Class<?> scheduleClass = null;
        for (Class<?> aClass : scheduleClasses) {
            MyScheduler annotation = scheduleClass.getAnnotation(MyScheduler.class);
            String aName = annotation.name();
            if (!hasText(aName)){
                aName = scheduleClass.getName();
            }
            if (name.equals(aName)){
                scheduleClass = aClass;
            }
        }
        if (scheduleClass == null){
            throw new InterruptedException("无此定时任务");
        }
        //创建任务
        Scheduler scheduler = schedulerFactoryBean.getScheduler();
        JobDataMap map = getJobDataMap(name, task.getCron(), task.getDescription());
        JobKey jobKey = JobKey.jobKey(name);
        JobDetail jobDetail = JobBuilder.newJob((Class<? extends QuartzJobBean>) scheduleClass)
                .withIdentity(jobKey)
                .withDescription(task.getDescription())
                .setJobData(map)
                .storeDurably()
                .build();
        scheduler.scheduleJob(jobDetail, TriggerBuilder.newTrigger()
                .withIdentity(name, null)
                .startAt(new Date(System.currentTimeMillis() + 5 * 1000))
                .build());

    }
}
