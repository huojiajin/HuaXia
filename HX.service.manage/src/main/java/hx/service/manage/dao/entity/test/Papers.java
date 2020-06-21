package hx.service.manage.dao.entity.test;

import hx.service.manage.dao.dict.PapersStatus;
import hx.service.manage.dao.dict.PapersType;
import hx.service.manage.dao.entity.common.AbstractInsertTimeEntity;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * @ClassName Papers
 * @Description 试卷管理
 * @Author HuoJiaJin
 * @Date 2020/6/20 21:51
 * @Version 1.0
 **/
@Entity
@Table(name = "hx_papers")
public class Papers extends AbstractInsertTimeEntity {

    private String name;//试卷名称
    private PapersType type;//试卷类型
    private LocalDateTime endTime;//截止时间
    private int answerTime;//答题时长
    private PapersStatus status = PapersStatus.WDR;//状态
    private boolean hasDelete;//是否删除

    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    public PapersType getType() {
        return type;
    }

    public void setType(PapersType type) {
        this.type = type;
    }

    @Column(name = "end_time")
    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    @Column(name = "answer_time")
    public int getAnswerTime() {
        return answerTime;
    }

    public void setAnswerTime(int answerTime) {
        this.answerTime = answerTime;
    }

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    public PapersStatus getStatus() {
        return status;
    }

    public void setStatus(PapersStatus status) {
        this.status = status;
    }

    @Column(name = "has_delete")
    public boolean isHasDelete() {
        return hasDelete;
    }

    public void setHasDelete(boolean delete) {
        this.hasDelete = delete;
    }
}
