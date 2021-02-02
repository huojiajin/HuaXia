package hx.base.core.dao.repo.request.black;

import hx.base.core.dao.dict.black.BlackListType;
import hx.base.core.dao.entity.black.BlackList;
import hx.base.core.dao.repo.request.common.HqlBuilder;
import hx.base.core.dao.repo.request.common.JpaPageableDataRequest;

/**
 * @ClassName: BlackListPageRequest
 * @Description: 黑名单分页查询Request
 * @Author HuoJiaJin
 * @Date 2021/2/1 1:20
 * @Version 1.0
 **/
public class BlackListPageRequest extends JpaPageableDataRequest<BlackList> {

    private BlackListType type;

    @Override
    public HqlBuilder toSelectHql() {
        HqlBuilder hql = new HqlBuilder("from " + clazz.getName() + " where 1=1");
        hql.append(" and type = :type", type);
        return hql;
    }

    public BlackListType getType() {
        return type;
    }

    public void setType(BlackListType type) {
        this.type = type;
    }
}
