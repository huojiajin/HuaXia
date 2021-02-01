package hx.service.manage.model.quit;

import hx.base.core.dao.entity.common.BaseEntity;

/**
 * @ClassName: QuitAssignQueryResponse
 * @Description: 离职人员指派查询Response
 * @Author HuoJiaJin
 * @Date 2021/2/1 23:24
 * @Version 1.0
 **/
public class QuitAssignQueryResponse extends BaseEntity {

    private String id;//指派id
    private int type;//人员类型
    private String employeeNum;//工号
    private String name;//姓名
    private String campName;//营服名称
    private String campCode;//营服代码

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getEmployeeNum() {
        return employeeNum;
    }

    public void setEmployeeNum(String employeeNum) {
        this.employeeNum = employeeNum;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCampName() {
        return campName;
    }

    public void setCampName(String campName) {
        this.campName = campName;
    }

    public String getCampCode() {
        return campCode;
    }

    public void setCampCode(String campCode) {
        this.campCode = campCode;
    }
}
