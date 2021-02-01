package hx.base.core.dao.repo.jpa.hualife;

import hx.base.core.dao.entity.hualife.Attendance;
import hx.base.core.dao.repo.jpa.common.AbstractJpaRepo;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

/**
 * @ClassName AttendanceRepo
 * @Description 出勤清单Repo
 * @Author HuoJiaJin
 * @Date 2020/6/27 5:05
 * @Version 1.0
 **/
public interface AttendanceRepo extends AbstractJpaRepo<Attendance, String> {

    @Query("from Attendance where staffCode in (?1) and attenDate >= ?2 and attenDate < ?3")
    List<Attendance> listByAgentCodes(List<String> agentCodes, LocalDate attenDateStart, LocalDate attenDateEnd);

    @Query("from Attendance where staffCode = ?1 and attenDate >= ?2 and attenDate < ?3")
    List<Attendance> listByAgentCode(String agentCode, LocalDate attenDateStart, LocalDate attenDateEnd);

    @Query("select count(1) from Attendance where staffCode = ?1 and attenDate >= ?2 and attenDate < ?3")
    Long countByAgentCode(String agentCode, LocalDate attenDateStart, LocalDate attenDateEnd);
}
