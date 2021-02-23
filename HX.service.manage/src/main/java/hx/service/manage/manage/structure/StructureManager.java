package hx.service.manage.manage.structure;

import hx.service.manage.model.common.CommonPageRequest;
import hx.service.manage.model.structure.StructureEditRequest;

/**
 * @ClassName: StructureManager
 * @Description: 组织架构Manager
 * @Author HuoJiaJin
 * @Date 2021/2/24 1:15
 * @Version 1.0
 **/
public interface StructureManager {

    /**
     * @Name query
     * @Author HuoJiaJin
     * @Description 查询组织架构
     * @Date 2021/2/24 1:32
     * @Param [request]
     * @Return java.lang.String
     **/
    String query(CommonPageRequest request);

    /**
     * @Name edit
     * @Author HuoJiaJin
     * @Description 修改组织架构
     * @Date 2021/2/24 1:32
     * @Param [request]
     * @Return java.lang.String
     **/
    String edit(StructureEditRequest request);
}
