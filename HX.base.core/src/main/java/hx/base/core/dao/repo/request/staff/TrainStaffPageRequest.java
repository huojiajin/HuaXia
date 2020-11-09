package hx.base.core.dao.repo.request.staff;

import hx.base.core.dao.entity.staff.TrainStaff;
import hx.base.core.dao.repo.request.common.HqlBuilder;
import hx.base.core.dao.repo.request.common.JpaPageableDataRequest;
import org.springframework.data.domain.Sort;

/**
 * @name: MarketingManpowerPageRequest
 * @description: 人力清单分页查询
 * @author: huojiajin
 * @time: 2020/7/3 11:42
 */
public class TrainStaffPageRequest extends JpaPageableDataRequest<TrainStaff> {

    private String deptCode1;//营业区代码
    private String deptName1;//营业区名称
    private String deptCode2;//总监区代码
    private String deptName2;//总监区名称
    private String deptCode3;//业务部代码
    private String deptName3;//业务部名称
    private String deptCode4;//业务组代码
    private String deptName4;//业务组名称

    public TrainStaffPageRequest() {
        this.otherSort = Sort.by("deptCode1", "deptCode2", "deptCode3", "deptCode4");
    }

    @Override
    public String toCountHql()
    {
        return "select count(*) " + toSelectHql().toString();
    }

    @Override
    public HqlBuilder toSelectHql() {
        HqlBuilder hql = new HqlBuilder("from " + clazz.getName() + " where 1=1");
        hql.append(" and deptName1 like :deptName1", like(deptName1));
        hql.append(" and deptCode1 = :deptCode1", like(deptCode1));
        hql.append(" and deptName2 like :deptName2", like(deptName2));
        hql.append(" and deptCode2 = :deptCode2", like(deptCode2));
        hql.append(" and deptName3 like :deptName3", like(deptName3));
        hql.append(" and deptCode3 = :deptCode3", like(deptCode3));
        hql.append(" and deptName4 like :deptName4", like(deptName4));
        hql.append(" and deptCode4 = :deptCode4", like(deptCode4));
        return hql;
    }

    public String getDeptCode1() {
        return deptCode1;
    }

    public void setDeptCode1(String deptCode1) {
        this.deptCode1 = deptCode1;
    }

    public String getDeptName1() {
        return deptName1;
    }

    public void setDeptName1(String deptName1) {
        this.deptName1 = deptName1;
    }

    public String getDeptCode2() {
        return deptCode2;
    }

    public void setDeptCode2(String deptCode2) {
        this.deptCode2 = deptCode2;
    }

    public String getDeptName2() {
        return deptName2;
    }

    public void setDeptName2(String deptName2) {
        this.deptName2 = deptName2;
    }

    public String getDeptCode3() {
        return deptCode3;
    }

    public void setDeptCode3(String deptCode3) {
        this.deptCode3 = deptCode3;
    }

    public String getDeptName3() {
        return deptName3;
    }

    public void setDeptName3(String deptName3) {
        this.deptName3 = deptName3;
    }

    public String getDeptCode4() {
        return deptCode4;
    }

    public void setDeptCode4(String deptCode4) {
        this.deptCode4 = deptCode4;
    }

    public String getDeptName4() {
        return deptName4;
    }

    public void setDeptName4(String deptName4) {
        this.deptName4 = deptName4;
    }
}
