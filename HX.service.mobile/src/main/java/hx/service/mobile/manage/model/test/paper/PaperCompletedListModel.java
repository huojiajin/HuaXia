package hx.service.mobile.manage.model.test.paper;

import hx.base.core.dao.entity.common.BaseEntity;

/**
 * @name: PaperCompletedListModel
 * @description: 已答试卷列表查询Model
 * @author: huojiajin
 * @time: 2020/6/30 13:27
 */
public class PaperCompletedListModel extends BaseEntity {

    private String id;//试卷ID
    private String name;//试卷名称
    private String completeTime;//答题时间yyyy-MM-dd HH:mm:ss
    private Integer num;//题数
    private Integer score;//分数

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

    public String getCompleteTime() {
        return completeTime;
    }

    public void setCompleteTime(String completeTime) {
        this.completeTime = completeTime;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }
}
