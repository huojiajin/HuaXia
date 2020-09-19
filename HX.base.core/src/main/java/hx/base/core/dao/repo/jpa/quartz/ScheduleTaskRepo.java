package hx.base.core.dao.repo.jpa.quartz;

import hx.base.core.dao.entity.Attendance;
import hx.base.core.dao.entity.quartz.ScheduleTask;
import hx.base.core.dao.repo.jpa.common.AbstractJpaRepo;
import org.springframework.data.jpa.repository.Query;

/**
 *@ClassName ScheduleTaskRepo
 *@Description 定时任务表Repo
 *@Author HuoJiaJin
 *@Date 2020/7/24 15:08
 *@Version 1.0
 **/
public interface ScheduleTaskRepo extends AbstractJpaRepo<ScheduleTask, String> {

    @Query(" from ScheduleTask where name = ?1")
    ScheduleTask findByName(String name);
}
