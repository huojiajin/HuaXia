package hx.service.manage.manage.quit;

import hx.service.manage.model.quit.QuitApplyIdRequest;
import hx.service.manage.model.quit.QuitApplyQueryRequest;

/**
 * @ClassName: QuitApplyManager
 * @Description: 离职申请Manager
 * @Author HuoJiaJin
 * @Date 2021/2/2 23:23
 * @Version 1.0
 **/
public interface QuitApplyManager {

    /**
     * @Name query
     * @Author HuoJiaJin
     * @Description 查询
     * @Date 2021/2/3 0:08
     * @Param [request]
     * @Return java.lang.String
     **/
    String query(QuitApplyQueryRequest request);

    /**
     * @Name export
     * @Author HuoJiaJin
     * @Description 导出申请表
     * @Date 2021/2/3 0:08
     * @Param [request]
     * @Return java.lang.String
     **/
    String export(QuitApplyIdRequest request);
}
