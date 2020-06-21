package hx.service.manage.dao.entity.acl;

import hx.service.manage.dao.entity.common.StringUUIDEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;

/**
 * @name: SystemInfo
 * @description: 系统日志表
 * @author: huojiajin
 * @time: 2020/5/27 10:48
 */
@Entity
@Table(name = "hx_system_info")
public class SystemInfo extends StringUUIDEntity {

    private String userId;//操作用户
    private String info;//操作详情
    private LocalDateTime insertTime;//操作时间

    @Column(name = "user_id")
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Column(name = "info")
    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    @Column(name = "insert_time")
    public LocalDateTime getInsertTime() {
        return insertTime;
    }

    public void setInsertTime(LocalDateTime insertTime) {
        this.insertTime = insertTime;
    }
}
