package hx.service.mobile.manage.login;

import hx.service.manage.manage.common.AbstractManager;
import hx.service.manage.manage.model.CommonRequest;
import hx.service.manage.manage.model.CommonResponse;
import hx.service.mobile.manage.model.LoginInfoResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Random;

/**
 * @ClassName LoginManagerImpl
 * @Description 登录Manager
 * @Author HuoJiaJin
 * @Date 2020/6/23 21:46
 * @Version 1.0
 **/
@Service
public class LoginManagerImpl extends AbstractManager implements LoginManager {

    @Value("${url}")
    private String url;
    @Value("${appId}")
    private String appId;
    @Value("${secretKey}")
    private String secretKey;

    @Override
    public String loginInfo(CommonRequest request) {
        CommonResponse response = new CommonResponse();
        LoginInfoResponse infoResponse = new LoginInfoResponse();
        infoResponse.setApp_id(appId);
        //生成十位随机数
        String nonce = "";
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            nonce += String.valueOf(random.nextInt(10));
        }
        infoResponse.setNonce(nonce);
        long timeStampSec = System.currentTimeMillis()/1000;
        String timestamp = String.format("%010d", timeStampSec);
        infoResponse.setTimestamp(timestamp);

    }

}
