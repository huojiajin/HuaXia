package hx.service.mobile.web;

import hx.base.core.manage.common.CommonAbstract;
import hx.service.mobile.manage.honor.EntryTimeManager;
import hx.service.mobile.manage.model.common.MobileCommonRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @name: HonorController
 * @description: 荣誉墙相关Controller
 * @author: huojiajin
 * @time: 2020/7/7 15:09
 */
@RestController
@RequestMapping("/honor")
public class HonorController extends CommonAbstract {

    @Autowired
    private EntryTimeManager entryTimeManager;

    @PostMapping("entrytime")
    public String entryTime(@RequestBody MobileCommonRequest request){
        return entryTimeManager.entryTime(request);
    }
}
