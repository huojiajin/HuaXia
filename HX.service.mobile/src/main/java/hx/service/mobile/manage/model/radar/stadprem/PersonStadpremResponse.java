package hx.service.mobile.manage.model.radar.stadprem;

import hx.base.core.dao.entity.common.BaseEntity;

import java.util.List;

/**
 * @ClassName PersonStadpremResponse
 * @Description 个人标保返回信息
 * @Author HuoJiaJin
 * @Date 2020/6/27 9:34
 * @Version 1.0
 **/
public class PersonStadpremResponse extends BaseEntity {

    private int currentPage;
    private int pageSize = 16;
    private long pageCount;
    private long totalCount;
    private List<PersonStadpremModel> result;

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

    public List<PersonStadpremModel> getResult() {
        return result;
    }

    public void setResult(List<PersonStadpremModel> result) {
        this.result = result;
    }
}
