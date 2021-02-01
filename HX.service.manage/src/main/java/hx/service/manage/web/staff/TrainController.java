package hx.service.manage.web.staff;

import hx.service.manage.model.common.CommonRequest;
import hx.service.manage.manage.staff.TrainManager;
import hx.service.manage.model.staff.train.*;
import hx.service.manage.web.common.MyBaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @name: TrainController
 * @description: 参训人员管理Controller
 * @author: huojiajin
 * @time: 2020/11/12 11:02
 */
@RestController
@RequestMapping("/staff/train")
public class TrainController extends MyBaseController {

    @Autowired
    private TrainManager manager;

    @PostMapping("/query")
    public String query(@RequestBody TrainQueryRequest request){
        return manager.query(request);
    }

    @PostMapping("/add")
    public String add(@RequestBody TrainAddRequest request){
        return manager.add(request);
    }

    @PostMapping("/delete")
    public String delete(@RequestBody TrainIdRequest request){
        return manager.delete(request);
    }

    @PostMapping("/template")
    public String downloadTemplate(@RequestBody CommonRequest request){
        return manager.downloadTemplate(request);
    }

    @PostMapping("/import")
    public String importPeople(@RequestBody TrainImportRequest request){
        return manager.importPeople(request);
    }

    @PostMapping("/people/query")
    public String peopleQuery(@RequestBody TrainPeopleQueryRequest request){
        return manager.peopleQuery(request);
    }

    @PostMapping("/people/export")
    public String peopleExport(@RequestBody TrainPeopleQueryRequest request){
        return manager.exportPeople(request);
    }
}
