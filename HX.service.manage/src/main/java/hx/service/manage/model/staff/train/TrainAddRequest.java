package hx.service.manage.model.staff.train;

import hx.service.manage.model.common.CommonRequest;

/**
 * @name: TrainAddRequest
 * @description: 参训人员管理培训场次添加询Request
 * @author: huojiajin
 * @time: 2020/11/12 11:41
 */
public class TrainAddRequest extends CommonRequest {

    private String name;//培训场次名称
    private String trainTime;//培训时间

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
}
