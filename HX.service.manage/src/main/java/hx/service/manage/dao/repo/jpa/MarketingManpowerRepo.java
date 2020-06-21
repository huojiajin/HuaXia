package hx.service.manage.dao.repo.jpa;

import hx.service.manage.dao.entity.MarketingManpower;
import hx.service.manage.dao.repo.jpa.common.AbstractJpaRepo;
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
}
