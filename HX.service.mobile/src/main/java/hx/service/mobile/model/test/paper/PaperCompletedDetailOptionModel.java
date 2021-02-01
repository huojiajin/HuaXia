package hx.service.mobile.model.test.paper;

import hx.base.core.dao.entity.common.BaseEntity;

/**
 * @name: PaperCompletedDetailOptionModel
 * @description: 获取已答试卷详情-选项Model
 * @author: huojiajin
 * @time: 2020/6/30 14:47
 */
public class PaperCompletedDetailOptionModel extends BaseEntity {

    private Integer index;//序号
    private String option;//选项
    private boolean isSelect;//是否已选
    private boolean isCorrect;//是否正确答案

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public String getOption() {
        return option;
    }

    public void setOption(String option) {
        this.option = option;
    }

    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }

    public boolean isCorrect() {
        return isCorrect;
    }

    public void setCorrect(boolean correct) {
        isCorrect = correct;
    }
}
