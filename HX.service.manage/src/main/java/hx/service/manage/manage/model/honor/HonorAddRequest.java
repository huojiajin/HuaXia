package hx.service.manage.manage.model.honor;

import hx.service.manage.manage.model.CommonRequest;

/**
 * @ClassName: HonorAddRequest
 * @Description: 荣誉添加Request
 * @Author HuoJiaJin
 * @Date 2021/2/1 1:27
 * @Version 1.0
 **/
public class HonorAddRequest extends CommonRequest {

    private String name;//荣誉名称
    private String icon;//荣誉图标

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}
