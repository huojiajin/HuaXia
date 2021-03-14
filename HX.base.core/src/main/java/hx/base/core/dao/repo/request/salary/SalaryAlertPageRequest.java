package hx.base.core.dao.repo.request.salary;

import hx.base.core.dao.entity.salary.SalaryAlert;
import hx.base.core.dao.repo.request.common.HqlBuilder;
import hx.base.core.dao.repo.request.common.JpaPageableDataRequest;

import java.time.LocalDate;

/**
 * @ClassName: SalaryAlertPageRequest.java
 * @Description: 薪资项目预警分页查询Request
 * @Author HuoJiaJin
 * @Date 2021/2/24 20:47
 * @Version 1.0
**/
public class SalaryAlertPageRequest extends JpaPageableDataRequest<SalaryAlert> {

    private String campCode;//区代码
    private String sectionCode;//部代码
    private String groupCode;//组代码
    private LocalDate month;//月份
    private boolean person;//是否查询个人

    public SalaryAlertPageRequest() {
        this.orderBy = "insertTime";
        this.desc = true;
    }

    @Override
    public HqlBuilder toSelectHql() {
        HqlBuilder hql = new HqlBuilder("from " + clazz.getName() + " where 1=1");
        hql.append(" and campCode = :campCode", campCode);
        hql.append(" and sectionCode = :sectionCode", sectionCode);
        hql.append(" and groupCode = :groupCode", groupCode);
        hql.append(" and month = :month", month);
        if (person){
            hql.append(" and type = 'INDIVIDUALHALFYEAR'");
        }else {
            hql.append(" and type != 'INDIVIDUALHALFYEAR'");
        }
        return hql;
    }

    public String getCampCode() {
        return campCode;
    }

    public void setCampCode(String campCode) {
        this.campCode = campCode;
    }

    public String getSectionCode() {
        return sectionCode;
    }

    public void setSectionCode(String sectionCode) {
        this.sectionCode = sectionCode;
    }

    public String getGroupCode() {
        return groupCode;
    }

    public void setGroupCode(String groupCode) {
        this.groupCode = groupCode;
    }

    public LocalDate getMonth() {
        return month;
    }

    public void setMonth(LocalDate month) {
        this.month = month;
    }

    public boolean isPerson() {
        return person;
    }

    public void setPerson(boolean person) {
        this.person = person;
    }
}
