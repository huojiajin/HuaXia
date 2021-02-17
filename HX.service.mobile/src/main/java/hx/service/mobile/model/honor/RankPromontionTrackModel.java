package hx.service.mobile.model.honor;

import hx.base.core.dao.entity.common.BaseEntity;

/**
 * @ClassName: RankPromontionTrackModel
 * @Description: 职级晋升轨迹返回Model
 * @Author HuoJiaJin
 * @Date 2021/2/6 2:46
 * @Version 1.0
 **/
public class RankPromontionTrackModel extends BaseEntity {

    private String gradeName;//职级名称
    private String startTime;//职级开始时间 yyyy-MM-dd
    private int type;//职级变更类型 1 入职 2 晋升 3 降级 4 平调

    public String getGradeName() {
        return gradeName;
    }

    public void setGradeName(String gradeName) {
        this.gradeName = gradeName;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
