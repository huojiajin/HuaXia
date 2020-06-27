package hx.service.mobile.web;

import hx.base.core.manage.common.CommonAbstract;
import hx.service.mobile.manage.index.IndexManager;
import hx.service.mobile.manage.model.common.MobileCommonRequest;
import hx.service.mobile.manage.model.radar.GroupListReqeust;
import hx.service.mobile.manage.model.radar.RadarRequest;
import hx.service.mobile.manage.radar.RadarManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName IndexManager
 * @Description 首页相关Controller
 * @Author HuoJiaJin
 * @Date 2020/6/27 10:32
 * @Version 1.0
 **/
@RestController
@RequestMapping("/index")
public class IndexController extends CommonAbstract {

    @Autowired
    private IndexManager manager;
    @Autowired
    private RadarManager radarManager;

    @PostMapping("/sectionlist")
    public String getSectionList(@RequestBody MobileCommonRequest request){
        return manager.getSectionList(request);
    }

    @PostMapping("/grouplist")
    public String getGroupList(@RequestBody GroupListReqeust request){
        return manager.getGroupList(request);
    }

    @PostMapping("/radar")
    public String radar(@RequestBody RadarRequest request){
        return radarManager.radar(request);
    }
}
