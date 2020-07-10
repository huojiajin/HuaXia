package hx.service.mobile.manage.structure;

import hx.service.mobile.manage.model.common.MobileCommonRequest;
import hx.service.mobile.manage.model.structure.SructureAnalysisRequest;
import hx.service.mobile.manage.model.structure.StructurePersonDetailRequest;
import hx.service.mobile.manage.model.structure.StructurePersonListRequest;

/**
 *@ClassName StructureManager
 *@Description 组织架构相关Manager
 *@Author HuoJiaJin
 *@Date 2020/7/7 1:15
 *@Version 1.0
 **/
public interface StructureManager {

    /**
     * @Name getOrgList
     * @Author HuoJiaJin
     * @Description 获取组织列表
     * @Date 2020/7/11 0:47
     * @Param [request]
     * @return java.lang.String
     **/
    String getOrgList(MobileCommonRequest request);

    /**
     * @Name structureAnalysis
     * @Author HuoJiaJin
     * @Description 组织机构分析
     * @Date 2020/7/11 0:47
     * @Param [request]
     * @return java.lang.String
     **/
    String structureAnalysis(SructureAnalysisRequest request);

    /**
     * @Name getPersonList
     * @Author HuoJiaJin
     * @Description 获取组人员列表
     * @Date 2020/7/11 0:47
     * @Param [request]
     * @return java.lang.String
     **/
    String getPersonList(StructurePersonListRequest request);

    /**
     * @Name getPersonDetail
     * @Author HuoJiaJin
     * @Description 获取人员详情
     * @Date 2020/7/11 0:48
     * @Param [request]
     * @return java.lang.String
     **/
    String getPersonDetail(StructurePersonDetailRequest request);
}
