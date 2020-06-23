package hx.service.manage.manage.common;

import hx.service.manage.dao.entity.acl.SystemInfo;
import hx.service.manage.dao.entity.acl.User;
import hx.service.manage.dao.repo.jpa.acl.SystemInfoRepo;
import hx.service.manage.manage.MyMecachedPrefix;
import hx.service.manage.manage.tools.JsonTools;
import net.spy.memcached.MemcachedClient;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
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

    protected void addSysLog(String info, String token, String eigenValue) {
        String userStr = (String)memcachedClient.get(MyMecachedPrefix.loginTokenPrefix + token);
        User user = null;
        try {
            user = JsonTools.json2Object(userStr, User.class);
        } catch (IOException e) {
            logger.error("", e);
            return;
        }
        SystemInfo systemInfo = new SystemInfo();
        systemInfo.setInsertTime(LocalDateTime.now());
        systemInfo.setUserId(user.getId());
        systemInfo.setInfo(info);
        systemInfo.setEigenValue(eigenValue);
        systemInfoRepo.persist(systemInfo);
    }

    protected String inputStreamToBase64Str(InputStream is) throws IOException {
        byte[] data = null;
        try {
            ByteArrayOutputStream swapStream = new ByteArrayOutputStream();
            byte[] buff = new byte[100];
            int rc = 0;
            while ((rc = is.read(buff, 0, 100)) > 0) {
                swapStream.write(buff, 0, rc);
            }
            data = swapStream.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    logger.error("", e);
                    throw e;
                }
            }
        }
        return new String(Base64.encodeBase64(data));
    }

    public static void closeQuietly(Closeable closeable){
        if(closeable != null) {
            try {
                closeable.close();
            } catch (IOException e) {

            }
        }
    }
}
