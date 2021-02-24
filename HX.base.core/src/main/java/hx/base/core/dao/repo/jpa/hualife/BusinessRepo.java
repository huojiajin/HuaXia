package hx.base.core.dao.repo.jpa.hualife;

import hx.base.core.dao.entity.hualife.Business;
import hx.base.core.dao.repo.jpa.common.AbstractJpaRepo;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

/**
 *@ClassName BusinessRepo
 *@Description 业务清单表Repo
 *@Author HuoJiaJin
 *@Date 2020/6/27 2:32
 *@Version 1.0
 **/
public interface BusinessRepo extends AbstractJpaRepo<Business, String> {

    @Query("select sum(writtenStadPrem) from Business where deptCode2 = ?1 and issueDate >= ?2 and issueDate < ?3 and agentState = '在职'")
    Double sumByDeptCode2(String deptCode2, LocalDate issueDateStart, LocalDate issueDateEnd);

    @Query("select sum(writtenStadPrem) from Business where deptCode3 = ?1 and issueDate >= ?2 and issueDate < ?3 and agentState = '在职'")
    Double sumByDeptCode3(String deptCode3, LocalDate issueDateStart, LocalDate issueDateEnd);

    @Query("select sum(writtenStadPrem) from Business where deptCode4 = ?1 and issueDate >= ?2 and issueDate < ?3 and agentState = '在职'")
    Double sumByDeptCode4(String deptCode4, LocalDate issueDateStart, LocalDate issueDateEnd);

    @Query("select sum(writtenStadPrem) from Business where agentCode = ?1 and issueDate >= ?2 and issueDate < ?3 and agentState = '在职'")
    Double sumByAgentCode(String agentCode, LocalDate issueDateStart, LocalDate issueDateEnd);

    @Query("from Business where deptCode3 = ?1 and issueDate >= ?2 and issueDate < ?3 and agentState = '在职'")
    List<Business> listByDeptCode3(String deptCode3, LocalDate issueDateStart, LocalDate issueDateEnd);

    @Query("from Business where deptCode4 = ?1 and issueDate >= ?2 and issueDate < ?3 and agentState = '在职'")
    List<Business> listByDeptCode4(String deptCode4, LocalDate issueDateStart, LocalDate issueDateEnd);

    @Query("from Business where agentCode = ?1 and issueDate >= ?2 and issueDate < ?3 and agentState = '在职'")
    List<Business> listByAgentCode(String agentCode, LocalDate issueDateStart, LocalDate issueDateEnd);

    @Query("select agentCode, sum(writtenStadPrem) from Business where deptCode4 = ?1 and issueDate >= ?2 and issueDate < ?3 and agentState = '在职' group by agentCode")
    List<Object[]> listSumByGroup(String groupCode, LocalDate issueDateStart, LocalDate issueDateEnd);

    @Query("select agentCode, sum(writtenStadPrem) from Business where deptCode3 = ?1 and issueDate >= ?2 and issueDate < ?3 and agentState = '在职' group by agentCode")
    List<Object[]> listSumBySection(String sectionCode, LocalDate issueDateStart, LocalDate issueDateEnd);

    @Query("select sum(preStadPrem) from Business where deptCode1 = ?1 and receiveDate >= ?2 and receiveDate < ?3")
    Double sumPreByDeptCode1(String deptCode1, LocalDate receiveDateStart, LocalDate receiveDateEnd);

    @Query("select sum(writtenStadPrem) from Business where deptCode1 = ?1 and issueDate >= ?2 and issueDate < ?3")
    Double sumByDeptCode1(String deptCode1, LocalDate issueDateStart, LocalDate issueDateEnd);

    /**
     * @Name campRealAction
     * @Author HuoJiaJin
     * @Description 营服实动或万元
     * @Date 2021/2/7 0:36
     * @Param [campCode, issueDateStart, issueDateEnd, minStadprem]
     * @Return java.lang.Integer
     **/
    @Query("select count(agentCode) from Business where deptCode1 = ?1 and issueDate >= ?2 and issueDate < ?3 " +
            "group by agentCode having sum(writtenStadPrem) >= ?4")
    Integer campRealAction(String campCode, LocalDate issueDateStart, LocalDate issueDateEnd, long minStadprem);

    /**
     * @Name newRealAction
     * @Author HuoJiaJin
     * @Description 新增实动或万元
     * @Date 2021/2/7 0:36
     * @Param [campCode, employDateStart, employDateEnd, minStadprem]
     * @Return java.lang.Integer
     **/
    @Query("select count(agentCode) from Business where deptCode1 = ?1 and employDate >= ?2 and employDate < ?3 " +
            "group by agentCode having sum(writtenStadPrem) >= ?4")
    Integer newRealAction(String campCode, LocalDate employDateStart, LocalDate employDateEnd, long minStadprem);

    /**
     * @Name sumByDeptCode1AndEmployeeDate
     * @Author HuoJiaJin
     * @Description 营服新增保费贡献
     * @Date 2021/2/7 1:05
     * @Param [deptCode1, employDateStart, employDateEnd]
     * @Return java.lang.Double
     **/
    @Query("select sum(writtenStadPrem) from Business where deptCode1 = ?1 and employDate >= ?2 and employDate < ?3")
    Double sumByDeptCode1AndEmployeeDate(String deptCode1, LocalDate employDateStart, LocalDate employDateEnd);

    /**
     * @Name executiveRealAction
     * @Author HuoJiaJin
     * @Description 主管实动
     * @Date 2021/2/7 1:24
     * @Param [campCode, issueDateStart, issueDateEnd, gradeList]
     * @Return java.lang.Integer
     **/
    @Query("select count(agentCode) from Business where deptCode1 = ?1 and issueDate >= ?2 and issueDate < ?3 and agentGrade in ?4" +
            "group by agentCode having sum(writtenStadPrem) >= 5000")
    Integer executiveRealAction(String campCode, LocalDate issueDateStart, LocalDate issueDateEnd, List<String> gradeList);

    /**
     * @Name starContribution
     * @Author HuoJiaJin
     * @Description 星级保费贡献
     * @Date 2021/2/7 2:04
     * @Param [deptCode1, issueDateStart, issueDateEnd]
     * @Return java.lang.Double
     **/
    @Query("select sum(b.writtenStadPrem) from Business b, StarRating s where b.deptCode1 = ?1 and b.issueDate >= ?2 and b.issueDate < ?3 and b.agentCode = s.agentCode")
    Double starContribution(String deptCode1, LocalDate issueDateStart, LocalDate issueDateEnd);

    /**
     * @Name sumFYCByDeptCode2
     * @Author HuoJiaJin
     * @Description 计算总监区FYC
     * @Date 2021/2/22 0:34
     * @Param [deptCode2, receiveDateStart, receiveDateEnd]
     * @Return java.lang.Double
     **/
    @Query("select sum(writteFyc) from Business where deptCode2 = ?1 and issueDate >= ?2 and issueDate < ?3")
    Double sumFYCByDeptCode2(String deptCode2, LocalDate issueDateStart, LocalDate issueDateEnd);

    /**
     * @Name sumFYCByDeptCode3
     * @Author HuoJiaJin
     * @Description 计算部FYC
     * @Date 2021/2/22 0:35
     * @Param [deptCode3, issueDateStart, issueDateEnd]
     * @Return java.lang.Double
     **/
    @Query("select sum(writteFyc) from Business where deptCode3 = ?1 and issueDate >= ?2 and issueDate < ?3")
    Double sumFYCByDeptCode3(String deptCode3, LocalDate issueDateStart, LocalDate issueDateEnd);

    /**
     * @Name sumFYCByDeptCode4
     * @Author HuoJiaJin
     * @Description 计算组FYC
     * @Date 2021/2/22 0:35
     * @Param [deptCode4, issueDateStart, issueDateEnd]
     * @Return java.lang.Double
     **/
    @Query("select sum(writteFyc) from Business where deptCode4 = ?1 and issueDate >= ?2 and issueDate < ?3")
    Double sumFYCByDeptCode4(String deptCode4, LocalDate issueDateStart, LocalDate issueDateEnd);

    /**
     * @Name sumFYCByAgentCode
     * @Author HuoJiaJin
     * @Description 计算个人FYC
     * @Date 2021/2/22 0:35
     * @Param [agentCode, issueDateStart, issueDateEnd]
     * @Return java.lang.Double
     **/
    @Query("select sum(writteFyc) from Business where agentCode = ?1 and issueDate >= ?2 and issueDate < ?3")
    Double sumFYCByAgentCode(String agentCode, LocalDate issueDateStart, LocalDate issueDateEnd);

    /**
     * @Name sumFYCByDeptCode2
     * @Author HuoJiaJin
     * @Description 总监区健康人力
     * @Date 2021/2/22 0:34
     * @Param [deptCode2, receiveDateStart, receiveDateEnd]
     * @Return java.lang.Double
     **/
    @Query("select agentCode, sum(writteFyc) from Business where deptCode2 = ?1 and receiveDate >= ?2 and receiveDate < ?3 group by agentCode having sum(writteFyc) >= 1500")
    List<Object[]> healthByDeptCode2(String deptCode2, LocalDate receiveDateStart, LocalDate receiveDateEnd);

    /**
     * @Name sumFYCByDeptCode3
     * @Author HuoJiaJin
     * @Description 部健康人力
     * @Date 2021/2/22 0:35
     * @Param [deptCode3, issueDateStart, issueDateEnd]
     * @Return java.lang.Double
     **/
    @Query("select agentCode, sum(writteFyc) from Business where deptCode3 = ?1 and issueDate >= ?2 and issueDate < ?3 group by agentCode having sum(writteFyc) >= 1500")
    List<Object[]> healthByDeptCode3(String deptCode3, LocalDate issueDateStart, LocalDate issueDateEnd);

    /**
     * @Name sumFYCByDeptCode4
     * @Author HuoJiaJin
     * @Description 组健康人力
     * @Date 2021/2/22 0:35
     * @Param [deptCode4, issueDateStart, issueDateEnd]
     * @Return java.lang.Double
     **/
    @Query("select agentCode, sum(writteFyc) from Business where deptCode4 = ?1 and issueDate >= ?2 and issueDate < ?3 group by agentCode having sum(writteFyc) >= 1500")
    List<Object[]> healthByDeptCode4(String deptCode4, LocalDate issueDateStart, LocalDate issueDateEnd);

}
