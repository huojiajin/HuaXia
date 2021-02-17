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

    private int year;//年份

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
