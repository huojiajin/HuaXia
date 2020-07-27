package hx.base.core.dao.entity.quartz;

import hx.base.core.dao.entity.common.StringUUIDEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @name: ScheduleTask
 * @description: 定时任务
 * @author: huojiajin
 * @time: 2020/7/22 15:55
 */
@Entity
@Table(name = "hx_schedule_task")
public class ScheduleTask extends StringUUIDEntity {

    private String name;//实例名称
    private String cron;//执行的cron
    private String description;//描述信息
    private Boolean auto;//是否自动执行
    private Boolean running;//是否正在执行

    @Column(name = "name", unique = true)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "cron")
    public String getCron() {
        return cron;
    }

    public void setCron(String cron) {
        this.cron = cron;
    }

    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name = "auto")
    public Boolean getAuto() {
        return auto;
    }

    public void setAuto(Boolean auto) {
        this.auto = auto;
    }

    @Column(name = "running")
    public Boolean getRunning() {
        return running;
    }

    public void setRunning(Boolean running) {
        this.running = running;
    }
}
