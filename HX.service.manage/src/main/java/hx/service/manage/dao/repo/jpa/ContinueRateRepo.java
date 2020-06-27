package hx.service.manage.dao.repo.jpa;

import hx.service.manage.dao.entity.ContinueRate;
import hx.service.manage.dao.repo.jpa.common.AbstractJpaRepo;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 *@ClassName ContinueRate
 *@Description 继续率表Repo
 *@Author HuoJiaJin
 *@Date 2020/6/27 3:39
 *@Version 1.0
 **/
public interface ContinueRateRepo extends AbstractJpaRepo<ContinueRate, String> {

    @Query(" from ContinueRate where agentCode in (?1)")
    List<ContinueRate> listByAgentCodes(List<String> agentCodes);
}
