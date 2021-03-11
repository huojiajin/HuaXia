package hx.service.manage.manage.quartz;

import hx.base.core.dao.dict.share.ShareType;
import hx.base.core.dao.entity.share.ShareData;
import hx.base.core.dao.repo.jpa.share.ShareDataRepo;
import hx.base.core.manage.annotation.MyScheduler;
import hx.base.core.manage.model.AccessTokenModel;
import hx.base.core.manage.model.HXCommonResponse;
import hx.base.core.manage.tools.JsonTools;
import hx.base.core.manage.tools.httpclient.HttpClientHelper;
import net.spy.memcached.MemcachedClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.net.URI;

/**
 * @name: AccessTokenQuartz
 * @description: 定时生成总部accessToken
 * @author: huojiajin
 * @time: 2021/3/11 15:07
 */
@Service
@MyScheduler(name = "ACCESS_TOKEN", cron = "0 0 * * * ?")
public class AccessTokenQuartz extends CommonQuartz {

    @Value("${appId}")
    protected String appId;
    @Value("${secretKey}")
    protected String secretKey;
    @Value("${url}")
    protected String url;
    @Autowired
    protected MemcachedClient memcachedClient;
    @Autowired
    protected ShareDataRepo shareDataRepo;

    @PostConstruct
    public void initAccessToken(){
        //启动时初始化Token
        createToken();
        logger.info("======accessToken init success");
    }

    @Override
    public void run() {
        createToken();
    }

    private void createToken() {
        HXCommonResponse<AccessTokenModel> response;
        try {
            String responseStr = HttpClientHelper.httpGet(new URI(url + "/oauth2/token?app_id=" + appId + "&secret_key=" + secretKey), "UTF-8");
            response = JsonTools.json2Object(responseStr, HXCommonResponse.class, AccessTokenModel.class);
        } catch (Exception e) {
            logger.error("", e);
            return;
        }
        if (!response.getCode().equals("0")) {
            logger.error("======生成accessToken失败，错误原因为：{}", response.getMessage());
            return;
        }else {
            String accessToken = response.getData().getAccess_token();
            ShareData data = shareDataRepo.findByType(ShareType.ACCESSTOKEN);
            if (data == null){
                data = new ShareData();
                data.setType(ShareType.ACCESSTOKEN);
                data.setData(accessToken);
            }
            shareDataRepo.save(data);
        }
    }
}
