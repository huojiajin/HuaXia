package hx.service.mobile.model.test.paper;

import hx.base.core.dao.entity.common.BaseEntity;
import org.checkerframework.checker.units.qual.C;

/**
 * @name: PaperSubmitSubjectModel
 * @description: 试卷提交-题目Model
 * @author: huojiajin
 * @time: 2020/6/30 11:26
 */
public class PaperSubmitSubjectModel extends BaseEntity {

    private String subjectId;//题目ID
    private String answer;//答案 按序号顺序用“|”拼接

    public String getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(String subjectId) {
        this.subjectId = subjectId;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
