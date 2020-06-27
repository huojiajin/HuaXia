package hx.service.manage.manage.model.test.papers;

import hx.base.core.dao.entity.common.BaseEntity;

import java.util.List;

/**
 * @ClassName PapersViewModel
 * @Description 试卷查看Model
 * @Author HuoJiaJin
 * @Date 2020/6/21 21:58
 * @Version 1.0
 **/
public class PapersViewModel extends BaseEntity {

    private int index;//序号
    private String stem;//题干
    private int type;//类型
    private String correctNum;//正确答案索引
    private List<String> optionList;//选项集合

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getStem() {
        return stem;
    }

    public void setStem(String stem) {
        this.stem = stem;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getCorrectNum() {
        return correctNum;
    }

    public void setCorrectNum(String correctNum) {
        this.correctNum = correctNum;
    }

    public List<String> getOptionList() {
        return optionList;
    }

    public void setOptionList(List<String> optionList) {
        this.optionList = optionList;
    }
}
