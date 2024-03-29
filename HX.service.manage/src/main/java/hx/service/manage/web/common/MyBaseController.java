package hx.service.manage.web.common;

import hx.base.core.dao.entity.acl.SystemInfo;
import hx.base.core.dao.repo.jpa.acl.SystemInfoRepo;
import hx.base.core.manage.common.CommonAbstract;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

/**
 * @name: MyBaseController
 * @description: 控制层基础类
 * @author: huojiajin
 * @time: 2020/5/27 10:47
 */
public class MyBaseController extends CommonAbstract {

    @Autowired
    private SystemInfoRepo systemInfoRepo;

    protected void addSysLog(String info, String userId){
        SystemInfo systemInfo = new SystemInfo();
        systemInfo.setInsertTime(LocalDateTime.now());
        systemInfo.setUserId(userId);
        systemInfo.setInfo(info);
        systemInfoRepo.persist(systemInfo);
    }

}
