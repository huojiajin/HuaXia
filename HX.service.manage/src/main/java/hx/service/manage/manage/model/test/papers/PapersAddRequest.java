package hx.service.manage.manage.model.test.papers;

import hx.service.manage.manage.model.CommonPageRequest;

/**
 * @ClassName PapersAddRequest
 * @Description 试卷添加Request
 * @Author HuoJiaJin
 * @Date 2020/6/21 14:26
 * @Version 1.0
 **/
public class PapersAddRequest extends CommonPageRequest {

    private String name;//试卷名称
    private int type;//试卷类型
    private String endTime;//截止时间
    private int answerTime;//答题时长

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public int getAnswerTime() {
        return answerTime;
    }

    public void setAnswerTime(int answerTime) {
        this.answerTime = answerTime;
    }
}
