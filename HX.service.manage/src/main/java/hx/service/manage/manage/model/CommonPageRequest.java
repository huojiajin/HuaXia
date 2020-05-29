package hx.service.manage.manage.model;

/**
 * @name: CommonPageRequest
 * @description: 公用分页请求
 * @author: huojiajin
 * @time: 2020/5/28 10:46
 */
public class CommonPageRequest extends CommonRequest {

    private int currentPage;
    private int pageSize = 16;

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
}
