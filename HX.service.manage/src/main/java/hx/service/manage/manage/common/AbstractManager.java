package hx.service.manage.manage.common;

import hx.base.core.dao.dict.share.ShareType;
import hx.base.core.dao.entity.acl.SystemInfo;
import hx.base.core.dao.entity.acl.User;
import hx.base.core.dao.entity.share.ShareData;
import hx.base.core.dao.repo.jpa.acl.SystemInfoRepo;
import hx.base.core.dao.repo.jpa.share.ShareDataRepo;
import hx.base.core.manage.common.CommonAbstract;
import hx.base.core.manage.tools.JsonTools;
import hx.service.manage.manage.tools.MyMecachedPrefix;
import net.spy.memcached.MemcachedClient;
import org.apache.commons.codec.binary.Base64;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
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

import java.io.*;
import java.net.URI;
import java.time.LocalDateTime;
import java.util.Map;

/**
 * @name: AbstractManager
 * @description: 通用类Manager
 * @author: huojiajin
 * @time: 2020/5/25 14:53
 */
public abstract class AbstractManager extends CommonAbstract {

    @Value("${serialNo}")
    protected String serialNo;
    @Value("${url}")
    protected String url;
    @Autowired
    private SystemInfoRepo systemInfoRepo;
    @Autowired
    protected MemcachedClient memcachedClient;
    @Autowired
    private ShareDataRepo shareDataRepo;

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
            httpPost.setHeader("Authorization", headerToken);
            httpPost.setHeader("serial_no", headerSerialNo);
            logger.info("======请求头信息为：");
            for (Header header : httpPost.getAllHeaders()) {
                System.out.println(header.getName() + ":" + header.getValue());
            }
            //添加请求体
            StringEntity stringEntity = new StringEntity(body, "UTF-8");
            //处理后的String
            logger.info("请求体是【{}】", body);
            httpPost.setEntity(stringEntity);
            //处理返回参数
            String result;
            response = httpclient.execute(httpPost);
            HttpEntity entity = response.getEntity();
            int httpCode = response.getStatusLine().getStatusCode();
            if(200 == httpCode){
                result = EntityUtils.toString(entity,"UTF-8").trim();
                logger.info("服务器请求成功,返回结果:{}",result);
            }else{
                result = EntityUtils.toString(entity,"UTF-8").trim();
                logger.error("失败HTTP状态码:【{}】失败信息【{}】",response.getStatusLine().getStatusCode(),result);
                throw new IOException("请求网关失败:" + result);
            }
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
        ShareData shareData = shareDataRepo.findByType(ShareType.ACCESSTOKEN);
        if (shareData == null){
            throw new IOException("======未获取到accessToken");
        }else {
            return shareData.getData();
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

    protected void addSysLog(String info, String token, String eigenValue) {
        User user = getUser(token);
        SystemInfo systemInfo = new SystemInfo();
        systemInfo.setInsertTime(LocalDateTime.now());
        systemInfo.setUserId(user.getId());
        systemInfo.setInfo(info);
        systemInfo.setEigenValue(eigenValue);
        systemInfoRepo.persist(systemInfo);
    }

    protected User getUser(String token){
        String userStr = (String)memcachedClient.get(MyMecachedPrefix.loginTokenPrefix + token);
        User user = null;
        try {
            user = JsonTools.json2Object(userStr, User.class);
        } catch (IOException e) {
            logger.error("", e);
            return null;
        }
        return user;
    }

    /**
     * @Name FileToBase64Str
     * @Author HuoJiaJin
     * @Description 文件转Base64字符串
     * @Date 2021/3/24 0:26
     * @Param [file]
     * @Return java.lang.String
     **/
    protected String FileToBase64Str(File file) throws IOException {
        InputStream in = null;
        try {
            in = new FileInputStream(file);
            byte[] bytes = new byte[(int) file.length()];
            in.read(bytes);
            return new String(Base64.encodeBase64(bytes));
        } catch (IOException e) {
            logger.error("", e);
            throw new IOException("文件转Base64转换失败");
        }finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    logger.error("", e);
                }
            }
        }
    }

    /**
     * @Name inputStreamToBase64Str
     * @Author HuoJiaJin
     * @Description 流转base64字符串
     * @Date 2021/3/24 0:26
     * @Param [is]
     * @Return java.lang.String
     **/
    protected String inputStreamToBase64Str(InputStream is) throws IOException {
        byte[] data = inputStreamToString(is);
        return new String(Base64.encodeBase64(data));
    }

    /**
     * @Name inputStreamToString
     * @Author HuoJiaJin
     * @Description 流转字符串
     * @Date 2021/3/24 0:27
     * @Param [is]
     * @Return byte[]
     **/
    private byte[] inputStreamToString(InputStream is) throws IOException {
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
        return data;
    }
}
