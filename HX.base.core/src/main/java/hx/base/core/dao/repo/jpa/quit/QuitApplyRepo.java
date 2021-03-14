package hx.base.core.dao.repo.jpa.quit;

import hx.base.core.dao.entity.quit.QuitApply;
import hx.base.core.dao.repo.jpa.common.AbstractJpaRepo;
import org.springframework.data.jpa.repository.Query;

/**
 * @ClassName: QuitApplyRepo.java
 * @Description: 离职申请Repo
 * @Author HuoJiaJin
 * @Date 2021/2/1 23:36
 * @Version 1.0
**/
public interface QuitApplyRepo extends AbstractJpaRepo<QuitApply, String> {

    @Query(" from QuitApply where agentCode = ?1 and status = 'SPECIAL'")
    QuitApply findBySpecial(String agentCode);

    @Query(" from QuitApply where agentCode = ?1 and status <> 'NOPASS'")
    QuitApply findApproval(String agentCode);
}
