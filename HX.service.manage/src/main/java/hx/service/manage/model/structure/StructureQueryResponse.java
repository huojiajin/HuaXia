package hx.service.manage.model.structure;

import hx.base.core.dao.entity.common.BaseEntity;

/**
 * @ClassName: StructureQueryResponse
 * @Description: 组织架构查询返回Response
 * @Author HuoJiaJin
 * @Date 2021/2/24 1:24
 * @Version 1.0
 **/
public class StructureQueryResponse extends BaseEntity {

    private String id;//指标id
    private int sectionType;//部门类型
    private int rateType;//等级类型
    private int structureType;//架构指标类型
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

    public int getSectionType() {
        return sectionType;
    }

    public void setSectionType(int sectionType) {
        this.sectionType = sectionType;
    }

    public int getRateType() {
        return rateType;
    }

    public void setRateType(int rateType) {
        this.rateType = rateType;
    }

    public int getStructureType() {
        return structureType;
    }

    public void setStructureType(int structureType) {
        this.structureType = structureType;
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
