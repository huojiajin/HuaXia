package hx.base.core.dao.repo.jpa.salary;

import hx.base.core.dao.dict.salary.PrizeType;
import hx.base.core.dao.entity.salary.SalaryAlert;
import hx.base.core.dao.repo.jpa.common.AbstractJpaRepo;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

/**
 * @ClassName: SalaryAlertRepo.java
 * @Description: 核心薪资项目预警表Repo
 * @Author HuoJiaJin
 * @Date 2021/2/21 23:39
 * @Version 1.0
**/
public interface SalaryAlertRepo extends AbstractJpaRepo<SalaryAlert, String> {

    @Query(" from SalaryAlert where employeeCode = ?1 and type = ?2 and month >= ?3 and month < ?4 and fullPayment = false")
    List<SalaryAlert> listNeedComplete(String employeeCode, PrizeType type, LocalDate startDate, LocalDate endDate);

    @Modifying
    @Transactional
    @Query("update SalaryAlert set fullPayment = true where id = ?1")
    int updateComplete(String id);
}
