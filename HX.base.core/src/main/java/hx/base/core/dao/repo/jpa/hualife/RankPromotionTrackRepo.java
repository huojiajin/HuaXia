package hx.base.core.dao.repo.jpa.hualife;

import hx.base.core.dao.entity.hualife.RankPromotionTrack;
import hx.base.core.dao.repo.jpa.common.AbstractJpaRepo;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @ClassName: RankPromotionTrackRepo.java
 * @Description: 职级晋升轨迹repo
 * @Author HuoJiaJin
 * @Date 2021/2/6 1:56
 * @Version 1.0
**/
public interface RankPromotionTrackRepo extends AbstractJpaRepo<RankPromotionTrack, String> {

    @Query("from RankPromotionTrack where agentCode = ?1 order by startDate desc, modifyTime desc")
    List<RankPromotionTrack> listByAgetnCode(String agentCode);

    @Query("from RankPromotionTrack where startDate = ?1")
    List<RankPromotionTrack> listByStartDate(LocalDateTime startDate);
}
