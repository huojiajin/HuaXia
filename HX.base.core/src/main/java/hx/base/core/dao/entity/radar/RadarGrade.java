package hx.base.core.dao.entity.radar;

import hx.base.core.dao.dict.structure.RateType;
import hx.base.core.dao.dict.acl.SectionType;
import hx.base.core.dao.entity.common.AbstractInsertTimeEntity;

import javax.persistence.*;

/**
 * @ClassName RadarGrade
 * @Description 雷达图评级表
 * @Author HuoJiaJin
 * @Date 2020/6/26 17:35
 * @Version 1.0
 **/
@Entity
@Table(name = "hx_radar_grade", indexes = {
        @Index(columnList = "code", name = "hx_radar_grade_index")
})
public class RadarGrade extends AbstractInsertTimeEntity {

    private String month;//yyyy-MM
    private SectionType sectionType;//所属部门类型
    private RateType rateType;//评级类型
    private String code;//所属部门编码

    @Column(name = "month")
    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

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

    @Column(name = "code")
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
