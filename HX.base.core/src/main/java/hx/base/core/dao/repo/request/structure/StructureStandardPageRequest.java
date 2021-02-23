package hx.base.core.dao.repo.request.structure;

import hx.base.core.dao.entity.structure.StructureStandard;
import hx.base.core.dao.repo.request.common.HqlBuilder;
import hx.base.core.dao.repo.request.common.JpaPageableDataRequest;

/**
 * @ClassName: StructureStandardPageRequest
 * @Description: 组织架构标准分页查询Request
 * @Author HuoJiaJin
 * @Date 2021/2/24 1:20
 * @Version 1.0
 **/
public class StructureStandardPageRequest extends JpaPageableDataRequest<StructureStandard> {

    @Override
    public HqlBuilder toSelectHql() {
        HqlBuilder hql = new HqlBuilder("from " + clazz.getName() + " where 1=1");
        return hql;
    }
}
