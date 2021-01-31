package hx.base.core.dao.entity.common;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

/**
 * @name: AbstractInsertTimeEntity
 * @description: 公用时间Entity
 * @author: huojiajin
 * @time: 2020/5/28 15:17
 */
@MappedSuperclass
public class AbstractInsertTimeEntity extends StringUUIDEntity {

    private LocalDateTime insertTime = LocalDateTime.now();//插入时间
    private LocalDateTime updateTime;//更新时间

    @Column(name = "insert_time")
    public LocalDateTime getInsertTime() {
        return insertTime;
    }

    public void setInsertTime(LocalDateTime insertTime) {
        this.insertTime = insertTime;
    }

    @Column(name = "update_time")
    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }
}
