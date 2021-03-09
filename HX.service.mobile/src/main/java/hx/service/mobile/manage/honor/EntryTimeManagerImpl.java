package hx.service.mobile.manage.honor;

import hx.base.core.dao.dict.acl.ErrorType;
import hx.base.core.manage.model.CommonResponse;
import hx.service.mobile.manage.common.AbstractMobileManager;
import hx.service.mobile.model.common.MobileCommonRequest;
import hx.service.mobile.model.honor.EntryTimeResponse;
import hx.service.mobile.model.login.MobileUserModel;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

/**
 * @name: EntryTimeManagerImpl
 * @description: 入职计时ManagerImpl
 * @author: huojiajin
 * @time: 2020/7/7 14:59
 */
@Service
public class EntryTimeManagerImpl extends AbstractMobileManager implements EntryTimeManager {

    @Override
    public String entryTime(MobileCommonRequest request){
        CommonResponse response = new CommonResponse();
        MobileUserModel user = getUser(request.getToken());
        if (user == null) return response.setError(ErrorType.NOLOGIN);
        EntryTimeResponse data = new EntryTimeResponse();
        String employeeDateStr = user.getEmployee_date();
        LocalDate employeeDate = LocalDate.parse(employeeDateStr);
        LocalDate nowDate = LocalDate.now();
        //处理年份
        Long year = ChronoUnit.YEARS.between(employeeDate, nowDate);
        data.setYear(year.intValue());
        employeeDate = employeeDate.plusYears(year);
        //处理月份
        Long month = ChronoUnit.MONTHS.between(employeeDate, nowDate);
        data.setMonth(month.intValue());
        employeeDate = employeeDate.plusMonths(month);
        //处理日期
        Long day = ChronoUnit.DAYS.between(employeeDate, nowDate);
        data.setDay(day.intValue());
        employeeDate = employeeDate.plusDays(day);
        //处理小时
        Long hour = ChronoUnit.HOURS.between(employeeDate.atTime(LocalTime.of(9,0)), LocalDateTime.now());
        data.setHour(hour.intValue() > 0 ? hour.intValue() : 0);

        response.setData(data);
        return response.toJson();
    }
}
