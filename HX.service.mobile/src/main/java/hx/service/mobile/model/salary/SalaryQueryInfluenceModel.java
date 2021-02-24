package hx.service.mobile.model.salary;

import hx.base.core.dao.entity.common.BaseEntity;

/**
 * @ClassName: SalaryQueryInfluenceModel
 * @Description: 追踪支持影响因素查询Model
 * @Author HuoJiaJin
 * @Date 2021/2/24 20:42
 * @Version 1.0
 **/
public class SalaryQueryInfluenceModel extends BaseEntity {

    private String itemName;//影响因素名称
    private String itemValue;//影响因素数值

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemValue() {
        return itemValue;
    }

    public void setItemValue(String itemValue) {
        this.itemValue = itemValue;
    }
}
