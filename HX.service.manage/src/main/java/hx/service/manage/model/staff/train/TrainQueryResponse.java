package hx.service.manage.model.staff.train;

import hx.base.core.dao.entity.common.BaseEntity;

/**
 * @name: TrainQueryResponse
 * @description: 参训人员管理培训场次查询Response
 * @author: huojiajin
 * @time: 2020/11/12 11:15
 */
public class TrainQueryResponse extends BaseEntity {

    private String id;//培训ID
    private String name;//培训名称
    private String trainTime;//培训时间
    private int peopleNum;//培训人数
    private int status;//培训场次状态

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTrainTime() {
        return trainTime;
    }

    public void setTrainTime(String trainTime) {
        this.trainTime = trainTime;
    }

    public int getPeopleNum() {
        return peopleNum;
    }

    public void setPeopleNum(int peopleNum) {
        this.peopleNum = peopleNum;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
