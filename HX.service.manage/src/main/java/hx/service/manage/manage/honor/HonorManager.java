package hx.service.manage.manage.honor;

import hx.service.manage.manage.model.CommonRequest;
import hx.service.manage.manage.model.honor.HonorAddRequest;
import hx.service.manage.manage.model.honor.HonorIdRequest;
import hx.service.manage.manage.model.honor.HonorImportRequest;
import hx.service.manage.manage.model.honor.HonorQueryRequest;

/**
 * @ClassName: HonorManager
 * @Description: 荣誉管理Manager
 * @Author HuoJiaJin
 * @Date 2021/2/1 1:23
 * @Version 1.0
 **/
public interface HonorManager{

    /**
     * @Name query
     * @Author HuoJiaJin
     * @Description 荣誉查询
     * @Date 2021/2/1 16:03
     * @Param [request]
     * @Return java.lang.String
     **/
    String query(HonorQueryRequest request);

    /**
     * @Name add
     * @Author HuoJiaJin
     * @Description 添加
     * @Date 2021/2/1 16:03
     * @Param [request]
     * @Return java.lang.String
     **/
    String add(HonorAddRequest request);

    /**
     * @Name delete
     * @Author HuoJiaJin
     * @Description 删除
     * @Date 2021/2/1 16:03
     * @Param [request]
     * @Return java.lang.String
     **/
    String delete(HonorIdRequest request);

    /**
     * @Name downloadTemplate
     * @Author HuoJiaJin
     * @Description 下载模板
     * @Date 2021/2/1 16:04
     * @Param [request]
     * @Return java.lang.String
     **/
    String downloadTemplate(CommonRequest request);

    /**
     * @Name importExcel
     * @Author HuoJiaJin
     * @Description 导入人员
     * @Date 2021/2/1 16:04
     * @Param [request]
     * @Return java.lang.String
     **/
    String importExcel(HonorImportRequest request);

    /**
     * @Name exportPeople
     * @Author HuoJiaJin
     * @Description 导出人员
     * @Date 2021/2/1 16:04
     * @Param [request]
     * @Return java.lang.String
     **/
    String exportPeople(HonorIdRequest request);
}
