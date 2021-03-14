package hx.service.mobile.web.back;

import hx.base.core.manage.common.CommonAbstract;
import hx.service.mobile.manage.back.ManpowerAPIManager;
import hx.service.mobile.model.back.kpi.ManpowerAPIRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: ManpowerAPIController
 * @Description: 人力APIController
 * @Author HuoJiaJin
 * @Date 2021/3/13 15:35
 * @Version 1.0
 **/
@RestController
@RequestMapping("/manpower")
public class ManpowerAPIController extends CommonAbstract {

    @Autowired
    private ManpowerAPIManager manager;

    @PostMapping("/query")
    public String query(@RequestBody ManpowerAPIRequest request){
        return manager.api(request);
    }
}
