package hx.service.mobile.manage.model.radar.attend;

import hx.base.core.dao.entity.common.BaseEntity;

import java.util.List;

/**
 * @ClassName PersonAttendResponse
 * @Description 个人出勤人力Response
 * @Author HuoJiaJin
 * @Date 2020/6/27 17:55
 * @Version 1.0
 **/
public class PersonAttendResponse extends BaseEntity {

    private int currentPage;
    private int pageSize = 16;
    private long pageCount;
    private long totalCount;
    private List<PersonAttendModel> result;

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

    public List<PersonAttendModel> getResult() {
        return result;
    }

    public void setResult(List<PersonAttendModel> result) {
        this.result = result;
    }
}
