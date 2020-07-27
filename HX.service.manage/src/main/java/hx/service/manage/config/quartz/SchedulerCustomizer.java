//package hx.service.manage.config.quartz;
//
//import org.springframework.boot.autoconfigure.quartz.SchedulerFactoryBeanCustomizer;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.scheduling.annotation.EnableScheduling;
//import org.springframework.scheduling.quartz.SchedulerFactoryBean;
//
///**
// *@ClassName SchedulerCustomizer
// *@Description 定时任务自定义实体类配置
// *@Author HuoJiaJin
// *@Date 2020/7/27 11:27
// *@Version 1.0
// **/
//@Configuration
//@EnableScheduling
//public class SchedulerCustomizer implements SchedulerFactoryBeanCustomizer {
//    @Override
//    public void customize(SchedulerFactoryBean schedulerFactoryBean) {
//        schedulerFactoryBean.setStartupDelay(2);
//        schedulerFactoryBean.setAutoStartup(true);
//        schedulerFactoryBean.setOverwriteExistingJobs(true);
//    }
//}
