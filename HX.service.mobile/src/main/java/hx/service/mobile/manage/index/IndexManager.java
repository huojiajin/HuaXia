package hx.service.mobile.manage.index;

import hx.service.mobile.manage.model.common.MobileCommonRequest;
import hx.service.mobile.manage.model.radar.GroupListReqeust;

/**
 * @ClassName IndexManager
 * @Description 首页相关Manager
 * @Author HuoJiaJin
 * @Date 2020/6/27 10:30
 * @Version 1.0
 **/
public interface IndexManager {

    /**
     * @Name getSectionList
     * @Author HuoJiaJin
     * @Description 获取部列表
     * @Date 2020/6/27
     * @Param [request]
     * @return java.lang.String
     **/
    String getSectionList(MobileCommonRequest request);

    /**
     * @Name getGroupList
     * @Author HuoJiaJin
     * @Description 获取组列表
     * @Date 2020/6/27
     * @Param [request]
     * @return java.lang.String
     **/
    String getGroupList(GroupListReqeust request);
}
