package hx.service.mobile.model.back.org;

import hx.base.core.dao.entity.common.BaseEntity;

import java.util.List;

/**
 * @name: RadarCampListResponse
 * @description: 雷达图-获取相关营服列表Response
 * @author: huojiajin
 * @time: 2021/2/25 11:52
 */
public class BackCampListResponse extends BaseEntity {

    private List<BackCampModel> campList;//营业区列表

    public List<BackCampModel> getCampList() {
        return campList;
    }

    public void setCampList(List<BackCampModel> campList) {
        this.campList = campList;
    }
}
