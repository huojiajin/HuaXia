package hx.service.mobile.web.test;

import hx.base.core.manage.common.CommonAbstract;
import hx.service.mobile.manage.model.test.paper.*;
import hx.service.mobile.manage.test.PaperManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @name: PaperController
 * @description: 试卷相关Controller
 * @author: huojiajin
 * @time: 2020/6/30 17:04
 */
@RestController
@RequestMapping("/test/paper")
public class PaperController extends CommonAbstract {

    @Autowired
    private PaperManager manager;

    @PostMapping("/list")
    public String getList(@RequestBody PaperListRequest request){
        return manager.list(request);
    }

    @PostMapping("/detail")
    public String getDetail(@RequestBody PaperDetailRequest request){
        return manager.detail(request);
    }

    @PostMapping("/submit")
    public String submit(@RequestBody PaperSubmitRequest request){
        return manager.submit(request);
    }

    @PostMapping("/completed/list")
    public String getCompletedList(@RequestBody PaperCompletedListRequest request){
        return manager.getCompletedList(request);
    }

    @PostMapping("/completed/detail")
    public String getCompletedDetail(@RequestBody PaperCompletedDetailRequest request){
        return manager.getCompletedDetail(request);
    }
}
