package hx.service.manage.manage.quit;

import hx.service.manage.model.common.CommonPageRequest;
import hx.service.manage.model.quit.QuitAssignEditRequest;

/**
 * @ClassName: QuitAssignManager
 * @Description: 离职人员指派Manager
 * @Author HuoJiaJin
 * @Date 2021/2/1 23:31
 * @Version 1.0
**/
public interface QuitAssignManager {

    /**
     * @Name query
     * @Author HuoJiaJin
     * @Description 查询
     * @Date 2021/2/1 23:53
     * @Param [request]
     * @Return java.lang.String
     **/
    String query(CommonPageRequest request);

    /**
     * @Name edit
     * @Author HuoJiaJin
     * @Description 修改
     * @Date 2021/2/1 23:53
     * @Param [request]
     * @Return java.lang.String
     **/
    String edit(QuitAssignEditRequest request);
}
