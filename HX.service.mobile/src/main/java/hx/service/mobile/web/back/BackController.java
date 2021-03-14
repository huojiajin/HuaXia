package hx.service.mobile.web.back;

import hx.base.core.manage.common.CommonAbstract;
import hx.service.mobile.manage.back.BackManager;
import hx.service.mobile.model.common.MobileCommonRequest;
import hx.service.mobile.model.back.org.BackDirectorListRequest;
import hx.service.mobile.model.back.org.BackGroupListRequest;
import hx.service.mobile.model.back.org.BackSectionListRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: BackController
 * @Description: 内勤相关Controller
 * @Author HuoJiaJin
 * @Date 2021/3/14 15:01
 * @Version 1.0
 **/
@RestController
@RequestMapping("/back")
public class BackController extends CommonAbstract {

    @Autowired
    private BackManager manager;


    @PostMapping("/camplist")
    public String campList(@RequestBody MobileCommonRequest request){
        return manager.getCampListBack(request);
    }

    @PostMapping("/directorlist")
    public String directorList(@RequestBody BackDirectorListRequest request){
        return manager.getDirectorListBack(request);
    }

    @PostMapping("/sectionlist")
    public String sectionList(@RequestBody BackSectionListRequest request){
        return manager.getSectionListBack(request);
    }

    @PostMapping("/grouplist")
    public String groupList(@RequestBody BackGroupListRequest request){
        return manager.getGroupListBack(request);
    }
}
