package hx.service.mobile.manage.radar;

import hx.service.mobile.manage.model.common.MobileCommonRequest;
import hx.service.mobile.manage.model.radar.*;

/**
 *@ClassName RadarManager
 *@Description 主管经营雷达图相关Manager
 *@Author HuoJiaJin
 *@Date 2020/6/26 23:47
 *@Version 1.0
 **/
public interface RadarManager {

    /**
     * @Name radar
     * @Author HuoJiaJin
     * @Description 主管经营雷达图
     * @Date 2020/6/27
     * @Param [request]
     * @return java.lang.String
     **/
    String radar(RadarRequest request);

    /**
     * @Name getOneselfStar
     * @Author HuoJiaJin
     * @Description 个人星级
     * @Date 2020/6/27
     * @Param [request]
     * @return java.lang.String
     **/
    String getOneselfStar(MobileCommonRequest request);
}
