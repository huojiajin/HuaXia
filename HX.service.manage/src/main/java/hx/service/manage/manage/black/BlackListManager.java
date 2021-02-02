package hx.service.manage.manage.black;

import hx.service.manage.model.black.BlackListQueryRequest;

/**
 * @ClassName: BlackListManager.java
 * @Description: 黑名单Manager
 * @Author HuoJiaJin
 * @Date 2021/2/3 0:49
 * @Version 1.0
**/
public interface BlackListManager {
    String query(BlackListQueryRequest request);

    String export(BlackListQueryRequest request);
}
