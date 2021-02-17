package hx.service.mobile.web.honor;

import hx.base.core.manage.common.CommonAbstract;
import hx.service.mobile.manage.honor.EntryTimeManager;
import hx.service.mobile.manage.honor.RankPromontionTrackManager;
import hx.service.mobile.manage.honor.WallManager;
import hx.service.mobile.model.common.MobileCommonRequest;
import hx.service.mobile.model.honor.WallQueryRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @name: HonorController
 * @description: 荣誉相关Controller
 * @author: huojiajin
 * @time: 2020/7/7 15:09
 */
@RestController
@RequestMapping("/honor")
public class HonorController extends CommonAbstract {

    @Autowired
    private EntryTimeManager entryTimeManager;
    @Autowired
    private RankPromontionTrackManager trackManager;
    @Autowired
    private WallManager wallManager;

    @PostMapping("/entrytime")
    public String entryTime(@RequestBody MobileCommonRequest request){
        return entryTimeManager.entryTime(request);
    }

    @PostMapping("/track")
    public String track(@RequestBody MobileCommonRequest request){
        return trackManager.track(request);
    }

    @PostMapping("/wall/query")
    public String wallQuery(@RequestBody WallQueryRequest request){
        return wallManager.query(request);
    }
}
