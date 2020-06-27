package hx.service.mobile;

import hx.service.mobile.manage.MyMecachedPrefix;
import hx.service.mobile.manage.model.login.MobileUserModel;
import net.spy.memcached.MemcachedClient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

/**
 * @ClassName TokenTest
 * @Description 生成token
 * @Author HuoJiaJin
 * @Date 2020/6/27 18:28
 * @Version 1.0
 **/
public class TokenTest extends MobileApplicationTests{

    @Autowired
    private MemcachedClient memcachedClient;

    @Test
    public void setTokenUser(){
        String token = UUID.randomUUID().toString().replace("-", "");
        MobileUserModel model = new MobileUserModel();
        model.setName("吴云天");
        model.setFhagent_grade("FH09");
        model.setPosition_code("AS");
        model.setEmployee_code("110001787");
        model.setEmployee_part_com("861102010601");
        model.setEmployee_part_com_name("天创部");
        model.setEmployee_group_com("861102010601001");
        model.setEmployee_group_com_name("天创部直辖组");
        memcachedClient.set(MyMecachedPrefix.mobileLoginTokenPrefix + token, 60*60, model.toJson());
        echo(token);
    }


    public static void main(String[] args) {
        for (int i = 0; i < 50; i++) {
            echo(UUID.randomUUID().toString().replace("-", ""));
        }
    }
}
