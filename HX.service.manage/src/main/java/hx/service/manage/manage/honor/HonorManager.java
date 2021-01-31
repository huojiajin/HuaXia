package hx.service.manage.manage.honor;

import hx.service.manage.manage.model.honor.HonorAddRequest;
import hx.service.manage.manage.model.honor.HonorQueryRequest;

/**
 * @ClassName: HonorManager
 * @Description: 荣誉管理Manager
 * @Author HuoJiaJin
 * @Date 2021/2/1 1:23
 * @Version 1.0
 **/
public interface HonorManager{
    String query(HonorQueryRequest request);

    String add(HonorAddRequest request);
}
