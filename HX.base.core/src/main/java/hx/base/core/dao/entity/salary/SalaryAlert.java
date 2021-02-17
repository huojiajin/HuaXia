package hx.base.core.dao.entity.salary;

import hx.base.core.dao.entity.common.AbstractInsertTimeEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @ClassName: SalaryAlert
 * @Description: 核心薪资项目预警表实体
 * @Author HuoJiaJin
 * @Date 2021/2/7 2:53
 * @Version 1.0
 **/
@Entity
@Table(name = "hx_salary_alert")
public class SalaryAlert extends AbstractInsertTimeEntity {

    private String month;//月份yyyy-MM

}
