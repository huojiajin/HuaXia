package hx.service.mobile.model.test.course;

import hx.base.core.dao.entity.common.BaseEntity;

/**
 * @name: CourseListModel
 * @description: 学习资料列表Model
 * @author: huojiajin
 * @time: 2020/6/30 22:49
 */
public class CourseListModel extends BaseEntity {

    private String id;//资料ID
    private String name;//资料名称
    private String createTime;//上传时间
    private boolean hasLearn;//是否已学
    private boolean hasStop;//是否已停止

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

    public boolean getHasLearn() {
        return hasLearn;
    }

    public void setHasLearn(boolean hasLearn) {
        this.hasLearn = hasLearn;
    }

    public boolean getHasStop() {
        return hasStop;
    }

    public void setHasStop(boolean hasStop) {
        this.hasStop = hasStop;
    }
}
