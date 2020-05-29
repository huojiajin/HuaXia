package hx.service.manage.manage.common;

import hx.service.manage.dao.entity.SystemInfo;
import hx.service.manage.dao.entity.User;
import hx.service.manage.dao.repo.jpa.SystemInfoRepo;
import hx.service.manage.manage.MyMecachedPrefix;
import net.spy.memcached.MemcachedClient;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

/**
 * @name: AbstractManager
 * @description: 通用类Manager
 * @author: huojiajin
 * @time: 2020/5/25 14:53
 */
public abstract class AbstractManager extends CommonAbstract {

    @Autowired
    private SystemInfoRepo systemInfoRepo;
    @Autowired
    private MemcachedClient memcachedClient;

    protected void addSysLog(String info, String token){
        User user = (User)memcachedClient.get(MyMecachedPrefix.loginTokenPrefix + token);
        SystemInfo systemInfo = new SystemInfo();
        systemInfo.setInsertTime(LocalDateTime.now());
        systemInfo.setUserId(user.getId());
        systemInfo.setInfo(info);
        systemInfoRepo.persist(systemInfo);
    }

}