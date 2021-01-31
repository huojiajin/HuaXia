package hx.service.manage.web.staff;

import hx.service.manage.manage.model.staff.situation.SituationQueryRequest;
import hx.service.manage.manage.staff.SituationManager;
import hx.service.manage.web.MyBaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: SituationController
 * @Description: 人员情况统计Controller
 * @Author huojiajin
 * @Date 2021/1/29 0:35
 * @Version 1.0
 **/
@RestController
@RequestMapping("/staff/situation")
public class SituationController extends MyBaseController {

    @Autowired
    private SituationManager manager;

    @PostMapping("/query")
    public String query(@RequestBody SituationQueryRequest request){
        return manager.query(request);
    }

    @PostMapping("/export")
    public String export(@RequestBody SituationQueryRequest request){
        return manager.export(request);
    }
}
