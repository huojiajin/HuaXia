package hx.service.manage.model.quit;

import hx.service.manage.model.common.CommonRequest;

/**
 * @ClassName: QuitApplyIdRequest
 * @Description: 离职申请ID Request
 * @Author HuoJiaJin
 * @Date 2021/2/2 23:23
 * @Version 1.0
 **/
public class QuitApplyIdRequest extends CommonRequest {

    private String applyId;//申请ID

    public String getApplyId() {
        return applyId;
    }

    public void setApplyId(String applyId) {
        this.applyId = applyId;
    }
}
