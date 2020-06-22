package hx.service.manage.dao.entity.test.course;

import hx.service.manage.dao.entity.common.AbstractInsertTimeEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;

/**
 * @name: CoursePush
 * @description: 资料推送表
 * @author: huojiajin
 * @time: 2020/6/22 16:57
 */
@Entity
@Table(name = "hx_course_push", indexes = {
        @Index(columnList = "agent_code", name = "hx_course_push_index"),
        @Index(columnList = "course_id", name = "hx_course_push_index1")
})
public class CoursePush extends AbstractInsertTimeEntity {

    private String courseId;//资料ID
    private String agentCode;//营销员编码
    private String staffName;//员工姓名
    private boolean hasLearn;//是否已学

    @Column(name = "course_id")
    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    @Column(name = "agent_code")
    public String getAgentCode() {
        return agentCode;
    }

    public void setAgentCode(String agentCode) {
        this.agentCode = agentCode;
    }

    @Column(name = "staff_name")
    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    @Column(name = "has_learn")
    public boolean isHasLearn() {
        return hasLearn;
    }

    public void setHasLearn(boolean hasLearn) {
        this.hasLearn = hasLearn;
    }
}
