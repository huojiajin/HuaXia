package hx.service.manage.config;

import hx.service.manage.manage.common.AbstractManager;
import net.spy.memcached.MemcachedClient;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.net.InetSocketAddress;

/**
 * @name: MemcacheClient
 * @description: memcache配置文件
 * @author: huojiajin
 * @time: 2020/5/25 15:10
 */
@Configuration
@ConfigurationProperties(prefix = "memcache")
public class MemcacheConfig extends AbstractManager {

    private String ip;
    private int port;

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    @Bean
    public MemcachedClient getClient() {
        MemcachedClient memcachedClient = null;
        try {
            memcachedClient  = new MemcachedClient(new InetSocketAddress(ip, port));
        } catch (IOException e) {
            logger.error("", e);
            e.printStackTrace();
        }
        return memcachedClient;
    }
}
