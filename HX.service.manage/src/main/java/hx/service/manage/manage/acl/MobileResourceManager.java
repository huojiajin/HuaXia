package hx.service.manage.manage.acl;

import hx.service.manage.manage.model.CommonRequest;
import hx.service.manage.manage.model.acl.mobile.MobileResourceConfigRequest;
import hx.service.manage.manage.model.acl.mobile.MobileResourceQueryRequest;

/**
 * @name: MobileResourceManager
 * @description: 移动端资源配置Manager
 * @author: huojiajin
 * @time: 2020/6/18 16:19
 */
public interface MobileResourceManager {

    /**
     * @Name listType
     * @Author HuoJiaJin
     * @Description 查询层级列表
     * @Date 2020/6/20
     * @Param [request]
     * @return java.lang.String
     **/
    String listType(CommonRequest request);

    /**
     * @Name resourceQuery
     * @Author HuoJiaJin
     * @Description 查询层级当前权限
     * @Date 2020/6/20
     * @Param [request]
     * @return java.lang.String
     **/
    String resourceQuery(MobileResourceQueryRequest request);

    /**
     * @Name resourceConfig
     * @Author HuoJiaJin
     * @Description 资源配置
     * @Date 2020/6/20
     * @Param [request]
     * @return java.lang.String
     **/
    String resourceConfig(MobileResourceConfigRequest request);
}
