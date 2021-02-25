package hx.service.mobile.model.structure.internal;

import hx.base.core.dao.entity.common.BaseEntity;
import java.util.List;

/**
 * @name: StructureCampListResponse
 * @description: 组织架构-获取相关营服列表Response
 * @author: huojiajin
 * @time: 2021/2/25 11:52
 */
public class StructureCampListResponse extends BaseEntity {

    private List<StructureCampModel> campList;//营业区列表

    public List<StructureCampModel> getCampList() {
        return campList;
    }

    public void setCampList(List<StructureCampModel> campList) {
        this.campList = campList;
    }
}
