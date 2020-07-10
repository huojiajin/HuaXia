package hx.service.mobile;

import hx.base.core.manage.tools.JsonTools;
import hx.base.core.manage.tools.httpclient.HttpClientHelper;
import hx.service.mobile.manage.model.common.AccessTokenModel;
import hx.service.mobile.manage.model.common.HXCommonResponse;
import hx.service.mobile.manage.model.login.LoginInfoResponse;

import java.io.IOException;
import java.net.URI;

/**
 * @name: HXAPITest
 * @description: TODO
 * @author: huojiajin
 * @time: 2020/7/5 16:31
 */
public class HXAPITest {

    public static void main(String[] args) throws IOException {
//        HXCommonResponse<AccessTokenModel> response;
//        String appId = "3c1ae45d-24e9-4300-bda5-18f15a89cf31";
//        String secretKey = "rJev4P6gOKVjV8SX45y22d3D6RlNbNyH";
//        try {
//            String responseStr = HttpClientHelper.httpGet(new URI("https://open-int.ihxlife.com/oauth2/token?app_id=" + appId + "&secret_key=" + secretKey), "UTF-8");
//            System.out.println(responseStr);
//            response = JsonTools.json2Object(responseStr, HXCommonResponse.class, AccessTokenModel.class);
//        } catch (Exception e) {
//            e.printStackTrace();
//            throw new IOException(e.getMessage());
//        }
//        if (!response.getCode().equals("0")) {
//            System.out.println(response.getMessage());
//            throw new IOException(response.getMessage());
//        }
//        String accessToken = response.getData().getAccess_token();
//        System.out.println(accessToken);

        String url = "https://open-int.ihxlife.com/oauth2/token/api/v2/uc/user/login/index";
    }
}
