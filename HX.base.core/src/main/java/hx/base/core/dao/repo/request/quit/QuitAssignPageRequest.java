package hx.base.core.dao.repo.request.quit;

import hx.base.core.dao.entity.quit.QuitAssign;
import hx.base.core.dao.repo.request.common.HqlBuilder;
import hx.base.core.dao.repo.request.common.JpaPageableDataRequest;

/**
 * @ClassName: QuitAssignPageRequest
 * @Description: 离职人员指派分页查询Request
 * @Author HuoJiaJin
 * @Date 2021/2/1 23:37
 * @Version 1.0
**/
public class QuitAssignPageRequest extends JpaPageableDataRequest<QuitAssign> {

    @Override
    public HqlBuilder toSelectHql() {
        HqlBuilder hql = new HqlBuilder("from " + clazz.getName() + " where 1=1");
        return hql;
    }
}
