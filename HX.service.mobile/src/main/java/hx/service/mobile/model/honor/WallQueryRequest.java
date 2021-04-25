package hx.service.mobile.model.honor;

import hx.service.mobile.model.common.MobileCommonRequest;

/**
 * @ClassName: WallQueryRequest
 * @Description: 荣誉墙查询Request
 * @Author HuoJiaJin
 * @Date 2021/2/6 3:20
 * @Version 1.0
 **/
public class WallQueryRequest extends MobileCommonRequest {

    private Integer year;//年份

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }
}
