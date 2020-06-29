package hx.service.mobile.manage;

import hx.base.core.dao.dict.ErrorType;
import hx.base.core.manage.model.CommonResponse;
import hx.base.core.manage.tools.JsonTools;
import hx.service.mobile.manage.model.common.MobileCommonRequest;
import net.spy.memcached.MemcachedClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @name: AclHandlerInterceptor
 * @description: 自定义拦截器
 * @author: huojiajin
 * @time: 2020/5/27 16:25
 */
@Service
public class AclHandlerInterceptor extends HandlerInterceptorAdapter {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private MemcachedClient memcachedClient;

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        try {
            String requestURI = request.getRequestURI();
            logger.info("requestURI:{}", requestURI);
            String[] urlSplitArr = requestURI.substring(1).split("/");
            if (!urlSplitArr[1].equals("login") && !urlSplitArr[1].equals("error")) {//除登陆操作之外
                String requestData = getOpenApiRequestData(request);
                logger.info("request:{}", requestData);
                MobileCommonRequest commonRequest = JsonTools.json2Object(requestData, MobileCommonRequest.class);
                String userKey = MyMecachedPrefix.mobileLoginTokenPrefix + commonRequest.getToken();
                Object userObject = memcachedClient.get(userKey);
                if (userObject == null) {
                    return errorResponse(response, ErrorType.NOLOGIN);
                }
                memcachedClient.touch(userKey, 30 * 60);
            }
        } catch (Exception e) {
            logger.error("", e);
            return errorResponse(response, ErrorType.NOLOGIN);
        }
        return true;
    }

    public boolean errorResponse(HttpServletResponse response, ErrorType errorType) {
        CommonResponse commonResponse = new CommonResponse();
        commonResponse.setError(errorType);
        returnJson(response, commonResponse.toJson());
        return false;
    }

    private void returnJson(HttpServletResponse response, String json) {
        PrintWriter writer = null;
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        try {
            writer = response.getWriter();
            writer.print(json);
        } catch (IOException e) {
            logger.error("response error", e);
        } finally {
            if (writer != null)
                writer.close();
        }
    }

    private String getOpenApiRequestData(HttpServletRequest request) {
        try {
            int contentLength = request.getContentLength();
            if (contentLength < 0) {
                return null;
            }
            byte buffer[] = new byte[contentLength];
            for (int i = 0; i < contentLength; ) {

                int readlen = request.getInputStream().read(buffer, i, contentLength - i);
                if (readlen == -1) {
                    break;
                }
                i += readlen;
            }

            String charEncoding = request.getCharacterEncoding();
            if (charEncoding == null) {
                charEncoding = "UTF-8";
            }
            return new String(buffer, charEncoding);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
