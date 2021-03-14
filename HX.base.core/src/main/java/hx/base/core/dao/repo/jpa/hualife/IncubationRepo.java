package hx.base.core.dao.repo.jpa.hualife;

import hx.base.core.dao.entity.hualife.Incubation;
import hx.base.core.dao.repo.jpa.common.AbstractJpaRepo;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @ClassName IncubationRepo
 * @Description 育成关系表Repo
 * @Author HuoJiaJin
 * @Date 2020/6/27 1:00
 * @Version 1.0
 **/
public interface IncubationRepo extends AbstractJpaRepo<Incubation, String> {

    @Query("from Incubation where rearAgentCode = ?1 and endDate is null")
    List<Incubation> listByRearAgentCode(String rearAgentCode);

    @Query("from Incubation where agentCode = ?1 and rearedgens = ?2 and endDate is null")
    List<Incubation> listByAgentCode(String agentCode, Long rearedgens);
}
