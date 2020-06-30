package hx.service.mobile.manage.model.test.paper;

import hx.base.core.dao.entity.common.BaseEntity;

import java.util.List;

/**
 * @name: PaperCompletedListResponse
 * @description: 已答试卷列表查询Response
 * @author: huojiajin
 * @time: 2020/6/30 14:35
 */
public class PaperCompletedListResponse extends BaseEntity {

    private List<PaperCompletedListModel> result;

    public List<PaperCompletedListModel> getResult() {
        return result;
    }

    public void setResult(List<PaperCompletedListModel> result) {
        this.result = result;
    }
}
