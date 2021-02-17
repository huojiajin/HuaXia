package hx.base.core.dao.repo.jpa.hualife;

import hx.base.core.dao.entity.hualife.StarRating;
import hx.base.core.dao.repo.jpa.common.AbstractJpaRepo;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 *@ClassName StarRatingRepo
 *@Description 凤凰社星级Repo
 *@Author HuoJiaJin
 *@Date 2020/6/27 3:16
 *@Version 1.0
 **/
public interface StarRatingRepo extends AbstractJpaRepo<StarRating, String> {

    @Query(" from StarRating where agentCode in (?1)")
    List<StarRating> listByAgentCodes(List<String> agentCodes);

    @Query(" from StarRating where agentCode = ?1")
    StarRating findByAgentCode(String agentCode);

    @Query(" from StarRating where agentCode in (select agentCode from MarketingManpower where deptCode1 = ?1 and outworkDate is null)")
    List<StarRating> listByDeptCode1(String deptCode1);

    @Query("select count(agentCode) from StarRating where agentCode in " +
            "(select agentCode from MarketingManpower where deptCode1 = ?1 and agentGrade in ?2 and outworkDate is null )")
    Integer countExecutive(String deptCode1, List<String> gradeList);
}
