package hx.service.manage.model.staff.train;

import hx.service.manage.model.common.CommonPageRequest;

/**
 * @name: TrainPeopleQueryRequest
 * @description: 参训人员管理人员查询Request
 * @author: huojiajin
 * @time: 2020/11/12 11:44
 */
public class TrainPeopleQueryRequest extends CommonPageRequest {

    private String trainId;//培训ID
    private String campName;//营服名称
    private String sectionName;//部名称
    private String groupName;//组名称
    private String name;//人员名称

    public String getTrainId() {
        return trainId;
    }

    public void setTrainId(String trainId) {
        this.trainId = trainId;
    }

    public String getCampName() {
        return campName;
    }

    public void setCampName(String campName) {
        this.campName = campName;
    }

    public String getSectionName() {
        return sectionName;
    }

    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
