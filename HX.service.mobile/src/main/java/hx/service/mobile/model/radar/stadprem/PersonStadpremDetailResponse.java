package hx.service.mobile.model.radar.stadprem;

import hx.base.core.dao.entity.common.BaseEntity;

import java.util.List;

/**
 * @ClassName PersonStadpremDetailResponse
 * @Description 个人标保详情Response
 * @Author HuoJiaJin
 * @Date 2020/6/27 9:54
 * @Version 1.0
 **/
public class PersonStadpremDetailResponse extends BaseEntity {

    private int currentPage;
    private int pageSize = 16;
    private long pageCount;
    private long totalCount;
    private List<PersonStadpremDetailModel> result;

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

    public List<PersonStadpremDetailModel> getResult() {
        return result;
    }

    public void setResult(List<PersonStadpremDetailModel> result) {
        this.result = result;
    }
}
