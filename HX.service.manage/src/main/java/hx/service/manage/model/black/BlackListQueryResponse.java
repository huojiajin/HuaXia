package hx.service.manage.model.black;

import hx.base.core.dao.entity.common.BaseEntity;

/**
 * @ClassName: BlackListQueryResponse
 * @Description: 黑名单查询Response
 * @Author HuoJiaJin
 * @Date 2021/2/3 0:47
 * @Version 1.0
 **/
public class BlackListQueryResponse extends BaseEntity {

    private String id;//ID
    private String name;//人员名称
    private String employeeNum;//工号
    private String sectionName;//部名称
    private String groupName;//组名称
    private int type;//类别
    private String createTime;//进入时间

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmployeeNum() {
        return employeeNum;
    }

    public void setEmployeeNum(String employeeNum) {
        this.employeeNum = employeeNum;
    }

    public String getSectionName() {
        return sectionName;
    }

    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}
