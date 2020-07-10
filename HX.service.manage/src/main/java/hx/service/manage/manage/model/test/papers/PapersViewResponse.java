package hx.service.manage.manage.model.test.papers;

import hx.base.core.dao.entity.common.BaseEntity;

import java.util.List;

/**
 * @ClassName PapersViewResponse
 * @Description 试卷查看Response
 * @Author HuoJiaJin
 * @Date 2020/6/21 21:57
 * @Version 1.0
 **/
public class PapersViewResponse extends BaseEntity {

    private int currentPage;
    private int pageSize = 16;
    private long pageCount;
    private long totalCount;
    private List<PapersViewModel> subjectList;//题目集合

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public long getPageCount() {
        return pageCount;
    }

    public void setPageCount(long pageCount) {
        this.pageCount = pageCount;
    }

    public long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(long totalCount) {
        this.totalCount = totalCount;
    }

    public List<PapersViewModel> getSubjectList() {
        return subjectList;
    }

    public void setSubjectList(List<PapersViewModel> subjectList) {
        this.subjectList = subjectList;
    }
}
