package hx.service.mobile.manage.person.team;

import hx.service.mobile.model.common.MobileCommonRequest;
import hx.service.mobile.model.person.team.TeamInfoGroupListReqeust;
import hx.service.mobile.model.person.team.TeamInfoQueryRequest;

/**
 * @ClassName: TeamInfoManager.java
 * @Description: 团队信息相关Manager
 * @Author HuoJiaJin
 * @Date 2021/2/24 23:41
 * @Version 1.0
**/
public interface TeamInfoManager {
    
    /**
     * @Name getSectionList
     * @Author HuoJiaJin
     * @Description 查询相关部信息
     * @Date 2021/2/25 2:26
     * @Param [request]
     * @Return java.lang.String
     **/
    String getSectionList(MobileCommonRequest request);

    /**
     * @Name getGroupList
     * @Author HuoJiaJin
     * @Description 查询相关组信息
     * @Date 2021/2/25 2:26
     * @Param [request]
     * @Return java.lang.String
     **/
    String getGroupList(TeamInfoGroupListReqeust request);

    /**
     * @Name query
     * @Author HuoJiaJin
     * @Description 查询团队信息
     * @Date 2021/2/25 2:26
     * @Param [request]
     * @Return java.lang.String
     **/
    String query(TeamInfoQueryRequest request);
}
