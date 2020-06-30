package hx.base.core.dao.repo.jpa.test.integral;

import hx.base.core.dao.entity.test.integral.Integral;
import hx.base.core.dao.repo.jpa.common.AbstractJpaRepo;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

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

    @Modifying
    @Transactional
    @Query("update Integral set signInNum  = signInNum + ?1 , allNum = allNum + ?1 where month = ?1 and agentCode = ?2")
    int updateSignIn(String month, String agentCode, int integral);

    @Modifying
    @Transactional
    @Query("update Integral set testNum  = testNum + ?1 , allNum = allNum + ?1 where month = ?1 and agentCode = ?2")
    int updatePaper(String month, String agentCode, int integral);

    @Modifying
    @Transactional
    @Query("update Integral set courseNum  = courseNum + ?1 , allNum = allNum + ?1 where month = ?1 and agentCode = ?2")
    int updateCourse(String month, String agentCode, int integral);

    @Query(" from Integral where month = ?1 order by allNum desc")
    List<Integral> listRank(String month);
}
