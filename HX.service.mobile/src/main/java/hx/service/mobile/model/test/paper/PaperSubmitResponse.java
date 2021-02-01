package hx.service.mobile.model.test.paper;

import hx.base.core.dao.entity.common.BaseEntity;

/**
 * @name: PaperSubmitResponse
 * @description: 试卷提交Response
 * @author: huojiajin
 * @time: 2020/6/30 13:03
 */
public class PaperSubmitResponse extends BaseEntity {

    private int score;//得分
    private int integral;//积分

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getIntegral() {
        return integral;
    }

    public void setIntegral(int integral) {
        this.integral = integral;
    }
}
