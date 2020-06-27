package hx.service.mobile.manage.radar;

import hx.service.mobile.manage.model.radar.attend.GroupAttendRequest;
import hx.service.mobile.manage.model.radar.attend.PersonAttendRequest;
import hx.service.mobile.manage.model.radar.attend.SectionAttendRequest;

/**
 * @ClassName AttendanceManager
 * @Description 出勤人力Manager
 * @Author HuoJiaJin
 * @Date 2020/6/27 16:46
 * @Version 1.0
 **/
public interface AttendanceManager {

    /**
     * @Name getSectionAttend
     * @Author HuoJiaJin
     * @Description 部出勤人力
     * @Date 2020/6/27
     * @Param [request]
     * @return java.lang.String
     **/
    String getSectionAttend(SectionAttendRequest request);

    /**
     * @Name getGroupAttend
     * @Author HuoJiaJin
     * @Description 组出勤人力
     * @Date 2020/6/27
     * @Param [request]
     * @return java.lang.String
     **/
    String getGroupAttend(GroupAttendRequest request);

    String getAttendDetail(PersonAttendRequest request);
}
