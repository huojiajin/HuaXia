package hx.service.manage.model.test.course;

import hx.base.core.dao.entity.common.BaseEntity;

/**
 * @name: CourseQueryModel
 * @description: 试卷查询Model
 * @author: huojiajin
 * @time: 2020/6/22 17:20
 */
public class CourseQueryModel extends BaseEntity {

    private String id;
    private String name;//资料名称
    private String createTime;//创建时间
    private int type;//类别
    private int status;//状态
    private int useStatus;//在用状态

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

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getUseStatus() {
        return useStatus;
    }

    public void setUseStatus(int useStatus) {
        this.useStatus = useStatus;
    }
}
