package hx.service.mobile.manage;

import hx.base.core.manage.common.CommonAbstract;
import hx.base.core.manage.tools.JsonTools;
import hx.base.core.manage.tools.httpclient.HttpClientHelper;
import hx.service.mobile.manage.model.common.AccessTokenModel;
import hx.service.mobile.manage.model.common.HXCommonResponse;
import hx.service.mobile.manage.model.login.MobileUserModel;
import net.spy.memcached.MemcachedClient;
import org.apache.commons.codec.binary.Base64;
import org.apache.http.HttpEntity;
import org.apache.http.StatusLine;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.Map;

/**
 * @ClassName AbstractMobileManager
 * @Description 移动端公用Manager
 * @Author HuoJiaJin
 * @Date 2020/6/26 13:52
 * @Version 1.0
 **/
public abstract class AbstractMobileManager extends CommonAbstract {

    @Value("${serialNo}")
    private String serialNo;
    @Value("${appId}")
    protected String appId;
    @Value("${secretKey}")
    protected String secretKey;
    @Value("${url}")
    protected String url;
    @Autowired
    protected MemcachedClient memcachedClient;

    /**
     * @Name post
     * @Author HuoJiaJin
     * @Description 请求华夏APIpost方法
     * @Date 2020/6/26
     * @Param [serviceUrl, body]
     * @return java.lang.String
     **/
    public String hxPost(String serviceUrl, String body) throws IOException {
        logger.info("请求网关的路径:【{}】",serviceUrl);
        CloseableHttpClient httpclient = HttpClients.createDefault();
        CloseableHttpResponse response = null;
        try {
            HttpPost httpPost = new HttpPost(serviceUrl);
            //设置请求及响应时间
            RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(5000).setSocketTimeout(5000).build();
            httpPost.setConfig(requestConfig);
            //设置请求头
            httpPost.setHeader("Content-type", "application/json");
            String headerToken = getToken();//先获取token
            String headerSerialNo = getSerialNo();
            httpPost.setHeader("Authorization",headerToken);
            httpPost.setHeader("serial_no",headerSerialNo);
            //添加请求体
            StringEntity stringEntity = new StringEntity(body, "UTF-8");
            logger.info("请求体是【{}】",stringEntity);
            httpPost.setEntity(stringEntity);
            String result = null;
            response = httpclient.execute(httpPost);
            HttpEntity entity = response.getEntity();
            if (entity == null)
            {
                logger.info("Http response's entity is null.");
                return "";
            }
            StatusLine sl = response.getStatusLine();
            int code = sl.getStatusCode();
            HttpEntity resEntity = entity;
            String res = EntityUtils.toString(resEntity, StandardCharsets.UTF_8.name());
            int rawLen = res == null ? 0 : res.length();
            logger.debug("response entity length,transfer length:{} ;raw lengthr:{}", resEntity.getContentLength(), rawLen);
            if (code >= 400) throw new IOException("Request Error:" + sl + "\n" + res);
            return result;
        } catch (UnsupportedEncodingException e) {
            logger.info("错误信息为：[{}]",e);
            throw new IOException("请求参数缺失");
        } catch (IOException e) {
            logger.info("错误信息为：[{}]",e);
            throw new IOException("连接服务器失败");
        } finally{
            try {
                if (response != null) {
                    response.close();
                }
                httpclient.close();
            } catch (IOException e) {
                logger.error("response为空",e);
                throw new IOException("response为空");
            }
        }
    }

    private String getSerialNo() {
        long timeStampSec = System.currentTimeMillis()/1000;
        String timestamp = String.format("%010d", timeStampSec);
        return serialNo + timestamp;
    }

    private String getToken() throws IOException {
        Object accessTokenObj = memcachedClient.get("mobile:accessToken");
        if (null == accessTokenObj){
            HXCommonResponse<AccessTokenModel> response;
            try {
                String responseStr = HttpClientHelper.httpGet(new URI(url + "/oauth2/token?app_id=" + appId + "&secret_key=" + secretKey), "UTF-8");
                response = JsonTools.json2Object(responseStr, HXCommonResponse.class, AccessTokenModel.class);
            } catch (Exception e) {
                logger.error("", e);
                throw new IOException(e.getMessage());
            }
            if (!response.getCode().equals("0")) throw new IOException(response.getMessage());
            String accessToken = response.getData().getAccess_token();
            memcachedClient.set("mobile:accessToken", 60*60, accessToken);
            return accessToken;
        }else {
            return (String)accessTokenObj;
        }
    }

    /**
     * @Name doGet
     * @Author HuoJiaJin
     * @Description 请求华夏APIget方法
     * @Date 2020/6/26
     * @Param [url, params, headers]
     * @return java.lang.String
     **/
    public String hxDoGet(String url, Map<String, String> params) throws IOException {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        String resultString = "";
        CloseableHttpResponse response = null;
        try {
            // 创建uri
            URIBuilder builder = new URIBuilder(url);
            if (params != null) {
                logger.info("请求参数【{}】",params);
                for (String param : params.keySet()) {
                    builder.addParameter(param, params.get(param));
                }
            }
            URI uri = builder.build();
            // 创建http GET请求
            logger.info("请求路径【{}】",uri);
            HttpGet httpGet = new HttpGet(uri);
            RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(5000).setSocketTimeout(5000).build();
            httpGet.setConfig(requestConfig);
            //添加请求头
            String accessToken = getToken();
            String serialNo = getSerialNo();
            httpGet.addHeader("Authorization", accessToken);
            httpGet.addHeader("serial_no", serialNo);
            logger.info("开始执行Get请求");
            // 执行请求
            response = httpclient.execute(httpGet);
            // 判断返回状态是否为200
            if (response.getStatusLine().getStatusCode() == 200) {
                resultString = EntityUtils.toString(response.getEntity(), "UTF-8");
                logger.info("请求成功，响应实体【{}】",resultString);
            }else {
                String errorMsg = EntityUtils.toString(response.getEntity(),"UTF-8").trim();
                logger.error("失败HTTP状态码:【{}】失败信息【{}】",response.getStatusLine().getStatusCode(), errorMsg);
                throw new Exception(errorMsg);
            }
        } catch (Exception e) {
            logger.error("Get请求发生异常",e);
        } finally {
            try {
                if (response != null) {
                    response.close();
                }
                httpclient.close();
            } catch (IOException e) {
                logger.error("response为空",e);
                throw e;
            }
        }
        return resultString;
    }

    protected MobileUserModel getUser(String token){
        Object userObj = memcachedClient.get(MyMecachedPrefix.mobileLoginTokenPrefix + token);
        if (userObj == null){
            logger.error("======用户未登录，token:{}", token);
            return null;
        }
        String userStr = (String) userObj;
        MobileUserModel user = null;
        try {
            user = JsonTools.json2Object(userStr, MobileUserModel.class);
        } catch (IOException e) {
            logger.error("", e);
            return null;
        }
        return user;
    }

    protected String inputStreamToBase64Str(InputStream is) throws IOException {
        byte[] data = null;
        try {
            ByteArrayOutputStream swapStream = new ByteArrayOutputStream();
            byte[] buff = new byte[100];
            int rc = 0;
            while ((rc = is.read(buff, 0, 100)) > 0) {
                swapStream.write(buff, 0, rc);
            }
            data = swapStream.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    logger.error("", e);
                    throw e;
                }
            }
        }
        return new String(Base64.encodeBase64(data));
    }
}
