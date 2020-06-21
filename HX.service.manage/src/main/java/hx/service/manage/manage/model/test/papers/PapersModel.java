package hx.service.manage.manage.model.test.papers;

import hx.service.manage.dao.entity.common.BaseEntity;

/**
 * @ClassName PapersModel
 * @Description 试卷Model
 * @Author HuoJiaJin
 * @Date 2020/6/22 0:35
 * @Version 1.0
 **/
public class PapersModel extends BaseEntity {

    private String id;
    private String name;//试卷名称
    private Integer type;//试卷类型
    private String endTime;//截止时间
    private int answerTime;//答题时长
    private Integer status;//状态

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

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
