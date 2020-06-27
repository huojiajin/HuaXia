package hx.base.core.dao.repo.jpa.test.integral;

import hx.base.core.dao.entity.test.integral.Integral;
import hx.base.core.dao.repo.jpa.common.AbstractJpaRepo;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 *@ClassName IntegralRepo
 *@Description 积分表Repo
 *@Author HuoJiaJin
 *@Date 2020/6/23 19:38
 *@Version 1.0
 **/
public interface IntegralRepo extends AbstractJpaRepo<Integral, String> {

    @Query(" from Integral where month = ?1 order by agentCode")
    List<Integral> listByMonth(String month);

    @Query(" from Integral where month = ?1 and agentCode = ?2")
    Integral findByAgentCode(String month, String agentCode);
}
