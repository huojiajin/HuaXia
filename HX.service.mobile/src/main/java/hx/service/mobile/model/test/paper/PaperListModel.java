package hx.service.mobile.model.test.paper;

import hx.base.core.dao.entity.common.BaseEntity;

/**
 * @ClassName PaperListModel
 * @Description 试卷查询Model
 * @Author HuoJiaJin
 * @Date 2020/6/27 22:04
 * @Version 1.0
 **/
public class PaperListModel extends BaseEntity {

    private String id;//试卷id
    private String name;//试卷名称
    private String endTime;//截止时间
    private int num;//题数
    private int answerType;//回答状态

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

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public int getAnswerType() {
        return answerType;
    }

    public void setAnswerType(int answerType) {
        this.answerType = answerType;
    }
}
