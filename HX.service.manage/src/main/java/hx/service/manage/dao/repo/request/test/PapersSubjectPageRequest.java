package hx.service.manage.dao.repo.request.test;

import hx.service.manage.dao.entity.test.papers.PapersSubject;
import hx.service.manage.dao.repo.request.common.HqlBuilder;
import hx.service.manage.dao.repo.request.common.JpaPageableDataRequest;

/**
 * @ClassName PapersSubjectPageRequest
 * @Description 试卷题目分页查询
 * @Author HuoJiaJin
 * @Date 2020/6/22 22:00
 * @Version 1.0
 **/
public class PapersSubjectPageRequest extends JpaPageableDataRequest<PapersSubject> {

    private String papersId;//试卷ID

    public PapersSubjectPageRequest() {
        this.orderBy = "list";
    }

    @Override
    public HqlBuilder toSelectHql() {
        HqlBuilder hql = new HqlBuilder(" from " + clazz.getName() + " where 1=1");
        hql.append(" papersId = :papersId", papersId);
        return hql;
    }

    public String getPapersId() {
        return papersId;
    }

    public void setPapersId(String papersId) {
        this.papersId = papersId;
    }
}
