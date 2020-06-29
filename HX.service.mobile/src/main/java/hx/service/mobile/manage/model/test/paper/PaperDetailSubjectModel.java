package hx.service.mobile.manage.model.test.paper;

import hx.base.core.dao.entity.common.BaseEntity;

import java.util.List;

/**
 * @ClassName PaperDetailSubjectModel
 * @Description 试卷详情-题目Model
 * @Author HuoJiaJin
 * @Date 2020/6/28 20:40
 * @Version 1.0
 **/
public class PaperDetailSubjectModel extends BaseEntity {

    private String id;//题目ID
    private Integer index;//序号
    private String stem;//题干
    private int type;//题目类型 1单选 2多选
    private List<PaperDetailOptionModel> optionList;//选项List

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

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public List<PaperDetailOptionModel> getOptionList() {
        return optionList;
    }

    public void setOptionList(List<PaperDetailOptionModel> optionList) {
        this.optionList = optionList;
    }
}
