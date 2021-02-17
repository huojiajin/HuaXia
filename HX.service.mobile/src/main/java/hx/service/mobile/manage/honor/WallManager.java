package hx.service.mobile.manage.honor;

import hx.service.mobile.model.honor.WallQueryRequest;

/**
 * @ClassName: WallManager
 * @Description: 荣誉墙Manager
 * @Author HuoJiaJin
 * @Date 2021/2/6 3:18
 * @Version 1.0
 **/
public interface WallManager {
    
    /**
     * @Name query
     * @Author HuoJiaJin
     * @Description 查询
     * @Date 2021/2/6 3:31
     * @Param [request]
     * @Return java.lang.String
     **/
    String query(WallQueryRequest request);
}
