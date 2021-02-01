package hx.service.manage.model.staff.train;

import hx.service.manage.model.common.CommonRequest;

/**
 * @ClassName TrainIdRequest
 * @Description 参训人员管理ID通用Request
 * @Author HuoJiaJin
 * @Date 2020/6/22 22:43
 * @Version 1.0
 **/
public class TrainIdRequest extends CommonRequest {

    private String trainId;

    public String getTrainId() {
        return trainId;
    }

    public void setTrainId(String trainId) {
        this.trainId = trainId;
    }
}
