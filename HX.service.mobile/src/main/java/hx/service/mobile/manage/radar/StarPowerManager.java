package hx.service.mobile.manage.radar;

import hx.service.mobile.manage.model.radar.star.StarPowerDetailRequest;
import hx.service.mobile.manage.model.radar.star.StarPowerRequest;

/**
 * @ClassName StarPowerManager
 * @Description 星级人力Manager
 * @Author HuoJiaJin
 * @Date 2020/6/27 15:26
 * @Version 1.0
 **/
public interface StarPowerManager {

    /**
     * @Name getStarList
     * @Author HuoJiaJin
     * @Description 获取星级人力列表
     * @Date 2020/6/27
     * @Param [request]
     * @return java.lang.String
     **/
    String getStarList(StarPowerRequest request);

    /**
     * @Name getStarDetail
     * @Author HuoJiaJin
     * @Description 获取星级人力详情
     * @Date 2020/6/27
     * @Param [request]
     * @return java.lang.String
     **/
    String getStarDetail(StarPowerDetailRequest request);
}
