package hx.service.manage.model.test.integral;

import hx.base.core.dao.entity.common.BaseEntity;

/**
 * @ClassName IntegralExportResponse
 * @Description 积分管理导出response
 * @Author HuoJiaJin
 * @Date 2020/6/23 20:00
 * @Version 1.0
 **/
public class IntegralExportResponse extends BaseEntity {

    private String integralFile;

    public String getIntegralFile() {
        return integralFile;
    }

    public void setIntegralFile(String integralFile) {
        this.integralFile = integralFile;
    }
}
