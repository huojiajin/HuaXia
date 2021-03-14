package hx.base.core.dao.repo.jpa.quit;

import hx.base.core.dao.dict.quit.QuitApplyApprovalType;
import hx.base.core.dao.entity.quit.QuitApplyFlow;
import hx.base.core.dao.repo.jpa.common.AbstractJpaRepo;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @ClassName: QuitApplyRepo.java
 * @Description: 离职申请流程Repo
 * @Author HuoJiaJin
 * @Date 2021/2/1 23:36
 * @Version 1.0
**/
public interface QuitApplyFlowRepo extends AbstractJpaRepo<QuitApplyFlow, String> {

    @Query(" from QuitApplyFlow where applyId = ?1 and approvalType = 'FGSRG'")
    QuitApplyFlow findSpecial(String applyId);

    @Query(" from QuitApplyFlow where applyId = ?1")
    List<QuitApplyFlow> listByApplyId(String applyId);

    @Query(" from QuitApplyFlow where applyId = ?1 and approvalName = ?2")
    QuitApplyFlow findApproval(String applyId, String approvalName);

    @Query(" from QuitApplyFlow where applyId = ?1 and approvalType = ?2")
    QuitApplyFlow findByType(String applyId, QuitApplyApprovalType approvalType);
}
