package hx.service.manage.dao.entity.test.papers;

import hx.service.manage.dao.entity.common.StringUUIDEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;

/**
 * @ClassName PapersOption
 * @Description 试卷选项
 * @Author HuoJiaJin
 * @Date 2020/6/21 14:03
 * @Version 1.0
 **/
@Entity
@Table(name = "hx_papers_options", indexes = {
        @Index(columnList = "subject_id", name = "hx_papers_options_index")
})
public class PapersOption extends StringUUIDEntity {

    private String subjectId;//题目ID
    private int list;//序号
    private String content;//内容

    @Column(name = "subject_id")
    public String getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(String subjectId) {
        this.subjectId = subjectId;
    }

    @Column(name = "list")
    public int getList() {
        return list;
    }

    public void setList(int list) {
        this.list = list;
    }

    @Column(name = "content")
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
