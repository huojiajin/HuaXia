package hx.service.manage.manage.acl;

import hx.service.manage.dao.repo.jpa.MobileRoleResourceRepo;
import hx.service.manage.manage.common.AbstractManager;
import hx.service.manage.manage.model.CommonRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @name: MobileResourceManagerImpl
 * @description: 移动端资源配置ManagerImpl
 * @author: huojiajin
 * @time: 2020/6/18 16:20
 */
@Service
public class MobileResourceManagerImpl extends AbstractManager implements MobileResourceManager{

    @Autowired
    private MobileRoleResourceRepo resourceRepo;

    @Override
    public String listType(CommonRequest request){
        return null;
    }
}
