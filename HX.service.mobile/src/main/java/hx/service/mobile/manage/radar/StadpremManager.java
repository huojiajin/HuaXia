package hx.service.mobile.manage.radar;

import hx.service.mobile.manage.model.radar.stadprem.GroupStadpremRequest;
import hx.service.mobile.manage.model.radar.stadprem.PersonStadpremDetailRequest;
import hx.service.mobile.manage.model.radar.stadprem.PersonStadpremRequest;
import hx.service.mobile.manage.model.radar.stadprem.SectionStadpremRequest;

/**
 * @ClassName StadpremManager
 * @Description 标保相关Manager
 * @Author HuoJiaJin
 * @Date 2020/6/27 10:42
 * @Version 1.0
 **/
public interface StadpremManager {

    /**
     * @Name getSectionStadprem
     * @Author HuoJiaJin
     * @Description 获取部标保
     * @Date 2020/6/27
     * @Param [request]
     * @return java.lang.String
     **/
    String getSectionStadprem(SectionStadpremRequest request);

    /**
     * @Name getGroupStadprem
     * @Author HuoJiaJin
     * @Description 获取组标保
     * @Date 2020/6/27
     * @Param [request]
     * @return java.lang.String
     **/
    String getGroupStadprem(GroupStadpremRequest request);

    /**
     * @Name getPersonStadprem
     * @Author HuoJiaJin
     * @Description 获取个人标保
     * @Date 2020/6/27
     * @Param [request]
     * @return java.lang.String
     **/
    String getPersonStadprem(PersonStadpremRequest request);

    /**
     * @Name getPersonStadpremDetail
     * @Author HuoJiaJin
     * @Description 获取个人标保详情
     * @Date 2020/6/27
     * @Param [request]
     * @return java.lang.String
     **/
    String getPersonStadpremDetail(PersonStadpremDetailRequest request);
}
