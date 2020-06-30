package hx.service.mobile.manage.model.test.paper;

import hx.base.core.dao.entity.common.BaseEntity;

import java.util.List;

/**
 * @name: PaperCompletedDetailModel
 * @description: 获取已答试卷详情-题目Model
 * @author: huojiajin
 * @time: 2020/6/30 14:45
 */
public class PaperCompletedDetailSubjectModel extends BaseEntity {

    private String id;//题目ID
    private Integer index;//序号
    private String stem;//题干
    private Integer type;//题目类型
    private List<PaperCompletedDetailOptionModel> optionList;//选项集合

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public String getStem() {
        return stem;
    }

    public void setStem(String stem) {
        this.stem = stem;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public List<PaperCompletedDetailOptionModel> getOptionList() {
        return optionList;
    }

    public void setOptionList(List<PaperCompletedDetailOptionModel> optionList) {
        this.optionList = optionList;
    }
}
