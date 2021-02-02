package hx.service.manage.web.quit;

import hx.service.manage.manage.quit.QuitApplyManager;
import hx.service.manage.model.quit.QuitApplyIdRequest;
import hx.service.manage.model.quit.QuitApplyQueryRequest;
import hx.service.manage.web.common.MyBaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: QuitApplyController
 * @Description: 离职申请Controller
 * @Author HuoJiaJin
 * @Date 2021/2/3 0:44
 * @Version 1.0
 **/
@RestController
@RequestMapping("/quit/apply")
public class QuitApplyController extends MyBaseController {

    @Autowired
    private QuitApplyManager manager;

    @PostMapping("/query")
    public String query(@RequestBody QuitApplyQueryRequest request){
        return manager.query(request);
    }

    @PostMapping("/export")
    public String export(@RequestBody QuitApplyIdRequest request){
        return manager.export(request);
    }
}
