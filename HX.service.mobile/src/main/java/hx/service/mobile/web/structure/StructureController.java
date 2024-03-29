package hx.service.mobile.web.structure;

import hx.base.core.manage.common.CommonAbstract;
import hx.service.mobile.manage.structure.StructureManager;
import hx.service.mobile.model.common.MobileCommonRequest;
import hx.service.mobile.model.structure.StructureAnalysisRequest;
import hx.service.mobile.model.structure.StructurePersonDetailRequest;
import hx.service.mobile.model.structure.StructurePersonListRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @name: StructureController
 * @description: 组织架构分析Controller
 * @author: huojiajin
 * @time: 2020/7/11 0:48
 */
@RestController
@RequestMapping("/organization")
public class StructureController extends CommonAbstract {

    @Autowired
    private StructureManager manager;

    @PostMapping("/orglist")
    public String orgList(@RequestBody MobileCommonRequest request){
        return manager.getOrgList(request);
    }

    @PostMapping("/structure")
    public String analysis(@RequestBody StructureAnalysisRequest request){
        return manager.structureAnalysis(request);
    }

    @PostMapping("/structure/list")
    public String getPersonList(@RequestBody StructurePersonListRequest request){
        return manager.getPersonList(request);
    }

    @PostMapping("/structure/detail")
    public String getPersonDetail(@RequestBody StructurePersonDetailRequest request){
        return manager.getPersonDetail(request);
    }
}
