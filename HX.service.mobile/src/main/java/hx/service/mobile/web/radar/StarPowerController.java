package hx.service.mobile.web.radar;

import hx.base.core.manage.common.CommonAbstract;
import hx.service.mobile.model.radar.star.StarPowerDetailRequest;
import hx.service.mobile.model.radar.star.StarPowerRequest;
import hx.service.mobile.manage.radar.StarPowerManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName StarPowerController
 * @Description 星级人力
 * @Author HuoJiaJin
 * @Date 2020/6/27 16:41
 * @Version 1.0
 **/
@RestController
@RequestMapping("/index/radar/star")
public class StarPowerController extends CommonAbstract {

    @Autowired
    private StarPowerManager manager;

    @PostMapping("/list")
    public String getStarPowerList(@RequestBody StarPowerRequest request){
        return manager.getStarList(request);
    }

    @PostMapping("/detail")
    public String getStarPowerDetail(@RequestBody StarPowerDetailRequest request){
        return manager.getStarDetail(request);
    }
}
