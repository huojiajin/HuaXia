package hx.base.core.dao.repo.jpa;

import hx.base.core.dao.entity.MarketingManpower;
import hx.base.core.dao.repo.jpa.common.AbstractJpaRepo;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

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

    @Query(" from MarketingManpower where agentGrade = ?1 and outworkDate is not null")
    List<MarketingManpower> listByAgentGrade(String agentGrade);

    @Query(" from MarketingManpower where agentCode in (?1) and outworkDate is not null")
    List<MarketingManpower> listByAgentCodes(List<String> agentCodes);

    @Query(" from MarketingManpower where deptCode3 = ?1 and outworkDate is not null")
    List<MarketingManpower> listByDeptCode3(String deptCode3);

    @Query(" from MarketingManpower where deptCode4 = ?1 and outworkDate is not null")
    List<MarketingManpower> listByDeptCode4(String deptCode4);

    @Query("select count(agentCode) from MarketingManpower where deptCode3 = ?1 and outworkDate is not null")
    Integer countByDeptCode3(String deptCode3);

    @Query("select count(agentCode) from MarketingManpower where deptCode4 = ?1 and outworkDate is not null")
    Integer countByDeptCode4(String deptCode4);

    @Query("select count(agentCode) from MarketingManpower where deptCode3 = ?1 and employDate >= ?2 and employDate < ?3")
    Integer sectionMonthNew(String deptCode3, LocalDate employStartDate, LocalDate employEndDate);

    @Query("select count(agentCode) from MarketingManpower where deptCode4 = ?1 and employDate >= ?2 and employDate < ?3")
    Integer groupMonthNew(String deptCode4, LocalDate employStartDate, LocalDate employEndDate);

    @Query("from MarketingManpower where recommAgentCode = ?1 and outworkDate is not null")
    List<MarketingManpower> listByRecomm(String recommAgentCode);

    @Query("select count(agentCode) from MarketingManpower where recommAgentCode in (?1) and outworkDate is not null")
    Integer countByRecommCodes(List<String> recommAgentCodes);
}
