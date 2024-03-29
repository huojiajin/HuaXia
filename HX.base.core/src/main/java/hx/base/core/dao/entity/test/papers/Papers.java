package hx.base.core.dao.entity.test.papers;

import hx.base.core.dao.dict.test.PapersStatus;
import hx.base.core.dao.dict.test.PapersType;
import hx.base.core.dao.entity.common.AbstractInsertTimeEntity;

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
    private Integer answerTime;//答题时长
    private PapersStatus status = PapersStatus.WDR;//状态
    private Integer subjectNum;//题数
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
    public Integer getAnswerTime() {
        return answerTime;
    }

    public void setAnswerTime(Integer answerTime) {
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

    @Column(name = "subject_num")
    public Integer getSubjectNum() {
        return subjectNum;
    }

    public void setSubjectNum(Integer subjectNum) {
        this.subjectNum = subjectNum;
    }

    @Column(name = "has_delete")
    public boolean isHasDelete() {
        return hasDelete;
    }

    public void setHasDelete(boolean delete) {
        this.hasDelete = delete;
    }
}
