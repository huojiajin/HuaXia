package hx.base.core.dao.repo.jpa.quartz;

import hx.base.core.dao.entity.quartz.ScheduleTask;
import hx.base.core.dao.entity.quartz.ScheduleTaskLog;
import hx.base.core.dao.repo.jpa.common.AbstractJpaRepo;

/**
 *@ClassName ScheduleTaskLogRepo
 *@Description 定时任务日志表Repo
 *@Author HuoJiaJin
 *@Date 2020/7/24 15:09
 *@Version 1.0
 **/
public interface ScheduleTaskLogRepo extends AbstractJpaRepo<ScheduleTaskLog, String> {
}
