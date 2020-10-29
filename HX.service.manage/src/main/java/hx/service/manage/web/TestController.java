package hx.service.manage.web;

import hx.base.core.dao.entity.MarketingManpower;
import hx.base.core.dao.repo.jpa.MarketingManpowerRepo;
import net.spy.memcached.MemcachedClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.net.InetAddress;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * @name: TestController
 * @description: 测试
 * @author: huojiajin
 * @time: 2020/5/25 17:50
 */
@RestController
public class TestController {

    @Value("${spring.application.name}")
    private String name;

    @Autowired
    private MemcachedClient memcacheClient;
    @Autowired
    private MarketingManpowerRepo manpowerRepo;

    @RequestMapping(value = "/health",method = {RequestMethod.GET,RequestMethod.HEAD})
    public String heathTest(){
        String adress="unknow";
        try {
            adress= InetAddress.getLocalHost()+"";
            List<MarketingManpower> all = manpowerRepo.findAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "I'm "+name+",the time is "
                + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-DD HH:mm:ss"))+", and my adress is " + adress;
    }
}
