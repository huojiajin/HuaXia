package hx.service.manage.dao.repo.jpa;

import hx.service.manage.dao.entity.Incubation;
import hx.service.manage.dao.repo.jpa.common.AbstractJpaRepo;
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
}
