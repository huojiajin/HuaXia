package hx.service.manage.manage.model.staff.train;

import hx.service.manage.manage.model.CommonPageRequest;

/**
 * @name: TrainQueryRequest
 * @description: 参训人员管理培训场次查询Request
 * @author: huojiajin
 * @time: 2020/11/12 11:15
 */
public class TrainQueryRequest extends CommonPageRequest {

    private String name;//培训名称
    private String month;//月份
    private Integer status;//状态

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
