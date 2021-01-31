package hx.service.manage.manage.model.honor;

import hx.service.manage.manage.model.CommonRequest;

/**
 * @ClassName: HonorIdRequest
 * @Description: 荣誉ID通用Request
 * @Author HuoJiaJin
 * @Date 2021/2/1 1:28
 * @Version 1.0
 **/
public class HonorIdRequest extends CommonRequest {

    private String honorId;//荣誉id

    public String getHonorId() {
        return honorId;
    }

    public void setHonorId(String honorId) {
        this.honorId = honorId;
    }
}
