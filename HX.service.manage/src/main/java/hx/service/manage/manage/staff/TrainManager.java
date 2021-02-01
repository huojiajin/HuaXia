package hx.service.manage.manage.staff;

import hx.service.manage.model.common.CommonRequest;
import hx.service.manage.model.staff.train.*;

/**
 *@ClassName TrainManager
 *@Description 参训人员管理Manager
 *@Author HuoJiaJin
 *@Date 2020/11/12 11:50
 *@Version 1.0
 **/
public interface TrainManager {
    
    /**
     * @Name query
     * @Author HuoJiaJin
     * @Description 分页查询
     * @Date 2020/11/12 14:44
     * @Param [request]
     * @return java.lang.String
     **/
    String query(TrainQueryRequest request);

    /**
     * @Name add
     * @Author HuoJiaJin
     * @Description 添加培训场次
     * @Date 2020/11/12 14:49
     * @Param [request]
     * @return void
     **/
    String add(TrainAddRequest request);

    /**
     * @Name delete
     * @Author HuoJiaJin
     * @Description 删除培训场次
     * @Date 2020/11/12 14:51
     * @Param [request]
     * @return java.lang.String
     **/
    String delete(TrainIdRequest request);

    /**
     * @Name downloadTemplate
     * @Author HuoJiaJin
     * @Description 人员导入模板下载
     * @Date 2020/11/12 14:55
     * @Param [request]
     * @return java.lang.String
     **/
    String downloadTemplate(CommonRequest request);

    /**
     * @Name importStaff
     * @Author HuoJiaJin
     * @Description 导入人员
     * @Date 2020/11/12 15:24
     * @Param [request]
     * @return java.lang.String
     **/
    String importPeople(TrainImportRequest request);

    /**
     * @Name peopleQuery
     * @Author HuoJiaJin
     * @Description 人员查看
     * @Date 2020/11/12 15:38
     * @Param [request]
     * @return java.lang.String
     **/
    String peopleQuery(TrainPeopleQueryRequest request);

    /**
     * @Name exportPeople
     * @Author HuoJiaJin
     * @Description 导出人员详情
     * @Date 2020/11/12 15:54
     * @Param [request]
     * @return java.lang.String
     **/
    String exportPeople(TrainPeopleQueryRequest request);
}
