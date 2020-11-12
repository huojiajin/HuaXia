package hx.base.core.dao.repo.jpa.staff;

import hx.base.core.dao.entity.staff.TrainPeople;
import hx.base.core.dao.repo.jpa.common.AbstractJpaRepo;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @ClassName TrainPeopleRepo
 * @Description 培训场次人员Repo
 * @Author HuoJiaJin
 * @Date 2020/11/8 2:38
 * @Version 1.0
 **/
public interface TrainPeopleRepo extends AbstractJpaRepo<TrainPeople, String> {

    @Query("from TrainPeople where trainId = ?1")
    List<TrainPeople> listByTrainId(String trainId);
}
