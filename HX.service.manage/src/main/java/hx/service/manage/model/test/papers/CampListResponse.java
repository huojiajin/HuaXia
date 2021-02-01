package hx.service.manage.model.test.papers;

import hx.base.core.dao.entity.common.BaseEntity;

import java.util.List;

/**
 * @name: CampListResponse
 * @description: 营服列表Response
 * @author: huojiajin
 * @time: 2020/11/12 16:26
 */
public class CampListResponse extends BaseEntity {

    private List<CampModel> modelList;

    public List<CampModel> getModelList() {
        return modelList;
    }

    public void setModelList(List<CampModel> modelList) {
        this.modelList = modelList;
    }
}
