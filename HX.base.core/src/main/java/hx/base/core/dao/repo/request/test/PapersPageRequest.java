package hx.base.core.dao.repo.request.test;

import hx.base.core.dao.repo.request.common.HqlBuilder;
import hx.base.core.dao.repo.request.common.JpaPageableDataRequest;
import hx.base.core.dao.dict.PapersType;
import hx.base.core.dao.entity.test.papers.Papers;

/**
 * @name: PapersPageRequest
 * @description: 试卷分页Request
 * @author: huojiajin
 * @time: 2020/5/28 10:57
 */
public class PapersPageRequest extends JpaPageableDataRequest<Papers> {

    private String name;//试卷名称
    private PapersType type;//试卷类型

    public PapersPageRequest() {
        this.orderBy = "updateTime";
        this.desc = true;
    }

    @Override
    public HqlBuilder toSelectHql() {
        HqlBuilder hql = new HqlBuilder("from " + clazz.getName() + " where 1=1");
        hql.append(" and hasDelete = 0");
        hql.append(" and name like :name", like(name));
        hql.append(" and type = :type", type);
        return hql;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PapersType getType() {
        return type;
    }

    public void setType(PapersType type) {
        this.type = type;
    }
}
