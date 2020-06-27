package hx.service.mobile.manage.model.test.paper;

import hx.base.core.dao.entity.common.BaseEntity;

import java.util.List;

/**
 * @ClassName PaperListResponse
 * @Description 试卷查询Response
 * @Author HuoJiaJin
 * @Date 2020/6/27 22:02
 * @Version 1.0
 **/
public class PaperListResponse extends BaseEntity {

    private List<PaperListModel> result;

    public List<PaperListModel> getResult() {
        return result;
    }

    public void setResult(List<PaperListModel> result) {
        this.result = result;
    }
}
