package hx.service.manage.dao.entity.test.course;

import hx.service.manage.dao.dict.CourseStatus;
import hx.service.manage.dao.dict.CourseType;
import hx.service.manage.dao.entity.common.AbstractInsertTimeEntity;

import javax.persistence.*;
import java.sql.Blob;

/**
 * @name: Course
 * @description: 学习资料表
 * @author: huojiajin
 * @time: 2020/6/22 16:52
 */
@Entity
@Table(name = "hx_course")
public class Course extends AbstractInsertTimeEntity {

    private String name;//资料名称
    private CourseType type;//资料类别
    private CourseStatus status = CourseStatus.WDR;//资料状态
    private boolean hasDelete;//是否停用
    private Blob content;//学习文件内容

    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    public CourseType getType() {
        return type;
    }

    public void setType(CourseType type) {
        this.type = type;
    }

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    public CourseStatus getStatus() {
        return status;
    }

    public void setStatus(CourseStatus status) {
        this.status = status;
    }

    @Column(name = "has_delete")
    public boolean isHasDelete() {
        return hasDelete;
    }

    public void setHasDelete(boolean hasStop) {
        this.hasDelete = hasStop;
    }

    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(name = "content")
    public Blob getContent() {
        return content;
    }

    public void setContent(Blob file) {
        this.content = file;
    }
}
