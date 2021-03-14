package hx.service.mobile.manage.back;

import hx.service.mobile.model.common.MobileCommonRequest;
import hx.service.mobile.model.back.org.BackDirectorListRequest;
import hx.service.mobile.model.back.org.BackGroupListRequest;
import hx.service.mobile.model.back.org.BackSectionListRequest;

/**
 *@ClassName BackManager.java
 *@Description 内情相关Manager
 *@Author HuoJiaJin
 *@Date 2021/3/14 15:02
 *@Version 1.0
 **/
public interface BackManager {

    /**
     * @Name getCampListBack
     * @Author HuoJiaJin
     * @Description 后勤-获取相关营服
     * @Date 2021/3/13 15:51
     * @Param [request]
     * @Return java.lang.String
     **/
    String getCampListBack(MobileCommonRequest request);

    /**
     * @Name getDirectorListBack
     * @Author HuoJiaJin
     * @Description 后勤-获取相关总监区
     * @Date 2021/3/13 15:51
     * @Param [request]
     * @Return java.lang.String
     **/
    String getDirectorListBack(BackDirectorListRequest request);

    /**
     * @Name getSectionListBack
     * @Author HuoJiaJin
     * @Description 后勤-获取相关部
     * @Date 2021/3/13 15:51
     * @Param [request]
     * @Return java.lang.String
     **/
    String getSectionListBack(BackSectionListRequest request);

    /**
     * @Name getGroupListBack
     * @Author HuoJiaJin
     * @Description 后勤-获取相关组
     * @Date 2021/3/13 15:52
     * @Param [request]
     * @Return java.lang.String
     **/
    String getGroupListBack(BackGroupListRequest request);
}
