package hx.service.manage.web.test;

import hx.service.manage.manage.model.CommonRequest;
import hx.service.manage.manage.model.test.papers.*;
import hx.service.manage.manage.test.PapersManager;
import hx.service.manage.web.MyBaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName PapersController
 * @Description 试卷管理Controller
 * @Author HuoJiaJin
 * @Date 2020/6/20 16:53
 * @Version 1.0
 **/
@RestController
@RequestMapping("/test/paper")
public class PapersController extends MyBaseController {

    @Autowired
    private PapersManager manager;

    @PostMapping("/query")
    public String query(@RequestBody PapersQueryRequest request){
        return manager.query(request);
    }

    @PostMapping("/add")
    public String add(@RequestBody PapersAddRequest request){
        return manager.add(request);
    }

    @PostMapping("/delete")
    public String delete(@RequestBody PapersIdRequest request){
        return manager.delete(request);
    }

    @PostMapping("/template")
    public String downloadTemplate(@RequestBody CommonRequest request){
        return manager.downloadTemplate(request);
    }

    @PostMapping("/import")
    public String importPapers(@RequestBody PapersImportReqeust request){
        return manager.importPapers(request);
    }

    @PostMapping("/view")
    public String view(@RequestBody PapersViewRequest request){
        return manager.view(request);
    }

    @PostMapping("/push")
    public String push(@RequestBody PapersPushRequest request){
        return manager.push(request);
    }

    @PostMapping("/result")
    public String add(@RequestBody PapersIdRequest request){
        return manager.resultView(request);
    }
}
