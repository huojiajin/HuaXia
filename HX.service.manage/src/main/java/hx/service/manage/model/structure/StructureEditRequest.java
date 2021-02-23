package hx.service.manage.model.structure;

import hx.service.manage.model.common.CommonRequest;

/**
 * @ClassName: StructureEditRequest
 * @Description: 组织架构标准修改Request
 * @Author HuoJiaJin
 * @Date 2021/2/24 1:22
 * @Version 1.0
 **/
public class StructureEditRequest extends CommonRequest {

    private String id;//指标id
    private int min;//最小值
    private String inferiority;//劣势
    private String advantage;//优势
    private String advise;//建议

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public String getInferiority() {
        return inferiority;
    }

    public void setInferiority(String inferiority) {
        this.inferiority = inferiority;
    }

    public String getAdvantage() {
        return advantage;
    }

    public void setAdvantage(String advantage) {
        this.advantage = advantage;
    }

    public String getAdvise() {
        return advise;
    }

    public void setAdvise(String advise) {
        this.advise = advise;
    }
}
