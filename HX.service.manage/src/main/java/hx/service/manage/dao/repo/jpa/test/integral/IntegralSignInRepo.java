package hx.service.manage.dao.repo.jpa.test.integral;

import hx.service.manage.dao.entity.test.integral.IntegralSignIn;
import hx.service.manage.dao.repo.jpa.common.AbstractJpaRepo;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;

/**
 * @ClassName IntegralSignInRepo
 * @Description 签到积分表Repo
 * @Author HuoJiaJin
 * @Date 2020/6/26 17:19
 * @Version 1.0
 **/
public interface IntegralSignInRepo  extends AbstractJpaRepo<IntegralSignIn, String> {

    @Query(" from IntegralSignIn where signInDate = ?1 and agentCode = ?2")
    IntegralSignIn findBySignInDate(LocalDate signInDate, String agentCode);
}
