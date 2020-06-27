package hx.service.manage.manage.model.test.papers;

import hx.base.core.dao.entity.common.BaseEntity;

/**
 * @ClassName PapersImportOptionModel
 * @Description 试卷导入-选项Model
 * @Author HuoJiaJin
 * @Date 2020/6/21 20:53
 * @Version 1.0
 **/
public class PapersImportOptionModel extends BaseEntity {

    private int list;//序号
    private String content;//内容

    public int getList() {
        return list;
    }

    public void setList(int list) {
        this.list = list;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
