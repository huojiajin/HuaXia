package hx.base.core.dao.repo.jpa;

import hx.base.core.dao.entity.MarketingManpower;
import hx.base.core.dao.repo.jpa.common.AbstractJpaRepo;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 *@ClassName MarketingManpowerRepo
 *@Description 人力清单Repo
 *@Author HuoJiaJin
 *@Date 2020/6/22 0:07
 *@Version 1.0
 **/
public interface MarketingManpowerRepo extends AbstractJpaRepo<MarketingManpower, String> {

    @Query(" from MarketingManpower where agentGrade = ?1")
    List<MarketingManpower> listByAgentGrade(String agentGrade);

    @Query(" from MarketingManpower where agentCode in (?1)")
    List<MarketingManpower> listByAgentCodes(List<String> agentCodes);

    @Query(" from MarketingManpower where deptCode3 = ?1")
    List<MarketingManpower> listByDeptCode3(String deptCode3);

    @Query(" from MarketingManpower where deptCode4 = ?1")
    List<MarketingManpower> listByDeptCode4(String deptCode4);
}
