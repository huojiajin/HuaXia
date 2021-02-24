package hx.service.mobile.web.team;

import hx.base.core.manage.common.CommonAbstract;
import hx.service.mobile.manage.team.TeamInfoManager;
import hx.service.mobile.model.common.MobileCommonRequest;
import hx.service.mobile.model.team.TeamInfoGroupListReqeust;
import hx.service.mobile.model.team.TeamInfoQueryRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: TeamInfoController
 * @Description: 团队信息相关Controller
 * @Author HuoJiaJin
 * @Date 2021/2/25 2:27
 * @Version 1.0
 **/
@RestController
@RequestMapping("/teaminfo")
public class TeamInfoController extends CommonAbstract {

    @Autowired
    private TeamInfoManager manager;

    @PostMapping("/sectionlist")
    public String sectionList(@RequestBody MobileCommonRequest request){
        return manager.getSectionList(request);
    }

    @PostMapping("/grouplist")
    public String groupList(@RequestBody TeamInfoGroupListReqeust request){
        return manager.getGroupList(request);
    }

    @PostMapping("/query")
    public String query(@RequestBody TeamInfoQueryRequest request){
        return manager.query(request);
    }
}
