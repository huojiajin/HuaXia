package hx.service.mobile.model.back.salary;

import hx.base.core.dao.entity.common.BaseEntity;

import java.util.List;

/**
 * @ClassName: SalaryQueryResponse
 * @Description: 追踪支持查询Response
 * @Author HuoJiaJin
 * @Date 2021/2/24 20:38
 * @Version 1.0
 **/
public class SalaryQueryResponse extends BaseEntity {

    private String name;//人员姓名
    private double salary;//预计收入
    private double gap;//距离下一档位差距
    private String type;//奖项类别
    private String remark;//备注
    private List<SalaryQueryInfluenceModel> itemList;//影响因素集合

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public double getGap() {
        return gap;
    }

    public void setGap(double gap) {
        this.gap = gap;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public List<SalaryQueryInfluenceModel> getItemList() {
        return itemList;
    }

    public void setItemList(List<SalaryQueryInfluenceModel> itemList) {
        this.itemList = itemList;
    }
}
