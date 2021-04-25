package hx.base.core.dao.repo.jpa.honor;

import hx.base.core.dao.entity.honor.HonorPeople;
import hx.base.core.dao.repo.jpa.common.AbstractJpaRepo;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @ClassName HonorPeopleRepo
 * @Description 荣誉获得人员Repo
 * @Author HuoJiaJin
 * @Date 2020/6/27 5:05
 * @Version 1.0
 **/
public interface HonorPeopleRepo extends AbstractJpaRepo<HonorPeople, String> {

    @Query("from HonorPeople where honorId = ?1")
    List<HonorPeople> listByHonorId(String honorId);

    @Query("from HonorPeople where agentCode = ?1 order by insertTime desc")
    List<HonorPeople> listByAgentCode(String agnetCode);
}
