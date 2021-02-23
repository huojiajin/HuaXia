package hx.base.core.dao.repo.jpa.hualife;

import hx.base.core.dao.entity.hualife.ContinueRate;
import hx.base.core.dao.repo.jpa.common.AbstractJpaRepo;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 *@ClassName ContinueRate
 *@Description 继续率表Repo
 *@Author HuoJiaJin
 *@Date 2020/6/27 3:39
 *@Version 1.0
 **/
public interface ContinueRateRepo extends AbstractJpaRepo<ContinueRate, String> {

    @Query(" from ContinueRate where agentCode in (?1) and statMonth = ?2 and calType = 13")
    List<ContinueRate> listByAgentCodes(List<String> agentCodes, String statMonth);

    @Query(" from ContinueRate where agentCode = ?1 and statMonth = ?2 and calType = 13")
    ContinueRate findByAgentCode(String agentCode, String statMonth);

    /**
     * @Name sumByDeptCode2
     * @Author HuoJiaJin
     * @Description 计算区应收保费
     * @Date 2021/2/22 0:48
     * @Param [deptCode2, statMonth]
     * @Return java.lang.Double
     **/
    @Query("select sum(c.writtenPrem) from ContinueRate c where c.agentCode in (select m.agentCode from MarketingManpower m where m.deptCode2 = ?1) and c.statMonth = ?2 and c.calType = 13")
    Double sumByDeptCode2(String deptCode2, String statMonth);

    /**
     * @Name sumByDeptCode3
     * @Author HuoJiaJin
     * @Description 计算部应收保费
     * @Date 2021/2/22 0:48
     * @Param [deptCode3, statMonth]
     * @Return java.lang.Double
     **/
    @Query("select sum(c.writtenPrem) from ContinueRate c where c.agentCode in (select m.agentCode from MarketingManpower m where m.deptCode3 = ?1) and c.statMonth = ?2 and c.calType = 13")
    Double sumByDeptCode3(String deptCode3, String statMonth);

    /**
     * @Name sumByDeptCode4
     * @Author HuoJiaJin
     * @Description 计算组应收保费
     * @Date 2021/2/22 0:49
     * @Param [deptCode4, statMonth]
     * @Return java.lang.Double
     **/
    @Query("select sum(c.writtenPrem) from ContinueRate c where c.agentCode in (select m.agentCode from MarketingManpower m where m.deptCode4 = ?1) and c.statMonth = ?2 and c.calType = 13")
    Double sumByDeptCode4(String deptCode4, String statMonth);

    /**
     * @Name sumPaidByDeptCode2
     * @Author HuoJiaJin
     * @Description 计算区实收保费
     * @Date 2021/2/22 0:49
     * @Param [deptCode2, statMonth]
     * @Return java.lang.Double
     **/
    @Query("select sum(c.paidPrem) from ContinueRate c where c.agentCode in (select m.agentCode from MarketingManpower m where m.deptCode2 = ?1) and c.statMonth = ?2 and c.calType = 13")
    Double sumPaidByDeptCode2(String deptCode2, String statMonth);

    /**
     * @Name sumPaidByDeptCode3
     * @Author HuoJiaJin
     * @Description 计算部实收保费
     * @Date 2021/2/22 0:49
     * @Param [deptCode2, statMonth]
     * @Return java.lang.Double
     **/
    @Query("select sum(c.paidPrem) from ContinueRate c where c.agentCode in (select m.agentCode from MarketingManpower m where m.deptCode3 = ?1) and c.statMonth = ?2 and c.calType = 13")
    Double sumPaidByDeptCode3(String deptCode3, String statMonth);

    /**
     * @Name sumPaidByDeptCode4
     * @Author HuoJiaJin
     * @Description 计算组实收保费
     * @Date 2021/2/22 0:49
     * @Param [deptCode4, statMonth]
     * @Return java.lang.Double
     **/
    @Query("select sum(c.paidPrem) from ContinueRate c where c.agentCode in (select m.agentCode from MarketingManpower m where m.deptCode4 = ?1) and c.statMonth = ?2 and c.calType = 13")
    Double sumPaidByDeptCode4(String deptCode4, String statMonth);
}
