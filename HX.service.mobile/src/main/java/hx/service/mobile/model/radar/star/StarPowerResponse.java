package hx.service.mobile.model.radar.star;

import hx.base.core.dao.entity.common.BaseEntity;

/**
 * @ClassName StarPowerResponse
 * @Description 星级人力Response
 * @Author HuoJiaJin
 * @Date 2020/6/27 15:23
 * @Version 1.0
 **/
public class StarPowerResponse extends BaseEntity {

    private int count;//四星以上人数
    private int preCount;//预四星人数
    private int type;//类别

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getPreCount() {
        return preCount;
    }

    public void setPreCount(int preCount) {
        this.preCount = preCount;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
