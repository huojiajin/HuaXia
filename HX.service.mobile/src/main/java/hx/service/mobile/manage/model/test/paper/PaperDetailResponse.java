package hx.service.mobile.manage.model.test.paper;

import hx.base.core.dao.entity.common.BaseEntity;

import java.util.List;

/**
 * @ClassName PaperDetailResponse
 * @Description 试卷详情Response
 * @Author HuoJiaJin
 * @Date 2020/6/28 20:40
 * @Version 1.0
 **/
public class PaperDetailResponse extends BaseEntity {

    private String id;//试卷id
    private String name;//试卷名称
    private int answerTime;//答题时长
    private List<PaperDetailSubjectModel> subjectList;//题目集合

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAnswerTime() {
        return answerTime;
    }

    public void setAnswerTime(int answerTime) {
        this.answerTime = answerTime;
    }

    public List<PaperDetailSubjectModel> getSubjectList() {
        return subjectList;
    }

    public void setSubjectList(List<PaperDetailSubjectModel> subjectList) {
        this.subjectList = subjectList;
    }
}
