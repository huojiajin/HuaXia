package hx.base.core.dao.repo.jpa.radar;

import hx.base.core.dao.dict.acl.SectionType;
import hx.base.core.dao.dict.structure.RateType;
import hx.base.core.dao.entity.radar.RadarGrade;
import hx.base.core.dao.repo.jpa.common.AbstractJpaRepo;
import org.springframework.data.jpa.repository.Query;

/**
 *@ClassName RadarGradeRepo
 *@Description 雷达图评级表Repo
 *@Author HuoJiaJin
 *@Date 2020/6/26 17:46
 *@Version 1.0
 **/
public interface RadarGradeRepo extends AbstractJpaRepo<RadarGrade, String> {

    @Query(" from RadarGrade where code = ?1 and month = ?2 and sectionType = ?3")
    RadarGrade findByCode(String code, String month, SectionType sectionType);

    @Query("select count(id) from RadarGrade where month = ?1 and sectionType = ?2 and rateType = ?3")
    Integer countByMonth(String month, SectionType sectionType, RateType rateType);
}
