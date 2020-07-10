package hx.service.mobile.manage.model.common;

/**
 * @name: CommonPageRequest
 * @description: 公用分页请求
 * @author: huojiajin
 * @time: 2020/5/28 10:46
 */
public class MobileCommonPageRequest extends MobileCommonRequest {

    private int pageNo;
    private int pageSize = 16;

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
