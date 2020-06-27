package hx.base.core.dao.entity.test.papers;

import hx.base.core.dao.dict.PapersAnswerType;
import hx.base.core.dao.entity.common.AbstractInsertTimeEntity;

import javax.persistence.*;

/**
 * @ClassName PapersPush
 * @Description 试卷推送
 * @Author HuoJiaJin
 * @Date 2020/6/21 22:25
 * @Version 1.0
 **/
@Entity
@Table(name = "hx_papers_push", indexes = {
        @Index(columnList = "agent_code", name = "hx_papers_push_index"),
        @Index(columnList = "papers_id", name = "hx_papers_push_index1")
})
public class PapersPush extends AbstractInsertTimeEntity {

    private String papersId;//试卷ID
    private String agentCode;//营销员编码
    private String staffName;//员工姓名
    private PapersAnswerType answerType = PapersAnswerType.WDT;//答题状态
    private Integer score;//得分

    @Column(name = "papers_id")
    public String getPapersId() {
        return papersId;
    }

    public void setPapersId(String papersId) {
        this.papersId = papersId;
    }

    @Column(name = "agent_code")
    public String getAgentCode() {
        return agentCode;
    }

    public void setAgentCode(String agentCode) {
        this.agentCode = agentCode;
    }

    @Column(name = "staff_name")
    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    @Enumerated(EnumType.STRING)
    @Column(name = "answer_type")
    public PapersAnswerType getAnswerType() {
        return answerType;
    }

    public void setAnswerType(PapersAnswerType answerType) {
        this.answerType = answerType;
    }

    @Column(name = "score")
    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }
}
