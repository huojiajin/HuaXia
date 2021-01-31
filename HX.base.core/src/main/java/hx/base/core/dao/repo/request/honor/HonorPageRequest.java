package hx.base.core.dao.repo.request.honor;

import hx.base.core.dao.entity.honor.Honor;
import hx.base.core.dao.repo.request.common.HqlBuilder;
import hx.base.core.dao.repo.request.common.JpaPageableDataRequest;

/**
 * @ClassName: HonorPageRequest
 * @Description: 荣誉分页查询Request
 * @Author HuoJiaJin
 * @Date 2021/2/1 1:20
 * @Version 1.0
 **/
public class HonorPageRequest extends JpaPageableDataRequest<Honor> {

    private String name;


    @Override
    public HqlBuilder toSelectHql() {
        HqlBuilder hql = new HqlBuilder("from " + clazz.getName() + " where 1=1");
        hql.append(" and name = :name", name);
        return hql;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
