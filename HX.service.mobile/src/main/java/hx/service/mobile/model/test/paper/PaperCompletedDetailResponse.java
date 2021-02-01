package hx.service.mobile.model.test.paper;

import hx.base.core.dao.entity.common.BaseEntity;

import java.util.List;

/**
 * @name: PaperCompletedDetailResponse
 * @description: 获取已答试卷详情Response
 * @author: huojiajin
 * @time: 2020/6/30 14:44
 */
public class PaperCompletedDetailResponse extends BaseEntity {

    private String name;//试卷名称
    private List<PaperCompletedDetailSubjectModel> subjectList;//题目List

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<PaperCompletedDetailSubjectModel> getSubjectList() {
        return subjectList;
    }

    public void setSubjectList(List<PaperCompletedDetailSubjectModel> subjectList) {
        this.subjectList = subjectList;
    }
}
