package hx.base.core.dao.repo.jpa.hualife;

import hx.base.core.dao.entity.hualife.MarketingManpower;
import hx.base.core.dao.repo.jpa.common.AbstractJpaRepo;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

/**
 *@ClassName MarketingManpowerRepo
 *@Description 人力清单Repo
 *@Author HuoJiaJin
 *@Date 2020/6/22 0:07
 *@Version 1.0
 **/
public interface MarketingManpowerRepo extends AbstractJpaRepo<MarketingManpower, String> {

    @Query(" from MarketingManpower where agentCode = ?1")
    MarketingManpower findByAgentCode(String agentCode);

    @Query(" from MarketingManpower where agentGrade = ?1 and outworkDate is null")
    List<MarketingManpower> listByAgentGrade(String agentGrade);

    @Query(" from MarketingManpower where agentCode in (?1) and outworkDate is null")
    List<MarketingManpower> listByAgentCodes(List<String> agentCodes);

    @Query(" from MarketingManpower where deptCode1 = ?1 and outworkDate is null")
    List<MarketingManpower> listByDeptCode1(String deptCode1);

    @Query(" from MarketingManpower where deptCode2 = ?1 and outworkDate is null")
    List<MarketingManpower> listByDeptCode2(String deptCode1);

    @Query(" from MarketingManpower where deptCode3 = ?1 and outworkDate is null")
    List<MarketingManpower> listByDeptCode3(String deptCode3);

    @Query(" from MarketingManpower where deptCode4 = ?1 and outworkDate is null")
    List<MarketingManpower> listByDeptCode4(String deptCode4);

    @Query("select count(agentCode) from MarketingManpower where deptCode3 = ?1 and outworkDate is null")
    Integer countByDeptCode3(String deptCode3);

    @Query("select count(agentCode) from MarketingManpower where deptCode4 = ?1 and outworkDate is null")
    Integer countByDeptCode4(String deptCode4);

    @Query("select count(agentCode) from MarketingManpower where deptCode3 = ?1 and employDate >= ?2 and employDate < ?3")
    Integer sectionMonthNew(String deptCode3, LocalDate employStartDate, LocalDate employEndDate);

    @Query("select count(agentCode) from MarketingManpower where deptCode1 = ?1 and employDate >= ?2 and employDate < ?3")
    Integer campMonthNew(String deptCode1, LocalDate employStartDate, LocalDate employEndDate);

    @Query("select count(agentCode) from MarketingManpower where deptCode4 = ?1 and employDate >= ?2 and employDate < ?3")
    Integer groupMonthNew(String deptCode4, LocalDate employStartDate, LocalDate employEndDate);

    @Query("from MarketingManpower where recommAgentCode = ?1 and outworkDate is null")
    List<MarketingManpower> listByRecomm(String recommAgentCode);

    @Query("select count(agentCode) from MarketingManpower where recommAgentCode in (?1) and outworkDate is null")
    Integer countByRecommCodes(List<String> recommAgentCodes);

    @Query("select deptName1 as campName, deptCode1 as campCode, count(agentCode) as num from MarketingManpower where outworkDate is null group by deptName1, deptCode1")
    List<Map<String, String>> groupByCamp();

    @Query("select deptName2 as directorName, deptCode2 as directorCode, count(agentCode) as num from MarketingManpower where outworkDate is null and deptCode1 = ?1 group by deptName2, deptCode2")
    List<Map<String, String>> groupByDirector(String campCode);

    @Query("select deptName3 as sectionName, deptCode3 as sectionCode, count(agentCode) as num from MarketingManpower where outworkDate is null and deptCode2 = ?1 group by deptName3, deptCode3")
    List<Map<String, String>> groupBySection(String directorCode);

    @Query("select deptName4 as groupName, deptCode4 as groupCode, count(agentCode) as num from MarketingManpower where outworkDate is null and deptCode3 = ?1 group by deptName4, deptCode4")
    List<Map<String, String>> groupByGroup(String sectionCode);

    @Query("select count(agentCode) from MarketingManpower where deptCode1 = ?1 and outworkDate is null")
    Integer countByDeptCode1(String deptCode1);

    @Query("select count(agentCode) from MarketingManpower where deptCode1 = ?1 and agentGrade in ?2 and outworkDate is null")
    Integer countByGrades(String campCode, List<String> gradeList);

    @Query("select count(deptCode3) from MarketingManpower where deptCode1 = ?1 group by deptCode3")
    Integer countSection(String campCode);

    @Query("select count(deptCode4) from MarketingManpower where deptCode1 = ?1 group by deptCode4")
    Integer countGroup(String campCode);
}
