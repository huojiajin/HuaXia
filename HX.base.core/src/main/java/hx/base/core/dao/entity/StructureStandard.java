package hx.base.core.dao.entity;

import hx.base.core.dao.dict.RateType;
import hx.base.core.dao.dict.SectionType;
import hx.base.core.dao.dict.StructureType;
import hx.base.core.dao.entity.common.AbstractInsertTimeEntity;

import javax.persistence.*;

/**
 * @name: StructureStandard
 * @description: 组织架构标准表
 * @author: huojiajin
 * @time: 2020/7/6 16:52
 */
@Entity
@Table(name = "hx_structure_standard")
public class StructureStandard extends AbstractInsertTimeEntity {

    private SectionType sectionType;//部门类型
    private RateType rateType;//等级类型
    private StructureType structureType;//架构指标类型
    private Integer min;//最小值
    private String inferiority;//劣势
    private String advantage;//优势
    private String advise;//建议

    @Enumerated(EnumType.STRING)
    @Column(name = "section_type", nullable = false)
    public SectionType getSectionType() {
        return sectionType;
    }

    public void setSectionType(SectionType sectionType) {
        this.sectionType = sectionType;
    }

    @Enumerated(EnumType.STRING)
    @Column(name = "rate_type", nullable = false)
    public RateType getRateType() {
        return rateType;
    }

    public void setRateType(RateType rateType) {
        this.rateType = rateType;
    }

    @Enumerated(EnumType.STRING)
    @Column(name = "structrue_type", nullable = false)
    public StructureType getStructureType() {
        return structureType;
    }

    public void setStructureType(StructureType structureType) {
        this.structureType = structureType;
    }

    @Column(name = "min", nullable = false)
    public Integer getMin() {
        return min;
    }

    public void setMin(Integer min) {
        this.min = min;
    }

    @Column(name = "inferiority", nullable = false)
    public String getInferiority() {
        return inferiority;
    }

    public void setInferiority(String inferiority) {
        this.inferiority = inferiority;
    }

    @Column(name = "advantage", nullable = false)
    public String getAdvantage() {
        return advantage;
    }

    public void setAdvantage(String advantage) {
        this.advantage = advantage;
    }

    @Column(name = "advise", nullable = false)
    public String getAdvise() {
        return advise;
    }

    public void setAdvise(String advise) {
        this.advise = advise;
    }
}
