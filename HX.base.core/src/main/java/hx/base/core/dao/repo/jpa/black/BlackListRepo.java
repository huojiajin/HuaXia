package hx.base.core.dao.repo.jpa.black;

import hx.base.core.dao.dict.black.BlackListType;
import hx.base.core.dao.entity.black.BlackList;
import hx.base.core.dao.repo.jpa.common.AbstractJpaRepo;
import org.springframework.data.jpa.repository.Query;

/**
 * @ClassName BlackListRepo
 * @Description 黑名单Repo
 * @Author HuoJiaJin
 * @Date 2020/6/27 5:05
 * @Version 1.0
 **/
public interface BlackListRepo extends AbstractJpaRepo<BlackList, String> {

    @Query("from BlackList where agentCode = ?1 and type = ?2")
    BlackList findByAgentCodeAndType(String agentCode, BlackListType type);
}
