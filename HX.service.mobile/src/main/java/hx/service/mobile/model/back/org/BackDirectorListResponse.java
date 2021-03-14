package hx.service.mobile.model.back.org;

import hx.base.core.dao.entity.common.BaseEntity;

import java.util.List;

/**
 * @name: RadarDirectorListResponse
 * @description: 雷达图-获取相关总监区列表Response
 * @author: huojiajin
 * @time: 2021/2/25 11:52
 */
public class BackDirectorListResponse extends BaseEntity {

    private List<BackDirectorModel> directorList;//总监区列表

    public List<BackDirectorModel> getDirectorList() {
        return directorList;
    }

    public void setDirectorList(List<BackDirectorModel> directorList) {
        this.directorList = directorList;
    }
}
