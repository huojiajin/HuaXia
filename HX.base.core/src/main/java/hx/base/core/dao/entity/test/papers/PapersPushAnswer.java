package hx.base.core.dao.entity.test.papers;

import hx.base.core.dao.entity.common.StringUUIDEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;

/**
 * @ClassName PapersPushAnswer
 * @Description 试卷推送答题结果表
 * @Author HuoJiaJin
 * @Date 2020/6/22 0:25
 * @Version 1.0
 **/
@Entity
@Table(name = "hx_papers_push_answer", indexes = {
        @Index(columnList = "push_id", name = "hx_papers_push_answer_index")
})
public class PapersPushAnswer extends StringUUIDEntity {

    private String pushId;//推送ID
    private String subjectId;//题目ID
    private String answer;//回答

    @Column(name = "push_id")
    public String getPushId() {
        return pushId;
    }

    public void setPushId(String pushId) {
        this.pushId = pushId;
    }

    @Column(name = "subject_id")
    public String getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(String subjectId) {
        this.subjectId = subjectId;
    }

    @Column(name = "answer")
    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
