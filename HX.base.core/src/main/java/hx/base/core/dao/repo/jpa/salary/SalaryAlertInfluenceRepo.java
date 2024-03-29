package hx.base.core.dao.repo.jpa.salary;

import hx.base.core.dao.entity.salary.SalaryAlertInfluence;
import hx.base.core.dao.repo.jpa.common.AbstractJpaRepo;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @ClassName: SalaryAlertRepo.java
 * @Description: 核心薪资项目影响因素表Repo
 * @Author HuoJiaJin
 * @Date 2021/2/21 23:39
 * @Version 1.0
**/
public interface SalaryAlertInfluenceRepo extends AbstractJpaRepo<SalaryAlertInfluence, String> {

    @Query(" from SalaryAlertInfluence where alertId = ?1")
    List<SalaryAlertInfluence> listByAlertId(String alertId);
}
