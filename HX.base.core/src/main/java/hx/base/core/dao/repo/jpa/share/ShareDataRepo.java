package hx.base.core.dao.repo.jpa.share;

import hx.base.core.dao.dict.share.ShareType;
import hx.base.core.dao.entity.share.ShareData;
import hx.base.core.dao.repo.jpa.common.AbstractJpaRepo;

/**
 * @name: ShareDataRepo
 * @description: 手机端电脑端共享数据Repo
 * @author: huojiajin
 * @time: 2021/3/11 15:34
 */
public interface ShareDataRepo extends AbstractJpaRepo<ShareData, String> {

    ShareData findByType(ShareType type);
}
