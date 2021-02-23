package hx.service.manage.web.structure;

import hx.service.manage.manage.structure.StructureManager;
import hx.service.manage.model.common.CommonPageRequest;
import hx.service.manage.model.structure.StructureEditRequest;
import hx.service.manage.web.common.MyBaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: StructureController
 * @Description: 组织架构相关Controller
 * @Author HuoJiaJin
 * @Date 2021/2/24 1:33
 * @Version 1.0
 **/
@RestController
@RequestMapping("/organize")
public class StructureController extends MyBaseController {

    @Autowired
    private StructureManager manager;

    @PostMapping("/query")
    public String query(@RequestBody CommonPageRequest request){
        return manager.query(request);
    }

    @PostMapping("/edit")
    public String edit(@RequestBody StructureEditRequest request){
        return manager.edit(request);
    }
}
