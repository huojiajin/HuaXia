package hx.service.manage.dao.entity.test;

import hx.service.manage.dao.dict.PapersSubjectType;
import hx.service.manage.dao.entity.common.StringUUIDEntity;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * @ClassName PapersSubject
 * @Description 试卷题目
 * @Author HuoJiaJin
 * @Date 2020/6/21 13:55
 * @Version 1.0
 **/
@Entity
@Table(name = "hx_papers_subject", indexes = {
        @Index(columnList = "papers_id", name = "hx_papers_subject_index")
})
public class PapersSubject extends StringUUIDEntity {

    private String papersId;//试卷ID
    private int list;//序号
    private String subject;//题目
    private String correctNum;//答案序号，用"|"连接
    private PapersSubjectType type;//题目类型
    private LocalDateTime answerTime;//答题时间
    private int score;//分值

    @Column(name = "papers_id")
    public String getPapersId() {
        return papersId;
    }

    public void setPapersId(String papersId) {
        this.papersId = papersId;
    }

    @Column(name = "list")
    public int getList() {
        return list;
    }

    public void setList(int list) {
        this.list = list;
    }

    @Column(name = "subject")
    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    @Column(name = "correct_num")
    public String getCorrectNum() {
        return correctNum;
    }

    public void setCorrectNum(String correctNum) {
        this.correctNum = correctNum;
    }

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    public PapersSubjectType getType() {
        return type;
    }

    public void setType(PapersSubjectType type) {
        this.type = type;
    }

    @Column(name = "answer_time")
    public LocalDateTime getAnswerTime() {
        return answerTime;
    }

    public void setAnswerTime(LocalDateTime answerTime) {
        this.answerTime = answerTime;
    }

    @Column(name = "score")
    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
