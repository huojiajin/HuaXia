package hx.base.core.dao.repo.jpa;

import hx.base.core.dao.entity.Business;
import hx.base.core.dao.repo.jpa.common.AbstractJpaRepo;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

/**
 *@ClassName BusinessRepo
 *@Description 业务清单表Repo
 *@Author HuoJiaJin
 *@Date 2020/6/27 2:32
 *@Version 1.0
 **/
public interface BusinessRepo extends AbstractJpaRepo<Business, String> {

    @Query("select sum(writtenStadPrem) from Business where deptCode3 = ?1 and issueDate >= ?2 and issueDate < ?3")
    Double sumByDeptCode3(String deptCode3, LocalDate issueDateStart, LocalDate issueDateEnd);

    @Query("select sum(writtenStadPrem) from Business where deptCode4 = ?1 and issueDate >= ?2 and issueDate < ?3")
    Double sumByDeptCode4(String deptCode4, LocalDate issueDateStart, LocalDate issueDateEnd);

    @Query("select sum(writtenStadPrem) from Business where agentCode = ?1 and issueDate >= ?2 and issueDate < ?3")
    Double sumByAgentCode(String agentCode, LocalDate issueDateStart, LocalDate issueDateEnd);

    @Query("from Business where deptCode3 = ?1 and issueDate >= ?2 and issueDate < ?3")
    List<Business> listByDeptCode3(String deptCode3, LocalDate issueDateStart, LocalDate issueDateEnd);

    @Query("from Business where deptCode4 = ?1 and issueDate >= ?2 and issueDate < ?3")
    List<Business> listByDeptCode4(String deptCode4, LocalDate issueDateStart, LocalDate issueDateEnd);

    @Query("from Business where agentCode = ?1 and issueDate >= ?2 and issueDate < ?3")
    List<Business> listByAgentCode(String agentCode, LocalDate issueDateStart, LocalDate issueDateEnd);
}
