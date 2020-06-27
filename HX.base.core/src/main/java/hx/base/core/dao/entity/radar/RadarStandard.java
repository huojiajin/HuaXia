package hx.base.core.dao.entity.radar;

import hx.base.core.dao.dict.RadarStandardType;
import hx.base.core.dao.dict.RateType;
import hx.base.core.dao.dict.SectionType;
import hx.base.core.dao.entity.common.AbstractInsertTimeEntity;

import javax.persistence.*;

/**
 * @ClassName RadarStandard
 * @Description 主管经营雷达图标准
 * @Author HuoJiaJin
 * @Date 2020/6/27 2:09
 * @Version 1.0
 **/
@Entity
@Table(name = "hx_radar_standard")
public class RadarStandard extends AbstractInsertTimeEntity {

    private SectionType sectionType;//所属部门类型
    private RateType rateType;//评级类型
    private int min;//最小值
    private RadarStandardType standardType;//指标类型

    @Enumerated(EnumType.STRING)
    @Column(name = "section_type")
    public SectionType getSectionType() {
        return sectionType;
    }

    public void setSectionType(SectionType sectionType) {
        this.sectionType = sectionType;
    }

    @Enumerated(EnumType.STRING)
    @Column(name = "rate_type")
    public RateType getRateType() {
        return rateType;
    }

    public void setRateType(RateType rateType) {
        this.rateType = rateType;
    }

    @Column(name = "min")
    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    @Enumerated(EnumType.STRING)
    @Column(name = "standard_type")
    public RadarStandardType getStandardType() {
        return standardType;
    }

    public void setStandardType(RadarStandardType standardType) {
        this.standardType = standardType;
    }
}
