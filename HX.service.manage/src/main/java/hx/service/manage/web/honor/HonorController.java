package hx.service.manage.web.honor;

import hx.service.manage.manage.honor.HonorManager;
import hx.service.manage.manage.model.CommonRequest;
import hx.service.manage.manage.model.honor.HonorAddRequest;
import hx.service.manage.manage.model.honor.HonorIdRequest;
import hx.service.manage.manage.model.honor.HonorImportRequest;
import hx.service.manage.manage.model.honor.HonorQueryRequest;
import hx.service.manage.web.MyBaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: HonorController
 * @Description: 荣誉管理Controller
 * @Author HuoJiaJin
 * @Date 2021/2/1 0:59
 * @Version 1.0
 **/
@RestController
@RequestMapping("/honor")
public class HonorController extends MyBaseController {

    @Autowired
    private HonorManager manager;

    @PostMapping("/query")
    public String query(@RequestBody HonorQueryRequest request){
        return manager.query(request);
    }

    @PostMapping("/add")
    public String add(@RequestBody HonorAddRequest request){
        return manager.add(request);
    }

    @PostMapping("/delete")
    public String delete(@RequestBody HonorIdRequest request){
        return manager.delete(request);
    }

    @PostMapping("/template")
    public String downloadTemplate(@RequestBody CommonRequest request){
        return manager.downloadTemplate(request);
    }

    @PostMapping("/import")
    public String importPeople(@RequestBody HonorImportRequest request){
        return manager.importExcel(request);
    }

    @PostMapping("/people/export")
    public String peopleExport(@RequestBody HonorIdRequest request){
        return manager.exportPeople(request);
    }
}
