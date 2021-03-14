package hx.base.core.dao.repo.jpa.quit;

import hx.base.core.dao.dict.quit.QuitAssignType;
import hx.base.core.dao.entity.quit.QuitAssign;
import hx.base.core.dao.repo.jpa.common.AbstractJpaRepo;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

/**
 * @ClassName: QuitAssignRepo.java
 * @Description: 离职人员指派Repo
 * @Author HuoJiaJin
 * @Date 2021/2/1 23:36
 * @Version 1.0
**/
public interface QuitAssignRepo extends AbstractJpaRepo<QuitAssign, String> {

    @Modifying
    @Transactional
    @Query("update QuitAssign set name = ?1 , agentCode = ?2 where id = ?3")
    int updatePeople(String name, String employeeNum, String id);

    QuitAssign findByCampCodeAndType(String campCode, QuitAssignType type);

    @Query(" from QuitAssign where type = 'FGSRG'")
    QuitAssign findRG();
}
