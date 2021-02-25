package hx.service.mobile.manage.structure;

import hx.service.mobile.model.common.MobileCommonRequest;
import hx.service.mobile.model.structure.StructureAnalysisRequest;
import hx.service.mobile.model.structure.StructurePersonDetailRequest;
import hx.service.mobile.model.structure.StructurePersonListRequest;
import hx.service.mobile.model.structure.internal.StructureDirectorListRequest;
import hx.service.mobile.model.structure.internal.StructureGroupListRequest;
import hx.service.mobile.model.structure.internal.StructureSectionListRequest;

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
    String structureAnalysis(StructureAnalysisRequest request);

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

    /**
     * @Name getCampList
     * @Author HuoJiaJin
     * @Description 获取相关营业区代码
     * @Date 2021/2/25 17:31
     * @Param [request]
     * @Return java.lang.String
     **/
    String getCampList(MobileCommonRequest request);

    /**
     * @Name getDirectorList
     * @Author HuoJiaJin
     * @Description 获取相关总监区代码
     * @Date 2021/2/25 17:31
     * @Param [request]
     * @Return java.lang.String
     **/
    String getDirectorList(StructureDirectorListRequest request);

    /**
     * @Name getSectionList
     * @Author HuoJiaJin
     * @Description 获取相关部代码
     * @Date 2021/2/25 17:31
     * @Param [request]
     * @Return java.lang.String
     **/
    String getSectionList(StructureSectionListRequest request);

    /**
     * @Name getGroupList
     * @Author HuoJiaJin
     * @Description 获取相关组代码
     * @Date 2021/2/25 17:31
     * @Param [request]
     * @Return java.lang.String
     **/
    String getGroupList(StructureGroupListRequest request);
}
