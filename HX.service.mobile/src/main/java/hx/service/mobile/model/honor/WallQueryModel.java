package hx.service.mobile.model.honor;

import hx.base.core.dao.entity.common.BaseEntity;

/**
 * @ClassName: WallQueryModel
 * @Description: 荣誉墙查询Model
 * @Author HuoJiaJin
 * @Date 2021/2/6 3:23
 * @Version 1.0
 **/
public class WallQueryModel extends BaseEntity {

    private String honorName;//荣誉名称
    private String obtainTime;//荣誉获得时间
    private String honorImage;//荣誉图片

    public String getHonorName() {
        return honorName;
    }

    public void setHonorName(String honorName) {
        this.honorName = honorName;
    }

    public String getObtainTime() {
        return obtainTime;
    }

    public void setObtainTime(String obtainTime) {
        this.obtainTime = obtainTime;
    }

    public String getHonorImage() {
        return honorImage;
    }

    public void setHonorImage(String honorImage) {
        this.honorImage = honorImage;
    }
}
