package hx.service.mobile.web.radar;

import hx.base.core.manage.common.CommonAbstract;
import hx.service.mobile.model.radar.attend.GroupAttendRequest;
import hx.service.mobile.model.radar.attend.PersonAttendRequest;
import hx.service.mobile.model.radar.attend.SectionAttendRequest;
import hx.service.mobile.manage.radar.AttendanceManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName AttendanceController
 * @Description 出勤人力相关Controller
 * @Author HuoJiaJin
 * @Date 2020/6/27 18:03
 * @Version 1.0
 **/
@RestController
@RequestMapping("/index/radar/attend")
public class AttendanceController extends CommonAbstract {

    @Autowired
    private AttendanceManager manager;

    @PostMapping("/section")
    public String getSection(@RequestBody SectionAttendRequest request){
        return manager.getSectionAttend(request);
    }

    @PostMapping("/group")
    public String getGroup(@RequestBody GroupAttendRequest request){
        return manager.getGroupAttend(request);
    }

    @PostMapping("/person")
    public String getPerson(@RequestBody PersonAttendRequest request){
        return manager.getAttendDetail(request);
    }
}
