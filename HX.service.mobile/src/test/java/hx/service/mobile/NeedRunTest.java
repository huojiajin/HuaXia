package hx.service.mobile;

import hx.base.core.dao.dict.RateType;
import hx.base.core.dao.dict.SectionType;
import hx.base.core.dao.dict.StructureType;
import hx.base.core.dao.entity.Incubation;
import hx.base.core.dao.entity.StructureStandard;
import hx.base.core.dao.repo.jpa.IncubationRepo;
import hx.base.core.dao.repo.jpa.StructureStandardRepo;
import org.apache.commons.compress.utils.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @name: IncationTest
 * @description: 需要bean的相关测试
 * @author: huojiajin
 * @time: 2020/7/11 16:22
 */
public class NeedRunTest extends MobileApplicationTests {

    @Autowired
    private IncubationRepo incubationRepo;
    @Autowired
    private StructureStandardRepo standardRepo;

    @Test
    public void incubationTest() {
        List<Incubation> all = incubationRepo.findAll();
        echo(all.get(0));
    }

    @Test
    public void createStandard() {

        List<StructureStandard> standardList = Lists.newArrayList();
        //部类型
        for (SectionType sectionType : SectionType.values()) {
            //标准类型
            for (StructureType structureType : StructureType.values()) {
                //等级类型
                for (RateType rateType : RateType.values()) {
                    if (sectionType == SectionType.SECTION) {
                        StructureStandard standard = handleSection(sectionType, structureType, rateType);
                        standardList.add(standard);
                    }else if (sectionType == SectionType.GROUP){
                        StructureStandard standard = handleGroup(sectionType, structureType, rateType);
                        standardList.add(standard);
                    }
                }
            }
        }
        System.out.println("======拼装成功！");
        standardRepo.persistAll(standardList);
        System.out.println("======保存成功！");
    }




    private StructureStandard handleSection(SectionType sectionType, StructureType structureType, RateType rateType) {
        StructureStandard standard = new StructureStandard();
        //在册人力
        if (structureType == StructureType.ZCRL) {
            //后进
            if (rateType == RateType.LAGGINGBEHIND) {
                standard.setSectionType(sectionType);
                standard.setStructureType(structureType);
                standard.setRateType(rateType);
                standard.setMin(0);
                standard.setInferiority("团队架构弱小，未达成基础标准");
                standard.setAdvantage(null);
                standard.setAdvise("做好内部挖潜、稳定团队根基");
            }
            //高潜
            if (rateType == RateType.HIGHPOTENTIAL) {
                standard.setSectionType(sectionType);
                standard.setStructureType(structureType);
                standard.setRateType(rateType);
                standard.setMin(30);
                standard.setInferiority("团队架构较小");
                standard.setAdvantage(null);
                standard.setAdvise("大力推动新增上量，扩充队伍规模");
            }
            //优秀
            if (rateType == RateType.OUTSTANDING) {
                standard.setSectionType(sectionType);
                standard.setStructureType(structureType);
                standard.setRateType(rateType);
                standard.setMin(50);
                standard.setInferiority(null);
                standard.setAdvantage("团队架构稳定");
                standard.setAdvise("做好新增补充");
            }
            //卓越
            if (rateType == RateType.EXCELLENT) {
                standard.setSectionType(sectionType);
                standard.setStructureType(structureType);
                standard.setRateType(rateType);
                standard.setMin(80);
                standard.setInferiority(null);
                standard.setAdvantage("团队架构充盈");
                standard.setAdvise(null);
            }
        }
        //出勤人力
        if (structureType == StructureType.CQRL) {
            //后进
            if (rateType == RateType.LAGGINGBEHIND) {
                standard.setSectionType(sectionType);
                standard.setStructureType(structureType);
                standard.setRateType(rateType);
                standard.setMin(0);
                standard.setInferiority("出勤人数过少，团队虚弱");
                standard.setAdvantage(null);
                standard.setAdvise("做好人员面谈，提升出勤氛围");
            }
            //高潜
            if (rateType == RateType.HIGHPOTENTIAL) {
                standard.setSectionType(sectionType);
                standard.setStructureType(structureType);
                standard.setRateType(rateType);
                standard.setMin(10);
                standard.setInferiority("出勤人数不足");
                standard.setAdvantage(null);
                standard.setAdvise("做好人员面谈，提升出勤氛围");
            }
            //优秀
            if (rateType == RateType.OUTSTANDING) {
                standard.setSectionType(sectionType);
                standard.setStructureType(structureType);
                standard.setRateType(rateType);
                standard.setMin(15);
                standard.setInferiority(null);
                standard.setAdvantage("出勤人数基本符合要求");
                standard.setAdvise("做好二早/夕会管理，做好出勤培训");
            }
            //卓越
            if (rateType == RateType.EXCELLENT) {
                standard.setSectionType(sectionType);
                standard.setStructureType(structureType);
                standard.setRateType(rateType);
                standard.setMin(30);
                standard.setInferiority(null);
                standard.setAdvantage("出勤人数较多");
                standard.setAdvise(null);
            }
        }
        //绩优人力
        if (structureType == StructureType.JYRL) {
            //后进
            if (rateType == RateType.LAGGINGBEHIND) {
                standard.setSectionType(sectionType);
                standard.setStructureType(structureType);
                standard.setRateType(rateType);
                standard.setMin(0);
                standard.setInferiority("绩优人力不足，团队产能低");
                standard.setAdvantage(null);
                standard.setAdvise("做好绩优推动，强化绩优理念");
            }
            //高潜
            if (rateType == RateType.HIGHPOTENTIAL) {
                standard.setSectionType(sectionType);
                standard.setStructureType(structureType);
                standard.setRateType(rateType);
                standard.setMin(4);
                standard.setInferiority("绩优人力较少，团队平台不稳定");
                standard.setAdvantage(null);
                standard.setAdvise("做好团队持续经营习惯");
            }
            //优秀
            if (rateType == RateType.OUTSTANDING) {
                standard.setSectionType(sectionType);
                standard.setStructureType(structureType);
                standard.setRateType(rateType);
                standard.setMin(7);
                standard.setInferiority(null);
                standard.setAdvantage("绩优人力较多，团队平台稳定");
                standard.setAdvise("做好准绩优培养，拉升绩优人数");
            }
            //卓越
            if (rateType == RateType.EXCELLENT) {
                standard.setSectionType(sectionType);
                standard.setStructureType(structureType);
                standard.setRateType(rateType);
                standard.setMin(15);
                standard.setInferiority(null);
                standard.setAdvantage("绩优人数较多，团队产能高，主管收入稳定");
                standard.setAdvise(null);
            }
        }
        //直育组数
        if (structureType == StructureType.ZYZS) {
            //后进
            if (rateType == RateType.LAGGINGBEHIND) {
                standard.setSectionType(sectionType);
                standard.setStructureType(structureType);
                standard.setRateType(rateType);
                standard.setMin(0);
                standard.setInferiority("直育不足，未达成考核标准");
                standard.setAdvantage(null);
                standard.setAdvise("做好准主管培养");
            }
            //高潜
            if (rateType == RateType.HIGHPOTENTIAL) {
                standard.setSectionType(sectionType);
                standard.setStructureType(structureType);
                standard.setRateType(rateType);
                standard.setMin(2);
                standard.setInferiority("直育较少，架构不稳定");
                standard.setAdvantage(null);
                standard.setAdvise("做好准主管培养");
            }
            //优秀
            if (rateType == RateType.OUTSTANDING) {
                standard.setSectionType(sectionType);
                standard.setStructureType(structureType);
                standard.setRateType(rateType);
                standard.setMin(3);
                standard.setInferiority(null);
                standard.setAdvantage("维持轻松，架构稳定");
                standard.setAdvise(null);
            }
            //卓越
            if (rateType == RateType.EXCELLENT) {
                standard.setSectionType(sectionType);
                standard.setStructureType(structureType);
                standard.setRateType(rateType);
                standard.setMin(5);
                standard.setInferiority(null);
                standard.setAdvantage("架构丰满");
                standard.setAdvise(null);
            }
        }
        //实动人力
        if (structureType == StructureType.SDRL) {
            //后进
            if (rateType == RateType.LAGGINGBEHIND) {
                standard.setSectionType(sectionType);
                standard.setStructureType(structureType);
                standard.setRateType(rateType);
                standard.setMin(0);
                standard.setInferiority("实动不足");
                standard.setAdvantage(null);
                standard.setAdvise("做好活动管理、客户储备");
            }
            //高潜
            if (rateType == RateType.HIGHPOTENTIAL) {
                standard.setSectionType(sectionType);
                standard.setStructureType(structureType);
                standard.setRateType(rateType);
                standard.setMin(8);
                standard.setInferiority("实动较少");
                standard.setAdvantage(null);
                standard.setAdvise("做好实动追踪，确保实动平台");
            }
            //优秀
            if (rateType == RateType.OUTSTANDING) {
                standard.setSectionType(sectionType);
                standard.setStructureType(structureType);
                standard.setRateType(rateType);
                standard.setMin(15);
                standard.setInferiority(null);
                standard.setAdvantage("实动稳定");
                standard.setAdvise(null);
            }
            //卓越
            if (rateType == RateType.EXCELLENT) {
                standard.setSectionType(sectionType);
                standard.setStructureType(structureType);
                standard.setRateType(rateType);
                standard.setMin(25);
                standard.setInferiority(null);
                standard.setAdvantage("实动充实");
                standard.setAdvise(null);
            }
        }

        //月新增数
        if (structureType == StructureType.YXZS) {
            //后进
            if (rateType == RateType.LAGGINGBEHIND) {
                standard.setSectionType(sectionType);
                standard.setStructureType(structureType);
                standard.setRateType(rateType);
                standard.setMin(0);
                standard.setInferiority("新增不足，无增员氛围");
                standard.setAdvantage(null);
                standard.setAdvise("做好增员面谈，启动增员意愿");
            }
            //高潜
            if (rateType == RateType.HIGHPOTENTIAL) {
                standard.setSectionType(sectionType);
                standard.setStructureType(structureType);
                standard.setRateType(rateType);
                standard.setMin(5);
                standard.setInferiority("新增较少，架构补充较慢");
                standard.setAdvantage(null);
                standard.setAdvise("做好增员面谈，启动增员意愿");
            }
            //优秀
            if (rateType == RateType.OUTSTANDING) {
                standard.setSectionType(sectionType);
                standard.setStructureType(structureType);
                standard.setRateType(rateType);
                standard.setMin(8);
                standard.setInferiority(null);
                standard.setAdvantage("新增稳定，架构扩张");
                standard.setAdvise("做好方案启动，推动持续新增");
            }
            //卓越
            if (rateType == RateType.EXCELLENT) {
                standard.setSectionType(sectionType);
                standard.setStructureType(structureType);
                standard.setRateType(rateType);
                standard.setMin(15);
                standard.setInferiority(null);
                standard.setAdvantage("新增充沛，团队发展趋势向好");
                standard.setAdvise(null);
            }
        }

        //主管绩优
        if (structureType == StructureType.ZGJY) {
            //后进
            if (rateType == RateType.LAGGINGBEHIND) {
                standard.setSectionType(sectionType);
                standard.setStructureType(structureType);
                standard.setRateType(rateType);
                standard.setMin(0);
                standard.setInferiority("主管非星级，团队表率不足");
                standard.setAdvantage(null);
                standard.setAdvise("率先垂范，规划晋升星级");
            }
            //高潜
            if (rateType == RateType.HIGHPOTENTIAL) {
                standard.setSectionType(sectionType);
                standard.setStructureType(structureType);
                standard.setRateType(rateType);
                standard.setMin(4);
                standard.setInferiority("主管星级较低");
                standard.setAdvantage(null);
                standard.setAdvise("持续晋星，做好表率");
            }
            //优秀
            if (rateType == RateType.OUTSTANDING) {
                standard.setSectionType(sectionType);
                standard.setStructureType(structureType);
                standard.setRateType(rateType);
                standard.setMin(6);
                standard.setInferiority(null);
                standard.setAdvantage("主管持续绩优");
                standard.setAdvise("持续晋星");
            }
            //卓越
            if (rateType == RateType.EXCELLENT) {
                standard.setSectionType(sectionType);
                standard.setStructureType(structureType);
                standard.setRateType(rateType);
                standard.setMin(10);
                standard.setInferiority(null);
                standard.setAdvantage("主管持续绩优");
                standard.setAdvise(null);
            }
        }
        return standard;
    }

    private StructureStandard handleGroup(SectionType sectionType, StructureType structureType, RateType rateType) {
        StructureStandard standard = new StructureStandard();
        //在册人力
        if (structureType == StructureType.ZCRL) {
            //后进
            if (rateType == RateType.LAGGINGBEHIND) {
                standard.setSectionType(sectionType);
                standard.setStructureType(structureType);
                standard.setRateType(rateType);
                standard.setMin(0);
                standard.setInferiority("团队架构弱小，未达成基础标准");
                standard.setAdvantage(null);
                standard.setAdvise("做好内部挖潜、稳定团队根基");
            }
            //高潜
            if (rateType == RateType.HIGHPOTENTIAL) {
                standard.setSectionType(sectionType);
                standard.setStructureType(structureType);
                standard.setRateType(rateType);
                standard.setMin(4);
                standard.setInferiority("团队架构较小");
                standard.setAdvantage(null);
                standard.setAdvise("大力推动新增上量，扩充队伍规模");
            }
            //优秀
            if (rateType == RateType.OUTSTANDING) {
                standard.setSectionType(sectionType);
                standard.setStructureType(structureType);
                standard.setRateType(rateType);
                standard.setMin(8);
                standard.setInferiority(null);
                standard.setAdvantage("团队架构稳定");
                standard.setAdvise("做好新增补充");
            }
            //卓越
            if (rateType == RateType.EXCELLENT) {
                standard.setSectionType(sectionType);
                standard.setStructureType(structureType);
                standard.setRateType(rateType);
                standard.setMin(15);
                standard.setInferiority(null);
                standard.setAdvantage("团队架构充盈");
                standard.setAdvise(null);
            }
        }
        //出勤人力
        if (structureType == StructureType.CQRL) {
            //后进
            if (rateType == RateType.LAGGINGBEHIND) {
                standard.setSectionType(sectionType);
                standard.setStructureType(structureType);
                standard.setRateType(rateType);
                standard.setMin(0);
                standard.setInferiority("出勤人数过少，团队虚弱");
                standard.setAdvantage(null);
                standard.setAdvise("做好人员面谈，提升出勤氛围");
            }
            //高潜
            if (rateType == RateType.HIGHPOTENTIAL) {
                standard.setSectionType(sectionType);
                standard.setStructureType(structureType);
                standard.setRateType(rateType);
                standard.setMin(3);
                standard.setInferiority("出勤人数不足");
                standard.setAdvantage(null);
                standard.setAdvise("做好人员面谈，提升出勤氛围");
            }
            //优秀
            if (rateType == RateType.OUTSTANDING) {
                standard.setSectionType(sectionType);
                standard.setStructureType(structureType);
                standard.setRateType(rateType);
                standard.setMin(5);
                standard.setInferiority(null);
                standard.setAdvantage("出勤人数基本符合要求");
                standard.setAdvise("做好二早/夕会管理，做好出勤培训");
            }
            //卓越
            if (rateType == RateType.EXCELLENT) {
                standard.setSectionType(sectionType);
                standard.setStructureType(structureType);
                standard.setRateType(rateType);
                standard.setMin(8);
                standard.setInferiority(null);
                standard.setAdvantage("出勤人数较多");
                standard.setAdvise(null);
            }
        }
        //绩优人力
        if (structureType == StructureType.JYRL) {
            //后进
            if (rateType == RateType.LAGGINGBEHIND) {
                standard.setSectionType(sectionType);
                standard.setStructureType(structureType);
                standard.setRateType(rateType);
                standard.setMin(0);
                standard.setInferiority("绩优人力不足，团队产能低");
                standard.setAdvantage(null);
                standard.setAdvise("做好绩优推动，强化绩优理念");
            }
            //高潜
            if (rateType == RateType.HIGHPOTENTIAL) {
                standard.setSectionType(sectionType);
                standard.setStructureType(structureType);
                standard.setRateType(rateType);
                standard.setMin(1);
                standard.setInferiority("绩优人力较少，团队平台不稳定");
                standard.setAdvantage(null);
                standard.setAdvise("做好团队持续经营习惯");
            }
            //优秀
            if (rateType == RateType.OUTSTANDING) {
                standard.setSectionType(sectionType);
                standard.setStructureType(structureType);
                standard.setRateType(rateType);
                standard.setMin(2);
                standard.setInferiority(null);
                standard.setAdvantage("绩优人力较多，团队平台稳定");
                standard.setAdvise("做好准绩优培养，拉升绩优人数");
            }
            //卓越
            if (rateType == RateType.EXCELLENT) {
                standard.setSectionType(sectionType);
                standard.setStructureType(structureType);
                standard.setRateType(rateType);
                standard.setMin(4);
                standard.setInferiority(null);
                standard.setAdvantage("绩优人数较多，团队产能高，主管收入稳定");
                standard.setAdvise(null);
            }
        }
        //直育组数
        if (structureType == StructureType.ZYZS) {
            //后进
            if (rateType == RateType.LAGGINGBEHIND) {
                standard.setSectionType(sectionType);
                standard.setStructureType(structureType);
                standard.setRateType(rateType);
                standard.setMin(0);
                standard.setInferiority("直育不足，未达成考核标准");
                standard.setAdvantage(null);
                standard.setAdvise("做好准主管培养");
            }
            //高潜
            if (rateType == RateType.HIGHPOTENTIAL) {
                standard.setSectionType(sectionType);
                standard.setStructureType(structureType);
                standard.setRateType(rateType);
                standard.setMin(2);
                standard.setInferiority("直育较少，架构不稳定");
                standard.setAdvantage(null);
                standard.setAdvise("做好准主管培养");
            }
            //优秀
            if (rateType == RateType.OUTSTANDING) {
                standard.setSectionType(sectionType);
                standard.setStructureType(structureType);
                standard.setRateType(rateType);
                standard.setMin(3);
                standard.setInferiority(null);
                standard.setAdvantage("维持轻松，架构稳定");
                standard.setAdvise(null);
            }
            //卓越
            if (rateType == RateType.EXCELLENT) {
                standard.setSectionType(sectionType);
                standard.setStructureType(structureType);
                standard.setRateType(rateType);
                standard.setMin(5);
                standard.setInferiority(null);
                standard.setAdvantage("架构丰满");
                standard.setAdvise(null);
            }
        }
        //实动人力
        if (structureType == StructureType.SDRL) {
            //后进
            if (rateType == RateType.LAGGINGBEHIND) {
                standard.setSectionType(sectionType);
                standard.setStructureType(structureType);
                standard.setRateType(rateType);
                standard.setMin(0);
                standard.setInferiority("实动不足");
                standard.setAdvantage(null);
                standard.setAdvise("做好活动管理、客户储备");
            }
            //高潜
            if (rateType == RateType.HIGHPOTENTIAL) {
                standard.setSectionType(sectionType);
                standard.setStructureType(structureType);
                standard.setRateType(rateType);
                standard.setMin(3);
                standard.setInferiority("实动较少");
                standard.setAdvantage(null);
                standard.setAdvise("做好实动追踪，确保实动平台");
            }
            //优秀
            if (rateType == RateType.OUTSTANDING) {
                standard.setSectionType(sectionType);
                standard.setStructureType(structureType);
                standard.setRateType(rateType);
                standard.setMin(7);
                standard.setInferiority(null);
                standard.setAdvantage("实动稳定");
                standard.setAdvise(null);
            }
            //卓越
            if (rateType == RateType.EXCELLENT) {
                standard.setSectionType(sectionType);
                standard.setStructureType(structureType);
                standard.setRateType(rateType);
                standard.setMin(10);
                standard.setInferiority(null);
                standard.setAdvantage("实动充实");
                standard.setAdvise(null);
            }
        }

        //月新增数
        if (structureType == StructureType.YXZS) {
            //后进
            if (rateType == RateType.LAGGINGBEHIND) {
                standard.setSectionType(sectionType);
                standard.setStructureType(structureType);
                standard.setRateType(rateType);
                standard.setMin(0);
                standard.setInferiority("新增不足，无增员氛围");
                standard.setAdvantage(null);
                standard.setAdvise("做好增员面谈，启动增员意愿");
            }
            //高潜
            if (rateType == RateType.HIGHPOTENTIAL) {
                standard.setSectionType(sectionType);
                standard.setStructureType(structureType);
                standard.setRateType(rateType);
                standard.setMin(2);
                standard.setInferiority("新增较少，架构补充较慢");
                standard.setAdvantage(null);
                standard.setAdvise("做好增员面谈，启动增员意愿");
            }
            //优秀
            if (rateType == RateType.OUTSTANDING) {
                standard.setSectionType(sectionType);
                standard.setStructureType(structureType);
                standard.setRateType(rateType);
                standard.setMin(3);
                standard.setInferiority(null);
                standard.setAdvantage("新增稳定，架构扩张");
                standard.setAdvise("做好方案启动，推动持续新增");
            }
            //卓越
            if (rateType == RateType.EXCELLENT) {
                standard.setSectionType(sectionType);
                standard.setStructureType(structureType);
                standard.setRateType(rateType);
                standard.setMin(5);
                standard.setInferiority(null);
                standard.setAdvantage("新增充沛，团队发展趋势向好");
                standard.setAdvise(null);
            }
        }

        //主管绩优
        if (structureType == StructureType.ZGJY) {
            //后进
            if (rateType == RateType.LAGGINGBEHIND) {
                standard.setSectionType(sectionType);
                standard.setStructureType(structureType);
                standard.setRateType(rateType);
                standard.setMin(0);
                standard.setInferiority("主管非星级，团队表率不足");
                standard.setAdvantage(null);
                standard.setAdvise("率先垂范，规划晋升星级");
            }
            //高潜
            if (rateType == RateType.HIGHPOTENTIAL) {
                standard.setSectionType(sectionType);
                standard.setStructureType(structureType);
                standard.setRateType(rateType);
                standard.setMin(4);
                standard.setInferiority("主管星级较低");
                standard.setAdvantage(null);
                standard.setAdvise("持续晋星，做好表率");
            }
            //优秀
            if (rateType == RateType.OUTSTANDING) {
                standard.setSectionType(sectionType);
                standard.setStructureType(structureType);
                standard.setRateType(rateType);
                standard.setMin(6);
                standard.setInferiority(null);
                standard.setAdvantage("主管持续绩优");
                standard.setAdvise("持续晋星");
            }
            //卓越
            if (rateType == RateType.EXCELLENT) {
                standard.setSectionType(sectionType);
                standard.setStructureType(structureType);
                standard.setRateType(rateType);
                standard.setMin(10);
                standard.setInferiority(null);
                standard.setAdvantage("主管持续绩优");
                standard.setAdvise(null);
            }
        }
        return standard;
    }
}
