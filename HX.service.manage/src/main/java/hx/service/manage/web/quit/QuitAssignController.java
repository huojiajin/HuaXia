package hx.service.manage.web.quit;

import hx.service.manage.manage.quit.QuitAssignManager;
import hx.service.manage.model.common.CommonPageRequest;
import hx.service.manage.model.quit.QuitAssignEditRequest;
import hx.service.manage.web.common.MyBaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: QuitAssignController
 * @Description: 离职人员指派Controller
 * @Author HuoJiaJin
 * @Date 2021/2/1 23:54
 * @Version 1.0
 **/
@RestController
@RequestMapping("/quit/assign")
public class QuitAssignController extends MyBaseController {

    @Autowired
    private QuitAssignManager manager;

    @PostMapping("/query")
    public String query(@RequestBody CommonPageRequest request){
        return manager.query(request);
    }

    @PostMapping("/edit")
    public String edit(@RequestBody QuitAssignEditRequest request){
        return manager.edit(request);
    }
}
