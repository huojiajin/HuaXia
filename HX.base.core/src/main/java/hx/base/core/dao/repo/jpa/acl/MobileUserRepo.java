package hx.base.core.dao.repo.jpa.acl;

import hx.base.core.dao.entity.acl.MobileUser;
import hx.base.core.dao.repo.jpa.common.AbstractJpaRepo;

public interface MobileUserRepo extends AbstractJpaRepo<MobileUser, String> {

    MobileUser findByAgentCode(String agentCode);
}
