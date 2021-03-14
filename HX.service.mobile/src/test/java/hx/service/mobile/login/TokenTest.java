package hx.service.mobile.login;

import hx.base.core.dao.entity.test.course.CoursePush;
import hx.base.core.dao.entity.test.papers.PapersPush;
import hx.base.core.dao.repo.jpa.test.course.CoursePushRepo;
import hx.base.core.dao.repo.jpa.test.papers.PapersPushRepo;
import hx.service.mobile.MobileApplicationTests;
import hx.service.mobile.manage.tools.MyMecachedPrefix;
import hx.service.mobile.model.login.MobileUserModel;
import net.spy.memcached.MemcachedClient;
import org.apache.commons.compress.utils.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * @ClassName TokenTest
 * @Description 生成token
 * @Author HuoJiaJin
 * @Date 2020/6/27 18:28
 * @Version 1.0
 **/
public class TokenTest extends MobileApplicationTests {

    @Autowired
    private MemcachedClient memcachedClient;
    @Autowired
    private CoursePushRepo coursePushRepo;
    @Autowired
    private PapersPushRepo papersPushRepo;

    @Test
    public void setToken(){
        setTokenUser();
        setGroupTokenUser();
        setFZGTokenUser();
    }

    @Test
    public void setTokenUser(){
        MobileUserModel model = new MobileUserModel();
        model.setName("吴云天");
        model.setFhagent_grade("FH09");
        model.setPosition_code("AS");
        model.setEmployee_code("110001787");
        model.setEmployee_part_com("861102010601");
        model.setEmployee_part_com_name("天创部");
        model.setEmployee_group_com("861102010601001");
        model.setEmployee_group_com_name("天创部直辖组");
        model.setEmployee_date("2016-07-20");
        model.setEmployee_type("1");
        model.setPosition("总监");
        memcachedClient.set(MyMecachedPrefix.mobileLoginTokenPrefix + "82bb3c229963450fbbce054bad092fc4", 7*24*60*60, model.toJson());
    }

    @Test
    public void setFZGTokenUser(){
        MobileUserModel model = new MobileUserModel();
        model.setName("谭振理");
        model.setFhagent_grade(null);
        model.setPosition_code("PBC");
        model.setEmployee_code("110002172");
        model.setEmployee_part_com("861102010611");
        model.setEmployee_part_com_name("任慧锋部");
        model.setEmployee_group_com("861102010611001");
        model.setEmployee_group_com_name("任慧锋部直辖组");
        model.setEmployee_date("2015-02-10");
        model.setEmployee_type("1");
        model.setPosition("业务主任");
        memcachedClient.set(MyMecachedPrefix.mobileLoginTokenPrefix + "57484c042a27490490881b593896a176", 7*24*60*60, model.toJson());
    }

    @Test
    public void setGroupTokenUser(){
        MobileUserModel model = new MobileUserModel();
        model.setName("刘杰");
        model.setFhagent_grade("FH10");
        model.setPosition_code("BC");
        model.setEmployee_code("110001798");
        model.setEmployee_part_com("861102010602");
        model.setEmployee_part_com_name("天佑部");
        model.setEmployee_group_com("861102010602004");
        model.setEmployee_group_com_name("天佑部刘杰组");
        model.setEmployee_date("2014-09-01");
        model.setEmployee_type("1");
        model.setPosition("组经理");
        memcachedClient.set(MyMecachedPrefix.mobileLoginTokenPrefix + "7ea849e7f26348498a6bc6d8cd383b09", 7*24*60*60, model.toJson());
    }



    public static void main(String[] args) {
        for (int i = 0; i < 50; i++) {
            echo(UUID.randomUUID().toString().replace("-", ""));
        }
    }


    @Test
    @Transactional
    @Rollback(false)
    public void deleteCourseDup(){
        List<CoursePush> list = coursePushRepo.findAll();
        System.out.println("======查询到已推送数据" + list.size() + "条");
        List<CoursePush> newList = Lists.newArrayList();
        Map<String, List<CoursePush>> maps = list.stream().collect(Collectors.groupingBy(r -> r.getAgentCode() + r.getCourseId()));
        for (Map.Entry<String, List<CoursePush>> map : maps.entrySet()) {
            CoursePush push = map.getValue().get(0);
            newList.add(push);
        }
        System.out.println("======已分组，剩余数据" + newList.size() + "条，开始删除全部数据");
        coursePushRepo.deleteAll();
        System.out.println("======已删除全部数据，开始保存新数据，");
        coursePushRepo.persistAll(newList);
    }

    @Test
    @Transactional
    @Rollback(false)
    public void deletePaperDup(){
        List<PapersPush> list = papersPushRepo.findAll();
        System.out.println("======查询到已推送数据" + list.size() + "条");
        List<PapersPush> newList = Lists.newArrayList();
        Map<String, List<PapersPush>> maps = list.stream().collect(Collectors.groupingBy(r -> r.getAgentCode() + r.getPapersId()));
        for (Map.Entry<String, List<PapersPush>> map : maps.entrySet()) {
            PapersPush push = map.getValue().get(0);
            newList.add(push);
        }
        System.out.println("======已分组，剩余数据" + newList.size() + "条，开始删除全部数据");
        papersPushRepo.deleteAll();
        System.out.println("======已删除全部数据，开始保存新数据，");
        papersPushRepo.persistAll(newList);
    }
}
