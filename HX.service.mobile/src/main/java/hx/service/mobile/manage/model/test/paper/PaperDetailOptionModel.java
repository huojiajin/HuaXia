package hx.service.mobile.manage.model.test.paper;

import hx.base.core.dao.entity.common.BaseEntity;

/**
 * @ClassName PaperDetailOptionModel
 * @Description 试卷详情-选项Model
 * @Author HuoJiaJin
 * @Date 2020/6/28 20:42
 * @Version 1.0
 **/
public class PaperDetailOptionModel extends BaseEntity {

    private int index;//序号
    private String option;//选项

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getOption() {
        return option;
    }

    public void setOption(String option) {
        this.option = option;
    }
}
