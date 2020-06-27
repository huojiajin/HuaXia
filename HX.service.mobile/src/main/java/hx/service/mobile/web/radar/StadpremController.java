package hx.service.mobile.web.radar;

import hx.base.core.manage.common.CommonAbstract;
import hx.service.mobile.manage.model.radar.stadprem.GroupStadpremRequest;
import hx.service.mobile.manage.model.radar.stadprem.PersonStadpremDetailRequest;
import hx.service.mobile.manage.model.radar.stadprem.PersonStadpremRequest;
import hx.service.mobile.manage.model.radar.stadprem.SectionStadpremRequest;
import hx.service.mobile.manage.radar.StadpremManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName RadarController
 * @Description 主管经营雷达图
 * @Author HuoJiaJin
 * @Date 2020/6/27 10:29
 * @Version 1.0
 **/
@RestController
@RequestMapping("/index/radar/stadprem")
public class StadpremController extends CommonAbstract {

    @Autowired
    private StadpremManager manager;
    
    @PostMapping("/section")
    public String sectionStadprem(@RequestBody SectionStadpremRequest request){
        return manager.getSectionStadprem(request);
    }

    @PostMapping("/group")
    public String groupStadprem(@RequestBody GroupStadpremRequest request){
        return manager.getGroupStadprem(request);
    }

    @PostMapping("/person")
    public String personStadprem(@RequestBody PersonStadpremRequest request){
        return manager.getPersonStadprem(request);
    }

    @PostMapping("/person/detail")
    public String personStadprem(@RequestBody PersonStadpremDetailRequest request){
        return manager.getPersonStadpremDetail(request);
    }
}
