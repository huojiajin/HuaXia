package hx.service.manage.model.honor;

import hx.base.core.dao.entity.common.BaseEntity;

/**
 * @ClassName: HonorQueryResponse
 * @Description: 荣誉查询Response
 * @Author HuoJiaJin
 * @Date 2021/2/1 1:26
 * @Version 1.0
 **/
public class HonorQueryResponse extends BaseEntity {

    private String name;//荣誉名称
    private int status;//状态
    private String icon;//荣誉图标
    private String createTime;//创建时间

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}
