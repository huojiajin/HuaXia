package hx.service.manage.manage.model.test.papers;

import hx.base.core.dao.entity.common.BaseEntity;

import java.util.List;

/**
 * @ClassName PapersViewResponse
 * @Description 试卷查看Response
 * @Author HuoJiaJin
 * @Date 2020/6/21 21:57
 * @Version 1.0
 **/
public class PapersViewResponse extends BaseEntity {

    private List<PapersViewModel> subjectList;//题目集合

    public List<PapersViewModel> getSubjectList() {
        return subjectList;
    }

    public void setSubjectList(List<PapersViewModel> subjectList) {
        this.subjectList = subjectList;
    }
}
