package hx.service.mobile.manage.honor;

import hx.service.mobile.model.common.MobileCommonRequest;

/**
 * @ClassName: RankPromontionTrackManager.java
 * @Description: 职级晋升轨迹Manager
 * @Author HuoJiaJin
 * @Date 2021/2/6 1:59
 * @Version 1.0
**/
public interface RankPromontionTrackManager {

    /**
     * @Name track
     * @Author HuoJiaJin
     * @Description 查询职级晋升轨迹
     * @Date 2021/2/6 3:16
     * @Param [request]
     * @Return java.lang.String
     **/
    String track(MobileCommonRequest request);
}
