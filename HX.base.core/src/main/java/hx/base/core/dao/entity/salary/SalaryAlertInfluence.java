package hx.base.core.dao.entity.salary;

import hx.base.core.dao.dict.salary.PrizeInfluenceType;
import hx.base.core.dao.entity.common.AbstractInsertTimeEntity;

import javax.persistence.*;

/**
 * @ClassName: SalaryAlertInfluence
 * @Description: 核心薪资项目影响因素表实体
 * @Author HuoJiaJin
 * @Date 2021/2/21 23:33
 * @Version 1.0
 **/
@Entity
@Table(name = "hx_salary_alert_influence")
public class SalaryAlertInfluence extends AbstractInsertTimeEntity {

    private String alertId;//预警表ID
    private PrizeInfluenceType type;//影响因素类别
    private double num;//数量

    @Column(name = "alert_id")
    public String getAlertId() {
        return alertId;
    }

    public void setAlertId(String alertId) {
        this.alertId = alertId;
    }

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    public PrizeInfluenceType getType() {
        return type;
    }

    public void setType(PrizeInfluenceType type) {
        this.type = type;
    }

    @Column(name = "num")
    public double getNum() {
        return num;
    }

    public void setNum(double num) {
        this.num = num;
    }
}
